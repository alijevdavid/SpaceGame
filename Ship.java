package space.shooter.xx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * Az összes hajó class mesterosztálya, egy abstract osztály, ebbõl származik az enemy-, és a játékoshajó.
 * @author Alijev Dévid
 *
 */
public abstract class Ship {
	
	protected int hajo_meret;
	protected int posX;
	protected int posY;
	protected int sebesseg;
	protected SpaceGame spacegame;
	
	/**
	 * Az osztály konstruktora, itt definiálódik a SpaceGame fõclass, mely segítségével a leszármazottak fontos adatokat kérhetnek le.
	 */
	public Ship() {
		this.spacegame = new SpaceGame();
  	}
	
	/**
	 * @return
	 * Visszaadja a hajó méretét.
	 */
	public int getHajo_meret() {
		return this.hajo_meret;
	}
	
	/**
	 * @return
	 * Visszaadja a hajó x koordinátáját.
	 */
	public int getHajoPosX() {
		return this.posX;
	}
	
	/**
	 * @return
	 * Visszaadja a hajó y koordinátáját.
	 */
	public int getHajoPosY() {
		return this.posY;
	}
	
	/**
	 * @return
	 * Visszaadja a hajó sebességét.
	 */
	public int getSebesseg() {
		return this.sebesseg;
	}
	
	/**
	 * Kirajzolja a hajót méretei és képei alapján.
	 * @param batch
	 * Egy sprite, mely a kirajzolásért felel.
	 * @param img
	 * Az a textúra, melyet ki kell rajzolni.
	 */
	public void draw(SpriteBatch batch, Texture img) {
		batch.draw(img, posX, posY, hajo_meret, hajo_meret);
	}

}
