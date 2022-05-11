package space.shooter.xx;

import java.awt.Rectangle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

/**
 * Ez a class felel a j�t�kos haj��rt.
 * @author Alijev D�vid
 *
 */
public class PlayerShip extends Ship{


	private Texture img;
	private int sebesseg;
	private int current_score;
	
	/**
	 * Ez az oszt�ly konstruktora. Be�ll�t�dik a kiindul�pontsz�m, a kin�zet, a sebess�g, a m�ret, �s be�ll�t�dik a kiindul�helyzet.
	 */
	public PlayerShip() {
		this.current_score = 0;
		this.img = new Texture("hajo.png");
		this.sebesseg = 10;
		this.hajo_meret = 75;
		this.posX = spacegame.getScreen_Width() / 2 - this.hajo_meret/2;
		this.posY = 10;
	}
	
	/**
	 * Getter f�ggv�ny a j�tkos aktu�lis pontsz�m�ra.
	 * @return
	 * Visszaadja a j�t�kos aktu�lis pontsz�m�t.
	 */
	public int getCurrent_score() {
		return this.current_score;
	}
	
	/**
	 * Setter f�ggv�ny a j�tkos aktu�lis pontsz�m�ra.
	 * @return
	 * Be�ll�tja (legink�bb lenull�zza) a j�t�kos aktu�lis pontsz�m�t.
	 */
	
	public void setCurrent_score(int x) {
		this.current_score = x;
	}
	
	/**
	 * Megn�veli a j�tkos pontsz�m�t.
	 * @param x
	 * Ezzel a param�terrel n�veli a pontsz�mot.
	 */
	public void increaseCurrent_score(int x) {
		this.current_score += x;
	}
	
	/**
	 * Rajzol egy t�glalapot a j�t�kos k�r�, amely megmutatja a hitboxot.
	 * @return
	 * Visszaadja a j�tkos hitbox�t.
	 */
	public Rectangle getBounds() {
		Rectangle hitbox = new Rectangle(this.posX, this.posY + this.hajo_meret, this.hajo_meret, this.hajo_meret);
		return hitbox;
	}
	
	/**
	 * Megadja a j�tkos k�p�t.
	 * @return
	 * Visszaadja a j�tkos text�r�j�t.
	 */
	public Texture getImg() {
		return this.img;
	}
	
	/**
	 * Ez a j�t�kos update f�ggv�nye. Bal ny�l lenyom�s�ra balra, jobb ny�l lenyom�s�ra jobbra mely a haj�. Itt figyeli a program
	 * azt is, hogy a j�t�kos ne mehessen ki a p�ly�r�l.
	 */
	public void update() {
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && this.posX + this.hajo_meret < spacegame.getScreen_Width()) {
			this.posX += this.sebesseg;
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && this.posX > 0) {
			this.posX -= this.sebesseg;
		}
	}


}
