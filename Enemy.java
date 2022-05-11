package space.shooter.xx;

import com.badlogic.gdx.graphics.Texture;

import java.awt.Rectangle;
import java.util.Random;

/**
 * Ez a class felel a játékban az ellenségekért.
 * @author Alijev Dávid
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
	 * Az osztály konstruktorában állítódnak be olyan alapértékek mint a lövés, láthatóság, elindulás, irányváltás (ezek
	 * a boolean értékek változnak a játékmenet során), méret, sebesség(amely a nehézség alapján változik), a kinézet és a kiinduló helyzet. 
	 * A randomizáló osztály és az a random magasság is itt kap értéket, mely késõbb felelni fog az ellenség végleges y koordinátájaért.
	 * @param difficulty
	 * Minden egyes enemy class megkapja az aktuális nehézséget, hogy ha a user idõközben átállítja, tudjanak az értékek frissülni.
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
	 * Beállítja az enemy Textúráját
	 * @param img
	 * Aparaméterként megadott képre állítja be.
	 */
	public void setImg(Texture img) {
		this.img = img;
	}
	
	/**
	 * Setter fügvény mely engedélyezi az iárnyvátást az enemy számára.
	 * @param x
	 * Egy boolean érték, erre állítódik be az irányváltás.
	 */
	public void setIranytValthat(boolean x) {
		this.iranytValthat = x;
	}
	
	
	/**
	 * Getter ahhoz a boolean értékhez, amely megmondja, hogy az enemy életben van-e.
	 * @return
	 * Visszaadja azt a boolean értéket, amely megmutatja, hogy az enemyt lelõtték-e már.
	 */
	public boolean getisAlive() {
		return this.isAlive;
	}
	
	/**
	 * Setter ahhoz a boolean értékhez, amely megmondja, hogy az enemy életben van-e.
	 * @param x
	 * Egy boolean érték, erre állítódik be, hogy az enemy életben van-e.
	 */
	public void setisAlive(boolean x) {
		this.isAlive = x;
	}
	
	/**
	 * Setter ahhoz a boolean értékhez, amely megmondja, hogy az enemy lõhet-e.
	 * @param x
	 * Egy boolean értéket ad vissza, amely engedélyezi, hogy az enemy lövést adjon.
	 */
	public void setLohet(boolean x) {
		this.lohet = x;
	}
	
	/**
	 * Getter ahhoz a boolean értékhez, amely megmondja, hogy az enemy lõhet-e.
	 * @return
	 * Visszaadja azt a boolean értéket, amely megmutatja, hogy az enemy lõhet-e.
	 */
	public boolean getLohet() {
		return this.lohet;
	}
	
	/**
	 * Getter az enemy textúrájára.
	 * @return
	 * Visszaadja az enemy képét, textúráját.
	 */
	public Texture getImg() {
		return this.img;
	}
	
	/**
	 * Egy téglalpot rajzol az enemy köré, melynek segítségével meghatározható a hitbox.
	 * @return
	 * Visszaadja az enemy hitboxát.
	 */
	public Rectangle getBounds() {
		Rectangle hitbox = new Rectangle(this.posX, this.posY + this.hajo_meret, this.hajo_meret, this.hajo_meret);
		return hitbox;
	}
	
	/**
	 * Meghatároz egy random értéket 1 és (-1) között.
	 * @return
	 * Random visszaad egy 1-est vagy egy (-1)-est.
	 */
	public int randomIrany() {
		return this.r.nextBoolean() ? -1 : 1;
	}
	
	/**
	 * Az enemy class update függvénye. Elõször az enemy lemegy egy random kikalkulált magasságig, majd ha irányváltás,
	 * vagy elsõ elindulás van, a program választ egy random irányt és ezzel beszorozza a sebességet, ettõl függõen fog jobbra vagy balra menni az enemy.
	 * A következõ 2 if nem engedi, gogy az objektum kilépjen a pályáról, úgy, hogy veszi az éppen aktuális sebesség ellentettjét, így az irány megfordul.
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
