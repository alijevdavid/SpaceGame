package space.shooter.xx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Ennek a classnak a seg�ts�g�vel lehet sz�ks�g szerint gombokat l�trehozni.
 * @author Alijev D�vid
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
	 * A konstruktorban tal�lhat�ak k�l�nf�le v�ltoz�k, mint a gomb kin�zete, f�class,
	 * a gomb hossza �s magass�ga, a gombok k�z�tti kihagy�s, �s a p�lya fele f�gg�legesen �s
	 * v�zszintesen. Erre az�rt van sz�ks�g mert a r�kattint�s vizsg�latakor ez a t�mpont amit a program figyel.
	 * @param img
	 * Megadja a gomb text�r�j�t.
	 * @param ButtonWidth
	 * Megadja a gomb sz�less�g�t.
	 * @param ButtonHeight
	 * Megadja a gomb magass�g�t.
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
	 * Getter f�ggv�ny a gomb sz�less�g�hez.
	 * @return
	 * Visszaadja a gomb sz�less�g�t integer t�pusban
	 */
	public int getButtonWidth() {
		return this.ButtonWidth;
	}
	
	/**
	 * Getter f�ggv�ny a gomb magass�g�hoz
	 * @return
	 * Visszaadja a gomb magass�g�t integer t�pusban
	 */
	public int getButtonHeight() {
		return this.ButtonHeight;
	}
	
	/**
	 * Getter f�ggv�ny a gombok k�z�tti kihagy�shoz
	 * @return
	 * Visszaadja, mennyi helyet kell kihagyni na gombok k�z�tt, integer t�pusban
	 */
	public int getGap() {
		return this.gap;
	}
	
	/**
	 * Megrajzolja a gombot.
	 * @param batch
	 * A Sprite, amit a rajzol�shoz felhaszn�l
	 * @param x
	 * Rajzol�s hely�nek x koordin�t�ja.
	 * @param y
	 * Rajzol�s hely�nek y koordin�t�ja.
	 */
	public void draw(SpriteBatch batch, int x, int y) {
		batch.draw(img, x, y, ButtonWidth, ButtonHeight);
	}
	
	/**
	 * Megvizsg�lja, hogy a felhaszn�l� r�kattintott e az adott gombra.
	 * @param x
	 * x hely mellyel meghat�rozza, hogy a felhaszn�l� a kurzor x koordin�t�j�t
	 * a gomb bal sz�l�n� nagybobb, �s a jobb sz�l�n�l kisebb koordin�t�ra vitte-e.
	 * Ebb�l a v�ltoz�b�l az�rt van csak egy, mert a gombok csak az y koordin�t�n cs�sznak el.
	 * @param y1
	 * y hely mellyel meghat�rozza, hogy a felhaszn�l� a kurzor y koordin�t�j�t
	 * a gomb fels� r�sz�n�l kisebb koordin�t�ra vitte-e
	 * @param y2
	 * y hely mellyel meghat�rozza, hogy a felhaszn�l� a kurzor y koordin�t�j�t
	 * a gomb als� r�sz�n�l nagyobb koordin�t�ra vitte-e
	 * @return
	 * Visszaad egy boolean �rt�ket, mely true ha a felhaszn�l� a gombra kattint, �s false ha nem
	 */
	public boolean rakattint(int x, int y1, int y2) {
		if(Gdx.input.getX() < x + this.ButtonWidth && Gdx.input.getX() > x && Gdx.input.getY() < y1 && Gdx.input.getY() > y2 && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
			return true;
		}
		return false;
	}

}
