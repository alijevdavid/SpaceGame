package space.shooter.xx;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * Ez az osztály fele a menü megjelenítéséért.
 * @author Alijev Dávid
 *
 */
public class MenuScreen implements Screen{

	SpaceGame spacegame;
	
	Button start;
	Button exit;
	
	
	/**
	 * Az osztály konstruktorában definiálódik 2 gomb(start, exit).
	 * @param spacegame
	 * Ezzel megkapja a fõ classot, melyben vannak a Spriteok(rajzoláshoz, kiíráshoz), metódusok,
	 * és függvények, melyeket az osztálynak ismernie kell a megfekõ mûködéshez.
	 */
	public MenuScreen(SpaceGame spacegame) {
		this.spacegame = spacegame;
		this.start = new Button(new Texture("StartButton.jpg"), 271, 55);
		this.exit = new Button(new Texture("ExitButton.jpg"), 271, 55);
	}
	
	/**
	 * Ennek a screen soztálynak a render metódusában történik a gombok kirajzolása
	 * és annak vizsgálata, hogy a játékos veszített-e és ha igen, ezt kiírja,
	 * ezek mellett a felhasználó rájuk kattintott-e, és ha igen, elvégzi a kívánt mûveleteket.
	 * Azt is figyeli még, hogy a user megnyomta-e az escape billentyût, mert ha igen, a program bezáródik.
	 */
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 0);
		spacegame.batch.begin();
		start.draw(spacegame.batch, spacegame.getScreen_Width() / 2 - start.getButtonWidth() / 2, spacegame.getScreen_Height() / 2);
		exit.draw(spacegame.batch, spacegame.getScreen_Width() / 2 - exit.getButtonWidth() / 2, spacegame.getScreen_Height() / 2 - (exit.getButtonHeight() + exit.getGap()));
		
		if(spacegame.lost) {
			spacegame.font.draw(spacegame.batch, "You Lost", spacegame.getScreen_Width() / 2 ,spacegame.getScreen_Height() - 30);
		}
		spacegame.batch.end();
		
		//start gomb
		if(start.rakattint(start.palyaxFele, start.palyayFele, start.palyayFele - start.getButtonHeight())) {
			spacegame.setLost(false);
			spacegame.setScreen(new DifficultyScreen(spacegame));
			this.hide();
		}
		
		
		//exit gomb
		if(exit.rakattint(exit.palyaxFele, start.palyayFele + start.getButtonHeight(), start.palyayFele)) {
			Gdx.app.exit();
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
	}
	
	
	/**
	 * A további beépített függvények kötelezõen implentálandóak, de nem kötelezõ õket felhasználni.
	 * Nevük egyértelmüsíti funkcióiukat.
	 */
	@Override
	public void dispose() {
		
	}

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
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

}
