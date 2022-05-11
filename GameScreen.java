package space.shooter.xx;
import java.util.Random;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * Ez a class egy screen class, melyben megjelntetik a j�t�k.
 * @author Alijev D�vid
 *
 */
public class GameScreen implements Screen{
	
	SpaceGame spacegame;

	int healthPointHeight;
	int healthPointWidth;
	
	int menuButtonxHelyzet;
	int menuButtonyHelyzet;
	
	int replayButtonxHelyzet;
	int replayButtonyHelyzet;
	
	
	Button menu;
	Button replay;
	
	Timer clock;
	
	Random r;
	
	/**
	 * Az oszt�ly konstruktor�ban defini�l�dik sok v�ltoz�, amelyek ezen a k�perny�n jelennek meg, t�rt�nnek.
	 * Ilyenek a gombok (ezek m�retei �s helye), a timer amely m�sodpercenk�nt sz�mol felfele, �s egy randomiz�l� class
	 * @param spacegame
	 * Ezzel megkapja a f� classot, melyben vannak a Spriteok(rajzol�shoz, ki�r�shoz), met�dusok,
	 * �s f�ggv�nyek, melyeket az oszt�lynak ismernie kell a megfek� m�k�d�shez.
	 */
	public GameScreen(SpaceGame spacegame) {
		this.spacegame = spacegame;
		this.r = new Random();
		this.replay = new Button(new Texture("replay.png"), 100, 33);
		this.menu = new Button(new Texture("menu.png"), 100, 33);
	
		this.clock = new Timer(Gdx.graphics.getDeltaTime());
		
		this.healthPointHeight = spacegame.jatekos.getHajo_meret() / 3;
		this.healthPointWidth = spacegame.jatekos.getHajo_meret() / 3;
		
		this.menuButtonxHelyzet = spacegame.getScreen_Width() - menu.getButtonWidth();
		this.menuButtonyHelyzet = 0;
		
		this.replayButtonxHelyzet = spacegame.getScreen_Width() - replay.getButtonWidth();
		this.replayButtonyHelyzet = replay.getButtonHeight();
		
	}
	
	/**
	 * Ez a render met�dus, melyben a kirajzol�s �s a vizsg�latok folynak.
	 * Legels�nek megh�v�dik a SpaceGame class update met�dusa, azt�n elindul a timer.
	 * A k�vetkez�kben megn�zi a program, hogy hol j�r a timer �s milyen milyen neh�zs�gen vagyunk (spawnrate �s shootrate) �s annak megfele� 
	 * gyakoris�ggal jelenteti meg az ellenfeleket �s l�nek azok. Itt t�rt�nik a random ir�nyv�lt�sa az ellens�geknek is bizonyos id�k�z�nk�nt.
	 * Ezek ut�n t�rt�nik az �sszes kirajzol�s �s ki�rat�s ami ezen a screenen t�rt�nik.
	 * Els� az �leter�pontok kirajzol�sa a kiv�lasztott neh�zs�gt�l f�gg�en, azt�n az ellens�gek, j�t�kos, gombok, goly�k �s v�g�l a feliratok(high score, j�t�kos pontsz�ma.
	 * A f�ggv�ny legv�g�n pedig megn�zi a program hogy a user r�kattintott-e a men� vagy replay gombokra, �s ha igen,
	 * elv�gzi a k�v�nt m�veleteket.
	 */
	@Override
	public void render(float delta) {
		spacegame.update();
		clock.timer();
		clock.count();
		
		ScreenUtils.clear(0, 0, 0, 0);
		
		if (clock.getActual_Time() % spacegame.getEnemySpawnRate() == 0 && clock.getCount() % 60 == 0) {
			Enemy e = new Enemy(spacegame.getDifficulty());
			e.setImg(new Texture("enemy.png"));
			spacegame.enemies.add(e);
		}
		
		if (clock.getActual_Time() % spacegame.getShootRate() == 0 && clock.getCount() % 60 == 0) {
			if(spacegame.enemies.size() != 0) {
				spacegame.enemies.get(this.r.nextInt(0,spacegame.enemies.size())).setLohet(true);
			}
		}
		
		if (clock.getCount() % 60 == 0) {
			if(spacegame.enemies.size() != 0) {
				spacegame.enemies.get(this.r.nextInt(0,spacegame.enemies.size())).setIranytValthat(true);
			}
			if(spacegame.levonhat) {
				spacegame.setAvailableLives(spacegame.availableLives -1);
				spacegame.setlevonhat(false);
			}
			
		}
		
		clock.updateTimer();
		
		
		spacegame.batch.begin();
		
		int yhely = spacegame.getScreen_Height() - this.healthPointHeight;
		int[] xhelyek = {spacegame.getScreen_Width() - menu.getButtonWidth() - this.healthPointWidth * 1, spacegame.getScreen_Width() - menu.getButtonWidth() - this.healthPointWidth * 2, spacegame.getScreen_Width() - menu.getButtonWidth() - this.healthPointWidth * 3};
		for(int i = 0; i < spacegame.getAvailableLives(); i++) {
			spacegame.batch.draw(spacegame.jatekos.getImg(), xhelyek[i], yhely, this.healthPointWidth, this.healthPointHeight);
		}
		
		for (int x = 0; x < spacegame.enemies.size(); x++) {
			if(spacegame.enemies.get(x).getisAlive()) {
				spacegame.enemies.get(x).draw(spacegame.batch, spacegame.enemies.get(x).getImg());
			}
		}
		
		spacegame.jatekos.draw(spacegame.batch, spacegame.jatekos.getImg());
		menu.draw(spacegame.batch,spacegame.getScreen_Width() - menu.getButtonWidth(), spacegame.getScreen_Height() - menu.getButtonHeight());
		replay.draw(spacegame.batch,spacegame.getScreen_Width() - replay.getButtonWidth(), spacegame.getScreen_Height() - replay.getButtonHeight() * 2);
		
		for (int x = 0; x < spacegame.player_bullets.size(); x++) {
			spacegame.player_bullets.get(x).draw(spacegame.batch);
		}
		
		for (int x = 0; x < spacegame.enemy_bullets.size(); x++) {
			spacegame.enemy_bullets.get(x).draw(spacegame.batch);
		}
		
		spacegame.font.draw(spacegame.batch,String.valueOf(spacegame.jatekos.getCurrent_score()), 0 ,spacegame.getScreen_Height() - 10);
		spacegame.font.draw(spacegame.batch, "High Score: " + spacegame.high_score, 0 ,spacegame.getScreen_Height() - 30);
		
		if(menu.rakattint(this.menuButtonxHelyzet, this.menuButtonyHelyzet + menu.getButtonHeight(), this.menuButtonyHelyzet)) {
			spacegame.setScreen(new MenuScreen(spacegame));
			spacegame.restart(); 
		}
		
		if(replay.rakattint(this.replayButtonxHelyzet, this.replayButtonyHelyzet + replay.getButtonHeight(), this.replayButtonyHelyzet)) {
			spacegame.setScreen(new DifficultyScreen(spacegame));
			spacegame.restart();
		}
		
		spacegame.batch.end();
	}
	
	/**
	 * A tov�bbi be�p�tett f�ggv�nyek k�telez�en implent�land�ak, de nem k�telez� �ket felhaszn�lni.
	 * Nev�k egy�rtelm�s�ti funkci�iukat.
	 */

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
	}

	@Override
	public void show() {
	}
}
