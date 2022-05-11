package space.shooter.xx;

import java.awt.Rectangle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

/**
 * Ez a class felel a játékos hajóért.
 * @author Alijev Dévid
 *
 */
public class PlayerShip extends Ship{


	private Texture img;
	private int sebesseg;
	private int current_score;
	
	/**
	 * Ez az osztály konstruktora. Beállítódik a kiindulópontszám, a kinézet, a sebesség, a méret, és beállítódik a kiindulóhelyzet.
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
	 * Getter függvény a játkos aktuális pontszámára.
	 * @return
	 * Visszaadja a játékos aktuális pontszámát.
	 */
	public int getCurrent_score() {
		return this.current_score;
	}
	
	/**
	 * Setter függvény a játkos aktuális pontszámára.
	 * @return
	 * Beállítja (leginkább lenullázza) a játékos aktuális pontszámát.
	 */
	
	public void setCurrent_score(int x) {
		this.current_score = x;
	}
	
	/**
	 * Megnöveli a játkos pontszámát.
	 * @param x
	 * Ezzel a paraméterrel növeli a pontszámot.
	 */
	public void increaseCurrent_score(int x) {
		this.current_score += x;
	}
	
	/**
	 * Rajzol egy téglalapot a játékos köré, amely megmutatja a hitboxot.
	 * @return
	 * Visszaadja a játkos hitboxát.
	 */
	public Rectangle getBounds() {
		Rectangle hitbox = new Rectangle(this.posX, this.posY + this.hajo_meret, this.hajo_meret, this.hajo_meret);
		return hitbox;
	}
	
	/**
	 * Megadja a játkos képét.
	 * @return
	 * Visszaadja a játkos textúráját.
	 */
	public Texture getImg() {
		return this.img;
	}
	
	/**
	 * Ez a játékos update függvénye. Bal nyíl lenyomására balra, jobb nyíl lenyomására jobbra mely a hajó. Itt figyeli a program
	 * azt is, hogy a játékos ne mehessen ki a pályáról.
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
