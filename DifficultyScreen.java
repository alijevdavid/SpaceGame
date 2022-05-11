package space.shooter.xx;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;


/**
 * Ez a képernyõ class jelenteti meg azt a képernyõt, ahol,
 * a nehézség kiválasztására van lehetõség.
 * @author Alijev Dávid
 *
 */
public class DifficultyScreen implements Screen{
	
	private SpaceGame spacegame;
	private Button easy;
	private Button medium;
	private Button hard;
	

	/**
	 * Ez az osztály konstruktora, itt inicializálom annak a 3 gombnak a képét, 
	 * amelyet az osztály használni fog
	 * 
	 * @param spacegame
	 * Ezzel megkapja a fõ classot, melyben vannak a Spriteok(rajzoláshoz, kiíráshoz), metódusok,
	 * és függvények, melyeket az osztálynak ismernie kell a megfekõ mûködéshez.
	 */
	public DifficultyScreen(SpaceGame spacegame) {
		this.spacegame = spacegame;
		this.easy = new Button(new Texture("easy.jpg"), 350, 110);
		this.medium = new Button(new Texture("medium.jpg"), 350, 110);
		this.hard = new Button(new Texture("hard.jpg"), 350, 110);
		
	}
	
	/**
	 * A függvényben történik a Textúrák kirajzolása, és a vizsgálások is.
	 * Elõször beállítódik a háttér feketére, majd elkezdõdik a kirajzolása a 3 gombnak.
	 * Ezek után 3 ifet tartalmaz melyekben folyamatosan figyeli, hogy a felhasználó
	 * belekattintott-e az egyik gombba, és ha igen végrehajtja a magban leírtakat. Ezek setter
	 * függvények, melyek segítségével beállítódnak azok az adatai a játéknak, melyek neheézség alapján változhatnak.
	 * A legutolsó vizsgálat pedig megfigyeli, hogy a user megnyomta-e az "exit" gombot, és ha igen, kilép a programból.
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
	 * Ez a beépített függvény a nevébõl adódóan is, elrejti a képernyõt.
	 */
	@Override
	public void hide() {
		
	}
	/**
	 * A további beépített függvények kötelezõen implentálandóak, de nem kötelezõ õket felhasználni.
	 * Nevük egyértelmüsíti funkcióiukat.
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
