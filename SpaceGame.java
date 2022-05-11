package space.shooter.xx;

import java.awt.Rectangle;

import java.util.ArrayList;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * A fõclass amely a libgdx könyvtár Game osztályából (Application Adapter) származik. Innen jönnek a spriteok
 * és az egész program fõ változói.
 * @author Alijev Dávid
 */
public class SpaceGame extends Game {
	
	SpriteBatch batch;
	BitmapFont font;
	
	private int screen_width;
	private int screen_height;
	
	PlayerShip jatekos;
	FileHandler filehandler;
	
	
	ArrayList<Shoot> player_bullets;
	ArrayList<Shoot> enemy_bullets;
	ArrayList<Enemy> enemies;
	
	private Sound explosion;
	private Music music;
	
	
	int difficulty;
	int shootRate;
	int enemySpawnRate;
	int high_score;
	int bulletSpeed;
	int availableLives;
	
	boolean lost;
	boolean levonhat;
	
	Rectangle player_bulletHitbox;
	Rectangle enemy_bulletHitbox;
	Rectangle enemyHitbox;
	Rectangle playerHitbox;
	
	/**
	 * Ez az osztály konstruktora, itt definiálódik az magassága és szélessége,
	 * a filekezelõ a lövedékek listái, a boolean változó, amely megadja, hogy a játekos veszírtett-e vagy nem. a high score, és
	 * feljebb az attribútumoknál látható még jópár használandó változó.
	 */
	public SpaceGame() {
		this.screen_width = 800;
		this.screen_height = 1000;
		
		this.filehandler = new FileHandler("C:\\Users\\alida\\OneDrive\\Asztali gép\\NFH\\assets\\high_score.txt");
		
		this.player_bullets = new ArrayList<Shoot>();
		this.enemy_bullets = new ArrayList<Shoot>();
		
		this.enemies = new ArrayList<Enemy>();
		this.lost = false;
		this.levonhat = false;
		
		this.high_score = filehandler.Read();
	}
	
	/**
	 * Újraindítja a játékot úgy, hogy kitörli az enemy, és a bullet listákat
	 * és lenullázza a játékos jelenlegi pontszámát
	 */
	public void restart() {
		enemies.clear();
		enemy_bullets.clear();
		player_bullets.clear();
		jatekos.setCurrent_score(0);
	}
	
	/**
	 * Setter a lost boolean értékhez
	 * @param x
	 * Megadja hogy mire állítódjon a lost érték.
	 */
	public void setLost(boolean x) {
		this.lost = x;
	}
	
	/**
	 * @return
	 * Visszaadja a fennmaradó életpontokat.
	 */
	public int getAvailableLives() {
		return this.availableLives;
	}
	
	/**
	 * Beállítja, hány életerõpontja legyen a játékosnak.
	 * @param x
	 * Erre állítódik be az életerõpontok száma.
	 */
	public void setAvailableLives(int x) {
		this.availableLives = x;
	}
	
	public void setlevonhat(boolean x) {
		this.levonhat= true;
	}
	
	/**
	 * @return
	 * Visszaadja a képernyõ magasságát.
	 */
	public int getScreen_Height() {
		return this.screen_height;
	}
	
	/**
	 * @return
	 * Visszaadja a képernyõ szélességét.
	 */
	public int getScreen_Width() {
		return this.screen_width;
	}
	
	/**
	 * Beállítja globálisan a golyók sebességét.
	 * @param x
	 * Erre az értékre állítja a golyók sebességét.
	 */
	public void setBulletSpeed(int x) {
		this.bulletSpeed = x;
	}
	
	/**
	 * @return
	 * Visszaadja a játék nehézségét.
	 */
	public int getDifficulty() {
		return this.difficulty;
	}
	
	/**
	 *Beállítja a játék nehézségét.
	 * @param x
	 * Ez az érték, amire beállítódik
	 */
	public void setDifficulty(int x) {
		this.difficulty = x;
	}
	
	/**
	 * Beállítja azt a lövési gyakoriságot, amellyel az enemyk dolgoznak
	 * @param x
	 * Erre az értékre állítódik be
	 */
	public void setShootRate(int x) {
		this.shootRate = x;
	}
	
	/**
	 * @return
	 * Visszaadja azt a lövési gyakoriságot, amellyel az enemyk dolgoznak
	 */
	public int getShootRate() {
		return this.shootRate;
	}
	
