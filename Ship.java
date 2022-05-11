package space.shooter.xx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * Az �sszes haj� class mesteroszt�lya, egy abstract oszt�ly, ebb�l sz�rmazik az enemy-, �s a j�t�koshaj�.
 * @author Alijev D�vid
 *
 */
public abstract class Ship {
	
	protected int hajo_meret;
	protected int posX;
	protected int posY;
	protected int sebesseg;
	protected SpaceGame spacegame;
	
	/**
	 * Az oszt�ly konstruktora, itt defini�l�dik a SpaceGame f�class, mely seg�ts�g�vel a lesz�rmazottak fontos adatokat k�rhetnek le.
	 */
	public Ship() {
		this.spacegame = new SpaceGame();
  	}
	
	/**
	 * @return
	 * Visszaadja a haj� m�ret�t.
	 */
	public int getHajo_meret() {
		return this.hajo_meret;
	}
	
	/**
	 * @return
	 * Visszaadja a haj� x koordin�t�j�t.
	 */
	public int getHajoPosX() {
		return this.posX;
	}
	
	/**
	 * @return
	 * Visszaadja a haj� y koordin�t�j�t.
	 */
	public int getHajoPosY() {
		return this.posY;
	}
	
	/**
	 * @return
	 * Visszaadja a haj� sebess�g�t.
	 */
	public int getSebesseg() {
		return this.sebesseg;
	}
	
	/**
	 * Kirajzolja a haj�t m�retei �s k�pei alapj�n.
	 * @param batch
	 * Egy sprite, mely a kirajzol�s�rt felel.
	 * @param img
	 * Az a text�ra, melyet ki kell rajzolni.
	 */
	public void draw(SpriteBatch batch, Texture img) {
		batch.draw(img, posX, posY, hajo_meret, hajo_meret);
	}

}
