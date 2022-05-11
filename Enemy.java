package space.shooter.xx;

import com.badlogic.gdx.graphics.Texture;

import java.awt.Rectangle;
import java.util.Random;

/**
 * Ez a class felel a j�t�kban az ellens�gek�rt.
 * @author Alijev D�vid
 *
 */
public class Enemy extends Ship{
	
	private Texture img;
	private Random r;
	private boolean indul;
	private int random_mag;
	private boolean isAlive;
	private boolean lohet;
	private boolean iranytValthat;
	
	/**
	 * Az oszt�ly konstruktor�ban �ll�t�dnak be olyan alap�rt�kek mint a l�v�s, l�that�s�g, elindul�s, ir�nyv�lt�s (ezek
	 * a boolean �rt�kek v�ltoznak a j�t�kmenet sor�n), m�ret, sebess�g(amely a neh�zs�g alapj�n v�ltozik), a kin�zet �s a kiindul� helyzet. 
	 * A randomiz�l� oszt�ly �s az a random magass�g is itt kap �rt�ket, mely k�s�bb felelni fog az ellens�g v�gleges y koordin�t�ja�rt.
	 * @param difficulty
	 * Minden egyes enemy class megkapja az aktu�lis neh�zs�get, hogy ha a user id�k�zben �t�ll�tja, tudjanak az �rt�kek friss�lni.
	 */
	public Enemy(int difficulty) {
		this.r = new Random();
		
		this.isAlive = true;
		this.indul = true;
		this.lohet = false;
		this.iranytValthat = false;
		
		this.sebesseg = 5*difficulty;
		this.hajo_meret = 37;
		
		this.posX = r.nextInt(spacegame.getScreen_Width() - this.hajo_meret);
		this.posY = spacegame.getScreen_Height() - hajo_meret;
		
		this.random_mag = this.r.nextInt(0,this.hajo_meret);
	}
	
	/**
	 * Be�ll�tja az enemy Text�r�j�t
	 * @param img
	 * Aparam�terk�nt megadott k�pre �ll�tja be.
	 */
	public void setImg(Texture img) {
		this.img = img;
	}
	
	/**
	 * Setter f�gv�ny mely enged�lyezi az i�rnyv�t�st az enemy sz�m�ra.
	 * @param x
	 * Egy boolean �rt�k, erre �ll�t�dik be az ir�nyv�lt�s.
	 */
	public void setIranytValthat(boolean x) {
		this.iranytValthat = x;
	}
	
	
	/**
	 * Getter ahhoz a boolean �rt�khez, amely megmondja, hogy az enemy �letben van-e.
	 * @return
	 * Visszaadja azt a boolean �rt�ket, amely megmutatja, hogy az enemyt lel�tt�k-e m�r.
	 */
	public boolean getisAlive() {
		return this.isAlive;
	}
	
	/**
	 * Setter ahhoz a boolean �rt�khez, amely megmondja, hogy az enemy �letben van-e.
	 * @param x
	 * Egy boolean �rt�k, erre �ll�t�dik be, hogy az enemy �letben van-e.
	 */
	public void setisAlive(boolean x) {
		this.isAlive = x;
	}
	
	/**
	 * Setter ahhoz a boolean �rt�khez, amely megmondja, hogy az enemy l�het-e.
	 * @param x
	 * Egy boolean �rt�ket ad vissza, amely enged�lyezi, hogy az enemy l�v�st adjon.
	 */
	public void setLohet(boolean x) {
		this.lohet = x;
	}
	
	/**
	 * Getter ahhoz a boolean �rt�khez, amely megmondja, hogy az enemy l�het-e.
	 * @return
	 * Visszaadja azt a boolean �rt�ket, amely megmutatja, hogy az enemy l�het-e.
	 */
	public boolean getLohet() {
		return this.lohet;
	}
	
	/**
	 * Getter az enemy text�r�j�ra.
	 * @return
	 * Visszaadja az enemy k�p�t, text�r�j�t.
	 */
	public Texture getImg() {
		return this.img;
	}
	
	/**
	 * Egy t�glalpot rajzol az enemy k�r�, melynek seg�ts�g�vel meghat�rozhat� a hitbox.
	 * @return
	 * Visszaadja az enemy hitbox�t.
	 */
	public Rectangle getBounds() {
		Rectangle hitbox = new Rectangle(this.posX, this.posY + this.hajo_meret, this.hajo_meret, this.hajo_meret);
		return hitbox;
	}
	
	/**
	 * Meghat�roz egy random �rt�ket 1 �s (-1) k�z�tt.
	 * @return
	 * Random visszaad egy 1-est vagy egy (-1)-est.
	 */
	public int randomIrany() {
		return this.r.nextBoolean() ? -1 : 1;
	}
	
	/**
	 * Az enemy class update f�ggv�nye. El�sz�r az enemy lemegy egy random kikalkul�lt magass�gig, majd ha ir�nyv�lt�s,
	 * vagy els� elindul�s van, a program v�laszt egy random ir�nyt �s ezzel beszorozza a sebess�get, ett�l f�gg�en fog jobbra vagy balra menni az enemy.
	 * A k�vetkez� 2 if nem engedi, gogy az objektum kil�pjen a p�ly�r�l, �gy, hogy veszi az �ppen aktu�lis sebess�g ellentettj�t, �gy az ir�ny megfordul.
	 */
	public void update() {
		
		if(this.posY > spacegame.getScreen_Height() - this.hajo_meret*3 - this.random_mag) {
			this.posY -= sebesseg;
		}
		
		else {
			if(indul || this.iranytValthat) {
				this.sebesseg *= randomIrany();
				this.indul = false;
				this.iranytValthat = false;
			}
		
			else{
				if(this.posX < 0) {
					this.sebesseg = -this.sebesseg;
				}
				if (this.posX + this.hajo_meret >= spacegame.getScreen_Width()){
					this.sebesseg = -this.sebesseg;
				}
			}
			
			this.posX += this.sebesseg;
		}
	}
}
