package cityRampage;

import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import javax.swing.Timer;

public abstract class City {
	private Runnable timerCallback;
	private int xMapCoord, yMapCoord;
	protected String name, nickname;
	BufferedImage image;
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
	
	
	public City(int xMapCoord, int yMapCoord, BufferedImage backImage) {
		this.xMapCoord = xMapCoord;
		this.yMapCoord = yMapCoord;
		this.image = backImage;;
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
			spawnCounter++;
			update();
			timerCallback.run();
		});
		timer.start();
		//-------------------------------------------------------------------------------------------
	}
	
	public void endRaid() {
		timer.stop();
	}
	
	private void update() {
		//update and spawn in new enemies
		for (Enemy enemy : enemies) {
			enemy.update();
		}
		if (spawnCounter >= enemyInterval/frameRate && numEnemiesSpawned < maxEnemies) {
			spawnCounter = 0;
			spawnEnemyPerson();
		}
		//MechBot operation
		mechBot.walk();
		mechBot.physics(groundY);
		
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
		g2.drawImage(emperor.emperor, 0, 490, 125, 200, null);
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
