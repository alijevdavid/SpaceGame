package space.shooter.xx;


import java.awt.Rectangle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/*
 * Az enemyk �s a j�t�kos �ltal leadott l�v�sek oszt�lya.
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
	 * Az oszt�ly konstruktora. Be�ll�t�dnak az alap�rt�kek, mintm�ret, text�ra, sebess�g, indul�si poz�ci�.
	 * @param hajo
	 * Megadja melyik haj�hoz tartozik a l�v�s.
	 * @param irany
	 * Megadja milyen ir�nyba megy a l�ved�k(ellens�g-le, j�t�kos-fel).
	 * @param bulletSpeed
	 * Megadja a l�v�s sebess�g�t, amely a neh�zs�gt�l f�gg.
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
	 * Visszaadja a l�ved�k sebess�g�t.
	 */
	public int getSebesseg() {
		return this.sebesseg;
	}
	
	/**
	 * @return
	 * Visszaadja a l�ved�k m�ret�t.
	 */
	public int getMeret() {
		return this.meret;
	}
	
	/**
	 * @return
	 * Visszaadja a lov�d x pozici�j�t.
	 */
	public float getBulletPosX() {
		return BulletPosX;
	}
	
	/**
	 * @return
	 * Visszaadja a lov�d y pozici�j�t.
	 */
	public float getBulletPosY() {
		return BulletPosY;
	}
	
	public void setImg(Texture img) {
		this.BulletImg = img;
	}
	
	/**
	 * Rajzol egy t�glalapot a l�ved�k k�r�, amely megmutatja a hitboxot.
	 * @return
	 * Visszaadja a l�ved�k hitbox�t.
	 */
	public Rectangle getBounds() {
		Rectangle hitbox = new Rectangle(this.BulletPosX, this.BulletPosY + this.meret, this.meret, this.meret);
		return hitbox;
	}
	
	/**
	 * Ez a f�ggv�ny rajzolja meg a l�ved�ket, helyzet �s m�ret alapj�n.
	 * @param batch
	 * Egy sprite, mely a kirajzol�s�rt felel.
	 */
	public void draw(SpriteBatch batch) {
		batch.draw(BulletImg, BulletPosX + (hajo.getHajo_meret() / 2 - meret/2), BulletPosY + hajo.getHajo_meret(), this.meret, this.meret);
	}
	
	/**
	 * A l�ved�k update f�ggv�nye, a megadott ir�ny szerint cs�kkenti vagy n�veli az y koordin�t�t.
	 */
	public void update () {
		BulletPosY += sebesseg*irany;
	}

}