	/**
	 * Beállítja azt a gyakoriságot, amellyel az Enemy classok létrejönnek.
	 * @param x
	 * Erre az értékre állítódik be
	 */
	public void setEnemySpawnRate(int x) {
		this.enemySpawnRate = x;
	}
	
	/**
	 * @return
	 * Visszaadja azt a gyakoriságot, amellyel az Enemy classok létrejönnek.
	 */
	public int getEnemySpawnRate() {
		return this.enemySpawnRate;
	}
	
	/**
	 * Ebben a metódusban jönnek létre az objektumok, itt allítódik be a háttérzene, és a betûszín.
	 */
	@Override
	public void create () {
		this.setScreen(new MenuScreen(this));
		jatekos = new PlayerShip();
		batch = new SpriteBatch();
		font = new BitmapFont();
		explosion = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
		music = Gdx.audio.newMusic(Gdx.files.internal("music.ogg"));
		music.setVolume(1f);
		music.setLooping(true);
		music.play();
		
		font.setColor(Color.GREEN);
	}
	/**
	 * Meghívja az õsosztály render metódusát, enélkül nem mûködne a képernyõváltás.
	 */
	public void render () {
		super.render();
	}
	
	/**
	 * Ebben a metódusban frissül a játék. Elõször is for ciklusokkal vegigmegy a listákon és folyamatpsan frissít minden mozgó 
	 * dolgot a képrenyõn. Figyeli a hitboxokat, amelyek ha összeérnek, elvégzi a megfelõ utasításokat(életet von le, eltünteti az adott enemyt), beállítja a lövési lehetõségeket.
	 * 2 billentyû aktivitását is nézi, a space billenytû lenyomásakor lõ egyet a játékos, az escappel pedig kilép.
	 * Ha a játékosnak nem maradt élete, akkor veszített és visszadobódik a fõmenübe.
	 */
	public void update() {
		jatekos.update();
		
		for(int x = 0; x < enemies.size(); x++) {
			if(enemies.get(x).getLohet()) {
				enemy_bullets.add(new Shoot(enemies.get(x), -1, this.bulletSpeed));
				enemies.get(x).setLohet(false);
			}
			enemies.get(x).update();
		}
		for(int x = 0; x < enemies.size(); x++) {
			for (int i = 0; i < player_bullets.size(); i++) {  
				
				enemyHitbox = enemies.get(x).getBounds();
				player_bulletHitbox = player_bullets.get(i).getBounds();
				
				if(enemyHitbox.contains(player_bulletHitbox) || player_bulletHitbox.intersects(enemyHitbox.getX(), enemyHitbox.getY(), enemyHitbox.getWidth(), enemyHitbox.getHeight())) {
					long id = explosion.play(0.2f);
					explosion.setPitch(id, 2);
					explosion.setLooping(id, false);
					
					jatekos.increaseCurrent_score(10);
					player_bullets.remove(i);  
					enemies.get(x).setisAlive(false);
				}
			}
		}
		
		for(int x = 0; x < enemies.size(); x++) {
			for (int i = 0; i < enemy_bullets.size(); i++) { 
				playerHitbox = jatekos.getBounds();
				enemy_bulletHitbox = enemy_bullets.get(i).getBounds();
				if(playerHitbox.contains(enemy_bulletHitbox) && enemy_bullets.get(i).getBulletPosX() < this.screen_height) {
					//setAvailableLives(this.availableLives -1);
					setlevonhat(true);
				}
			}
		}
		
		for (int x = 0; x < player_bullets.size(); x++) {
			player_bullets.get(x).update();
			if (player_bullets.get(x).getBulletPosY() > screen_height) {
				player_bullets.remove(x);
			}
		}
		
		for (int x = 0; x < enemy_bullets.size(); x++) {
			enemy_bullets.get(x).update();
		}
		
		if(high_score < jatekos.getCurrent_score()) {
			high_score = jatekos.getCurrent_score();
			filehandler.Update(high_score);
		}
		
		if(this.availableLives < 0) {
			restart();
			this.lost = true;
			this.setScreen(new MenuScreen(this));
			
		}
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			Shoot s = new Shoot(jatekos, 1, this.bulletSpeed);
			s.setImg( new Texture("bullet.png"));
			player_bullets.add(s);
		}
			
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
	}
}
