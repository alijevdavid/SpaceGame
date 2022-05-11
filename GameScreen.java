package space.shooter.xx;
import java.util.Random;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * Ez a class egy screen class, melyben megjelntetik a játék.
 * @author Alijev Dávid
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
	 * Az osztály konstruktorában definiálódik sok változó, amelyek ezen a képernyõn jelennek meg, történnek.
	 * Ilyenek a gombok (ezek méretei és helye), a timer amely másodpercenként számol felfele, és egy randomizáló class
	 * @param spacegame
	 * Ezzel megkapja a fõ classot, melyben vannak a Spriteok(rajzoláshoz, kiíráshoz), metódusok,
	 * és függvények, melyeket az osztálynak ismernie kell a megfekõ mûködéshez.
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
	 * Ez a render metódus, melyben a kirajzolás és a vizsgálatok folynak.
	 * Legelsõnek meghívódik a SpaceGame class update metódusa, aztán elindul a timer.
	 * A következõkben megnézi a program, hogy hol jár a timer és milyen milyen nehézségen vagyunk (spawnrate és shootrate) és annak megfeleõ 
	 * gyakorisággal jelenteti meg az ellenfeleket és lõnek azok. Itt történik a random irányváltása az ellenségeknek is bizonyos idõközönként.
	 * Ezek után történik az összes kirajzolás és kiíratás ami ezen a screenen történik.
	 * Elsõ az életerõpontok kirajzolása a kiválasztott nehézségtõl függõen, aztán az ellenségek, játékos, gombok, golyók és végül a feliratok(high score, játékos pontszáma.
	 * A függvény legvégén pedig megnézi a program hogy a user rákattintott-e a menü vagy replay gombokra, és ha igen,
	 * elvégzi a kívánt mûveleteket.
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
	 * A további beépített függvények kötelezõen implentálandóak, de nem kötelezõ õket felhasználni.
	 * Nevük egyértelmüsíti funkcióiukat.
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
