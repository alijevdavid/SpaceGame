package space.shooter.xx;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Tester {
	
	private SpaceGame spacegame;
	private Enemy enemy;
	private Shoot shoot;
	
	
	@Before
	public void before() {
		spacegame = new SpaceGame();
		spacegame.setAvailableLives(2);
		spacegame.setDifficulty(2);
		spacegame.setBulletSpeed(spacegame.getDifficulty() * 10);
		enemy = new Enemy(spacegame.getDifficulty());
		shoot = new Shoot(enemy, -1, spacegame.bulletSpeed);
		
	}
	
	@Test
	public void testgetScreen_Height() {
		//Arrange
		int expected = 1000;
		
		//Act
		int actual = spacegame.getScreen_Height();
		
		//Assert
		assertEquals(expected, actual);
	}
	
	@Test
	public void testgetScreen_Width() {
		//Arrange
		int expected = 800;
		
		//Act
		int actual = spacegame.getScreen_Width();
		
		//Assert
		assertEquals(expected, actual);
	}
	
	@Test
	public void hardElet() {
		//Arrange
		boolean expected = false;
		
		//Act
		boolean actual = spacegame.levonhat;
		
		//Assert
		assertEquals(expected, actual);
	}
	
	@Test
	public void testLevontaE() {
		//Arrange
		int expected = 1;
		
		//Act
		int actual = spacegame.getAvailableLives();
		
		//Assert
		if(spacegame.levonhat) {
			assertEquals(expected, actual);
		}
	}
	
	@Test
	public void EnemySebessegtest() {
		//Arrange
		int expected = 10;
		
		//Act
		int actual = enemy.getSebesseg();
		
		//Assert
		assertEquals(expected, actual);
	}
	
	@Test
	public void EnemyMerettest() {
		//Arrange
		int expected = 37;
		
		//Act
		int actual = enemy.getHajo_meret();
		
		//Assert
		assertEquals(expected, actual);
	}
	
	@Test
	public void EnemyHelyzettest() {
		//Arrange
		boolean expected = true;
		
		//Act
		boolean actual = enemy.getHajoPosY() > spacegame.getScreen_Height() / 2;
		
		//Assert
		assertEquals(expected, actual);
	}
	
	@Test
	public void BulletSpeedtest() {
		//Arrange
		int expected = shoot.getSebesseg();
		
		//Act
		int actual = spacegame.getDifficulty() * 10;
		
		//Assert
		assertEquals(expected, actual);
	}
	
	@Test
	public void BulletPalyanKivultest() {
		//Arrange
		boolean expected = false;
		
		//Act
		boolean actual = shoot.getBulletPosY() > spacegame.getScreen_Height();
		
		//Assert
		assertEquals(expected, actual);
	}
	
	@Test
	public void BulletMegfMerettest() {
		//Arrange
		int expected = 10;
		
		//Act
		int actual = shoot.getMeret();
		
		//Assert
		assertEquals(expected, actual);
	}
	
	

}
