package cityRampage;

import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

public abstract class City {
	private Runnable timerCallback;
	private int xMapCoord, yMapCoord;
	protected String name, nickname;
	private int damageFrameCounter = 0;
	BufferedImage image;
	private CityViewer cityViewer;
	//MechBot------------------//
	MechBot mechBot;
	//Emperor-----------------//
	Emperor emperor;
	//Enemies------------------//
	ArrayList<Enemy> enemies = new ArrayList<>(); //this ArrayList holds all of the current, on screen enemies
	//Game play fields---------//
	Timer timer;
	protected int frameRate, spawnCounter, numEnemiesSpawned;
	protected int maxEnemies, enemyInterval; //max enemies that are to be spawned, interval to spawn them at
	protected int frameHeight, frameWidth;
	protected int groundY;
	protected int emperorX = 0;
	protected int emperorY = 490;
	

	public City(int xMapCoord, int yMapCoord, BufferedImage backImage) {
		this.xMapCoord = xMapCoord;
		this.yMapCoord = yMapCoord;
		this.image = backImage;
	}
	
	public void setCityViewer(CityViewer viewer) {
		this.cityViewer = viewer;
	}
	
	
	//Game run functions (not dormant)-------------//
	public void startRaid() {
		//
		//initialize MechBot instance
		mechBot = new MechBot(400,frameHeight/2,3);
		//initialize emperor instance
		emperor = new Emperor();
		//enemy data, start with an enemy
		numEnemiesSpawned = 0;
		spawnEnemyPerson();
		//TIMER: start timer stuff-------------------------------------------------------------------
		spawnCounter = 0;
		frameRate = 100; // milliseconds
		timer = new Timer(frameRate, e -> {
			if (cityViewer != null && !cityViewer.isPaused()) {
			spawnCounter++;
			update();
			}
			timerCallback.run();
		});
		timer.start();
		//-------------------------------------------------------------------------------------------
	}
	
	public void endRaid() {
		timer.stop();
	}
	
	public void passKeyPress2Mech(KeyEvent e) {
		System.out.println("KeyEvent Registered in City");
		if (cityViewer != null && !cityViewer.isPaused()) {
		mechBot.respond(e);
		}
	}
	
	private void update() {
		//update and spawn in new enemies
		int enemiesAtEmperor = 0;
		for (Enemy enemy : enemies) {
			if (enemy.xpos <= emperorX + 70) {
				enemy.speed = 0;
				enemiesAtEmperor++;
			} else {
				enemy.update();
			}
		}
		
		if (enemiesAtEmperor > 0) {
			damageFrameCounter++;
			if (damageFrameCounter >= 10) {
				int totalDamage = 5 * enemiesAtEmperor;
				emperor.reduceHealth(totalDamage);
				cityViewer.updateHealth(emperor.getHealth());
				
				if (emperor.getHealth() <= 0) {
					cityViewer.endRaid();
					return;
				}
				
				damageFrameCounter = 0;
			}
		} else {
			damageFrameCounter = 0;
		}
		
		if (spawnCounter >= enemyInterval/frameRate && numEnemiesSpawned < maxEnemies) {
			spawnCounter = 0;
			spawnEnemyPerson();
		}
		//MechBot operation
		mechBot.handleMovement(groundY);
		
	}
	

	//specific in the abstract class for now
	private void spawnEnemyPerson() {
		EnemyPerson enemy = new EnemyPerson(frameWidth, groundY-72);
		enemy.setSize(40, 72);
		enemies.add(enemy);
		numEnemiesSpawned++;
		System.out.println("Enemy Spawned: " + numEnemiesSpawned);
	}
	
	//Getters--------------------------------------//
	public int getXMapCoord() {
		return xMapCoord;
	}
	
	public int getYMapCoord() {
		return yMapCoord;
	}
	
	public String getname() {
		return name;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	//Setters---------------------------------------//
	public void setFrameSize(int width, int height) {
		frameWidth = width;
		frameHeight = height;
		adjustToFrameSize();
	}
	
	public void setTimerCallback(Runnable function) {
		timerCallback = function;
	}
	//Graphics--------------------------------------//
	public void draw(Graphics2D g2) {
		mechBot.draw(g2);
		g2.drawImage(emperor.emperor, emperorX, emperorY, 125, 200, null);
		for (Enemy enemy: enemies) {
			enemy.draw(g2);
		}
	}
	private void adjustToFrameSize() {
		groundY = frameHeight - 5;
		System.out.println("Drawing Frame Height is " + frameHeight);
		System.out.println("groundY is " + groundY);
	}
}
