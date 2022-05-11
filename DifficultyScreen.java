package space.shooter.xx;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;


/**
 * Ez a k�perny� class jelenteti meg azt a k�perny�t, ahol,
 * a neh�zs�g kiv�laszt�s�ra van lehet�s�g.
 * @author Alijev D�vid
 *
 */
public class DifficultyScreen implements Screen{
	
	private SpaceGame spacegame;
	private Button easy;
	private Button medium;
	private Button hard;
	

	/**
	 * Ez az oszt�ly konstruktora, itt inicializ�lom annak a 3 gombnak a k�p�t, 
	 * amelyet az oszt�ly haszn�lni fog
	 * 
	 * @param spacegame
	 * Ezzel megkapja a f� classot, melyben vannak a Spriteok(rajzol�shoz, ki�r�shoz), met�dusok,
	 * �s f�ggv�nyek, melyeket az oszt�lynak ismernie kell a megfek� m�k�d�shez.
	 */
	public DifficultyScreen(SpaceGame spacegame) {
		this.spacegame = spacegame;
		this.easy = new Button(new Texture("easy.jpg"), 350, 110);
		this.medium = new Button(new Texture("medium.jpg"), 350, 110);
		this.hard = new Button(new Texture("hard.jpg"), 350, 110);
		
	}
	
	/**
	 * A f�ggv�nyben t�rt�nik a Text�r�k kirajzol�sa, �s a vizsg�l�sok is.
	 * El�sz�r be�ll�t�dik a h�tt�r feket�re, majd elkezd�dik a kirajzol�sa a 3 gombnak.
	 * Ezek ut�n 3 ifet tartalmaz melyekben folyamatosan figyeli, hogy a felhaszn�l�
	 * belekattintott-e az egyik gombba, �s ha igen v�grehajtja a magban le�rtakat. Ezek setter
	 * f�ggv�nyek, melyek seg�ts�g�vel be�ll�t�dnak azok az adatai a j�t�knak, melyek nehe�zs�g alapj�n v�ltozhatnak.
	 * A legutols� vizsg�lat pedig megfigyeli, hogy a user megnyomta-e az "exit" gombot, �s ha igen, kil�p a programb�l.
	 */
	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 0);
		
		spacegame.batch.begin();
		easy.draw(spacegame.batch, spacegame.getScreen_Width() / 2 - easy.getButtonWidth() / 2, spacegame.getScreen_Height() / 2 + (easy.getButtonHeight() + easy.getGap()));
		medium.draw(spacegame.batch, spacegame.getScreen_Width() / 2 - medium.getButtonWidth() / 2, spacegame.getScreen_Height() / 2 );
		hard.draw(spacegame.batch, spacegame.getScreen_Width() / 2 - hard.getButtonWidth() / 2, spacegame.getScreen_Height() / 2 - (hard.getButtonHeight() + hard.getGap()));
		
		spacegame.batch.end();
		
		//easy gomb
		if(easy.rakattint(easy.palyaxFele, easy.palyayFele - easy.getButtonHeight() , easy.palyayFele - 2 * easy.getButtonHeight())) {
			spacegame.setBulletSpeed(10);
			spacegame.setDifficulty(1);
			spacegame.setEnemySpawnRate(4);
			spacegame.setShootRate(3);
			spacegame.setAvailableLives(3);
			spacegame.setScreen(new GameScreen(spacegame));
			this.hide();
		}
		
		//medium gomb
		if(medium.rakattint(medium.palyaxFele, medium.palyayFele, medium.palyayFele - medium.getButtonHeight())) {
			spacegame.setDifficulty(2);
			spacegame.setEnemySpawnRate(3);
			spacegame.setShootRate(2);
			spacegame.setBulletSpeed(20);
			spacegame.setAvailableLives(2);
			spacegame.setScreen(new GameScreen(spacegame));
			this.hide();
		}
		
		//hard gomb
		if(medium.rakattint(medium.palyaxFele, medium.palyayFele + hard.getButtonHeight(), medium.palyayFele)) {
			spacegame.setDifficulty(3);
			spacegame.setEnemySpawnRate(2);
			spacegame.setShootRate(1);
			spacegame.setBulletSpeed(30);
			spacegame.setAvailableLives(1);
			spacegame.setScreen(new GameScreen(spacegame));
			this.hide();
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
	}
	
	/**
	 * Ez a be�p�tett f�ggv�ny a nev�b�l ad�d�an is, elrejti a k�perny�t.
	 */
	@Override
	public void hide() {
		
	}
	/**
	 * A tov�bbi be�p�tett f�ggv�nyek k�telez�en implent�land�ak, de nem k�telez� �ket felhaszn�lni.
	 * Nev�k egy�rtelm�s�ti funkci�iukat.
	 */
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

}
