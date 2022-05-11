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
 * A f�class amely a libgdx k�nyvt�r Game oszt�ly�b�l (Application Adapter) sz�rmazik. Innen j�nnek a spriteok
 * �s az eg�sz program f� v�ltoz�i.
 * @author Alijev D�vid
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
	 * Ez az oszt�ly konstruktora, itt defini�l�dik az magass�ga �s sz�less�ge,
	 * a filekezel� a l�ved�kek list�i, a boolean v�ltoz�, amely megadja, hogy a j�tekos vesz�rtett-e vagy nem. a high score, �s
	 * feljebb az attrib�tumokn�l l�that� m�g j�p�r haszn�land� v�ltoz�.
	 */
	public SpaceGame() {
		this.screen_width = 800;
		this.screen_height = 1000;
		
		this.filehandler = new FileHandler("C:\\Users\\alida\\OneDrive\\Asztali g�p\\NFH\\assets\\high_score.txt");
		
		this.player_bullets = new ArrayList<Shoot>();
		this.enemy_bullets = new ArrayList<Shoot>();
		
		this.enemies = new ArrayList<Enemy>();
		this.lost = false;
		this.levonhat = false;
		
		this.high_score = filehandler.Read();
	}
	
	/**
	 * �jraind�tja a j�t�kot �gy, hogy kit�rli az enemy, �s a bullet list�kat
	 * �s lenull�zza a j�t�kos jelenlegi pontsz�m�t
	 */
	public void restart() {
		enemies.clear();
		enemy_bullets.clear();
		player_bullets.clear();
		jatekos.setCurrent_score(0);
	}
	
	/**
	 * Setter a lost boolean �rt�khez
	 * @param x
	 * Megadja hogy mire �ll�t�djon a lost �rt�k.
	 */
	public void setLost(boolean x) {
		this.lost = x;
	}
	
	/**
	 * @return
	 * Visszaadja a fennmarad� �letpontokat.
	 */
	public int getAvailableLives() {
		return this.availableLives;
	}
	
	/**
	 * Be�ll�tja, h�ny �leter�pontja legyen a j�t�kosnak.
	 * @param x
	 * Erre �ll�t�dik be az �leter�pontok sz�ma.
	 */
	public void setAvailableLives(int x) {
		this.availableLives = x;
	}
	
	public void setlevonhat(boolean x) {
		this.levonhat= true;
	}
	
	/**
	 * @return
	 * Visszaadja a k�perny� magass�g�t.
	 */
	public int getScreen_Height() {
		return this.screen_height;
	}
	
	/**
	 * @return
	 * Visszaadja a k�perny� sz�less�g�t.
	 */
	public int getScreen_Width() {
		return this.screen_width;
	}
	
	/**
	 * Be�ll�tja glob�lisan a goly�k sebess�g�t.
	 * @param x
	 * Erre az �rt�kre �ll�tja a goly�k sebess�g�t.
	 */
	public void setBulletSpeed(int x) {
		this.bulletSpeed = x;
	}
	
	/**
	 * @return
	 * Visszaadja a j�t�k neh�zs�g�t.
	 */
	public int getDifficulty() {
		return this.difficulty;
	}
	
	/**
	 *Be�ll�tja a j�t�k neh�zs�g�t.
	 * @param x
	 * Ez az �rt�k, amire be�ll�t�dik
	 */
	public void setDifficulty(int x) {
		this.difficulty = x;
	}
	
	/**
	 * Be�ll�tja azt a l�v�si gyakoris�got, amellyel az enemyk dolgoznak
	 * @param x
	 * Erre az �rt�kre �ll�t�dik be
	 */
	public void setShootRate(int x) {
		this.shootRate = x;
	}
	
	/**
	 * @return
	 * Visszaadja azt a l�v�si gyakoris�got, amellyel az enemyk dolgoznak
	 */
	public int getShootRate() {
		return this.shootRate;
	}
	
	/**
	 * Be�ll�tja azt a gyakoris�got, amellyel az Enemy classok l�trej�nnek.
	 * @param x
	 * Erre az �rt�kre �ll�t�dik be
	 */
	public void setEnemySpawnRate(int x) {
		this.enemySpawnRate = x;
	}
	
	/**
	 * @return
	 * Visszaadja azt a gyakoris�got, amellyel az Enemy classok l�trej�nnek.
	 */
	public int getEnemySpawnRate() {
		return this.enemySpawnRate;
	}
	
	/**
	 * Ebben a met�dusban j�nnek l�tre az objektumok, itt all�t�dik be a h�tt�rzene, �s a bet�sz�n.
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
	 * Megh�vja az �soszt�ly render met�dus�t, en�lk�l nem m�k�dne a k�perny�v�lt�s.
	 */
	public void render () {
		super.render();
	}
	
	/**
	 * Ebben a met�dusban friss�l a j�t�k. El�sz�r is for ciklusokkal vegigmegy a list�kon �s folyamatpsan friss�t minden mozg� 
	 * dolgot a k�preny�n. Figyeli a hitboxokat, amelyek ha �ssze�rnek, elv�gzi a megfel� utas�t�sokat(�letet von le, elt�nteti az adott enemyt), be�ll�tja a l�v�si lehet�s�geket.
	 * 2 billenty� aktivit�s�t is n�zi, a space billenyt� lenyom�sakor l� egyet a j�t�kos, az escappel pedig kil�p.
	 * Ha a j�t�kosnak nem maradt �lete, akkor vesz�tett �s visszadob�dik a f�men�be.
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
