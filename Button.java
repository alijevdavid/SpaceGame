package space.shooter.xx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Ennek a classnak a segítségével lehet szükség szerint gombokat létrehozni.
 * @author Alijev Dávid
 *
 */
public class Button {
	
	private Texture img;
	private SpaceGame spacegame;
	
	private int ButtonWidth;
	private int ButtonHeight;
	private int gap;
	
	protected int palyaxFele;
	protected int palyayFele;
	
	/**
	 * A konstruktorban találhatóak különféle változók, mint a gomb kinézete, fõclass,
	 * a gomb hossza és magassága, a gombok közötti kihagyás, és a pálya fele függõlegesen és
	 * vízszintesen. Erre azért van szükség mert a rákattintás vizsgálatakor ez a támpont amit a program figyel.
	 * @param img
	 * Megadja a gomb textúráját.
	 * @param ButtonWidth
	 * Megadja a gomb szélességét.
	 * @param ButtonHeight
	 * Megadja a gomb magasságát.
	 */
	public Button(Texture img, int ButtonWidth, int ButtonHeight) {
		spacegame = new SpaceGame();
		this.img = img;
		this.gap = 2;
		this.ButtonWidth = ButtonWidth;
		this.ButtonHeight = ButtonHeight;
		
		this.palyaxFele = spacegame.getScreen_Width() / 2 - this.ButtonWidth / 2;
		this.palyayFele = spacegame.getScreen_Height() / 2;
	}
	/**
	 * Getter függvény a gomb szélességéhez.
	 * @return
	 * Visszaadja a gomb szélességét integer típusban
	 */
	public int getButtonWidth() {
		return this.ButtonWidth;
	}
	
	/**
	 * Getter függvény a gomb magasságához
	 * @return
	 * Visszaadja a gomb magasságát integer típusban
	 */
	public int getButtonHeight() {
		return this.ButtonHeight;
	}
	
	/**
	 * Getter függvény a gombok közötti kihagyáshoz
	 * @return
	 * Visszaadja, mennyi helyet kell kihagyni na gombok között, integer típusban
	 */
	public int getGap() {
		return this.gap;
	}
	
	/**
	 * Megrajzolja a gombot.
	 * @param batch
	 * A Sprite, amit a rajzoláshoz felhasznál
	 * @param x
	 * Rajzolás helyének x koordinátája.
	 * @param y
	 * Rajzolás helyének y koordinátája.
	 */
	public void draw(SpriteBatch batch, int x, int y) {
		batch.draw(img, x, y, ButtonWidth, ButtonHeight);
	}
	
	/**
	 * Megvizsgálja, hogy a felhasználó rákattintott e az adott gombra.
	 * @param x
	 * x hely mellyel meghatározza, hogy a felhasználó a kurzor x koordinátáját
	 * a gomb bal széléné nagybobb, és a jobb szélénél kisebb koordinátára vitte-e.
	 * Ebbõl a változóból azért van csak egy, mert a gombok csak az y koordinátán csúsznak el.
	 * @param y1
	 * y hely mellyel meghatározza, hogy a felhasználó a kurzor y koordinátáját
	 * a gomb felsõ részénél kisebb koordinátára vitte-e
	 * @param y2
	 * y hely mellyel meghatározza, hogy a felhasználó a kurzor y koordinátáját
	 * a gomb alsó részénél nagyobb koordinátára vitte-e
	 * @return
	 * Visszaad egy boolean értéket, mely true ha a felhasználó a gombra kattint, és false ha nem
	 */
	public boolean rakattint(int x, int y1, int y2) {
		if(Gdx.input.getX() < x + this.ButtonWidth && Gdx.input.getX() > x && Gdx.input.getY() < y1 && Gdx.input.getY() > y2 && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
			return true;
		}
		return false;
	}

}
