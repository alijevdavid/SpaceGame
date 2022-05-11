package space.shooter.xx;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * Ez az oszt�ly fele a men� megjelen�t�s��rt.
 * @author Alijev D�vid
 *
 */
public class MenuScreen implements Screen{

	SpaceGame spacegame;
	
	Button start;
	Button exit;
	
	
	/**
	 * Az oszt�ly konstruktor�ban defini�l�dik 2 gomb(start, exit).
	 * @param spacegame
	 * Ezzel megkapja a f� classot, melyben vannak a Spriteok(rajzol�shoz, ki�r�shoz), met�dusok,
	 * �s f�ggv�nyek, melyeket az oszt�lynak ismernie kell a megfek� m�k�d�shez.
	 */
	public MenuScreen(SpaceGame spacegame) {
		this.spacegame = spacegame;
		this.start = new Button(new Texture("StartButton.jpg"), 271, 55);
		this.exit = new Button(new Texture("ExitButton.jpg"), 271, 55);
	}
	
	/**
	 * Ennek a screen sozt�lynak a render met�dus�ban t�rt�nik a gombok kirajzol�sa
	 * �s annak vizsg�lata, hogy a j�t�kos vesz�tett-e �s ha igen, ezt ki�rja,
	 * ezek mellett a felhaszn�l� r�juk kattintott-e, �s ha igen, elv�gzi a k�v�nt m�veleteket.
	 * Azt is figyeli m�g, hogy a user megnyomta-e az escape billenty�t, mert ha igen, a program bez�r�dik.
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
	 * A tov�bbi be�p�tett f�ggv�nyek k�telez�en implent�land�ak, de nem k�telez� �ket felhaszn�lni.
	 * Nev�k egy�rtelm�s�ti funkci�iukat.
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
