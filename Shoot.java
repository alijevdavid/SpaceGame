package space.shooter.xx;


import java.awt.Rectangle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/*
 * Az enemyk és a játékos által leadott lövések osztálya.
 */
public class Shoot{
	Texture BulletImg;
	Ship hajo;
	
	private int BulletPosX;
	private int BulletPosY;
	private int meret;
	private int sebesseg;
	private int irany;
	
	/**
	 * Az osztály konstruktora. Beállítódnak az alapértékek, mintméret, textúra, sebesség, indulási pozíció.
	 * @param hajo
	 * Megadja melyik hajóhoz tartozik a lövés.
	 * @param irany
	 * Megadja milyen irányba megy a lövedék(ellenség-le, játékos-fel).
	 * @param bulletSpeed
	 * Megadja a lövés sebességét, amely a nehézségtõl függ.
	 */
	public Shoot(Ship hajo, int irany, int bulletSpeed) {
		this.hajo = hajo;
		this.meret = 10;
		this.sebesseg = bulletSpeed;
		this.BulletPosX = hajo.getHajoPosX();
		this.BulletPosY = hajo.getHajoPosY();
		this.irany = irany;
	}
	
	/**
	 * @return
	 * Visszaadja a lövedék sebességét.
	 */
	public int getSebesseg() {
		return this.sebesseg;
	}
	
	/**
	 * @return
	 * Visszaadja a lövedék méretét.
	 */
	public int getMeret() {
		return this.meret;
	}
	
	/**
	 * @return
	 * Visszaadja a lovéd x pozicióját.
	 */
	public float getBulletPosX() {
		return BulletPosX;
	}
	
	/**
	 * @return
	 * Visszaadja a lovéd y pozicióját.
	 */
	public float getBulletPosY() {
		return BulletPosY;
	}
	
	public void setImg(Texture img) {
		this.BulletImg = img;
	}
	
	/**
	 * Rajzol egy téglalapot a lövedék köré, amely megmutatja a hitboxot.
	 * @return
	 * Visszaadja a lövedék hitboxát.
	 */
	public Rectangle getBounds() {
		Rectangle hitbox = new Rectangle(this.BulletPosX, this.BulletPosY + this.meret, this.meret, this.meret);
		return hitbox;
	}
	
	/**
	 * Ez a függvény rajzolja meg a lövedéket, helyzet és méret alapján.
	 * @param batch
	 * Egy sprite, mely a kirajzolásért felel.
	 */
	public void draw(SpriteBatch batch) {
		batch.draw(BulletImg, BulletPosX + (hajo.getHajo_meret() / 2 - meret/2), BulletPosY + hajo.getHajo_meret(), this.meret, this.meret);
	}
	
	/**
	 * A lövedék update függvénye, a megadott irány szerint csökkenti vagy növeli az y koordinátát.
	 */
	public void update () {
		BulletPosY += sebesseg*irany;
	}

}
