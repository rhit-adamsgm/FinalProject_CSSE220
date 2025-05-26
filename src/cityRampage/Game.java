package cityRampage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

public class Game {
	//fields----------------------------------------//
		//fields for startup------------------------//
	private String username;
	private boolean raidEnding;
	private StartScreen startScreen;
		//fields for general game architecture
	private Model model;
	private Controller controller;
    private int gameState;  // example state
    	//fields for map logic
    private City selectedCity;
    private MapViewer mapViewer;
    	//fields for CityViewer logic
    private CityViewer cityViewer;
    	//cities object - base game data
    private static BufferedImage atlBack,denverBack, kcBack, newyorkBack, oblockBack, seattleBack;
    private City chicago, denver, atl, seattle, newyork, kansascity;
	private ArrayList<City> cityArray = new ArrayList<>();
		//mechbot stats - base game data
	private double mechHealth;
	//----------------------------------------------//

	public static void main(String[] args) {
		new Game();
	}
	
	/**
	 * This is basically a main, but callable from within the game. It will restart the game.
	 */
	public Game() {
		model = new Model();
		//LOADDATA: loading in all the data like images and sounds
		loadAllCityBackgrounds();
		// Add cities to the array
		makeCitiesAndCityArray();
		//controller = new Controller(model, mapViewer);
		//Starts the game by opening the start screen
		startScreen = new StartScreen(() -> onStartScreenClose());
	}
	
	//Start screen stuff----------------------------------------------------------------------//
	/**
	 * This pushes the code to the true start of the game after the startup screen is closed
	 */
	private void onStartScreenClose() {
		username = startScreen.getReturnedUsername();
		mapViewer = new MapViewer(model, () -> onMapScreenClose(), cityArray);
	}
		
	//Map stuff-------------------------------------------------------------------------------//
	private void onMapScreenClose() {
		raidEnding = false;
		selectedCity = mapViewer.getSelectedCity();
		cityViewer = new CityViewer(selectedCity,() -> onCityRaidEnd());
	}
	
	//CityViewer stuff
	private void onCityRaidEnd() {
		// NOTE: city viewer owns it's own frame and can dispose that
		if (raidEnding) {
			return;
		}
		raidEnding = true;
		
		cityViewer = null;

		SwingUtilities.invokeLater(() -> {
			if (mapViewer != null) {
				mapViewer.showFrame();
			} 
			raidEnding = false;
		});
	}
	
	
	//Data loading to start the game----------------------------------------------------------//
	/**
	 * ensures: All the background images for the different cities are available
	 * 
	 */
	private void loadAllCityBackgrounds() {
		//atlanta background
		try {
			atlBack = ImageIO.read(new File("src/images/atlanta_background.jpg"));
		} catch (IOException e) {
			System.err.println("Caught: " + e.getMessage());
			e.printStackTrace();
			atlBack = null;
		}
		
		//denver background
		try {
			denverBack = ImageIO.read(new File("src/images/denver_background.png"));
		} catch (IOException e) {
			System.err.println("Caught: " + e.getMessage());
			e.printStackTrace();
			denverBack = null;
		}
		
		//kansas city background
		try {
			kcBack = ImageIO.read(new File("src/images/kansascity_background.jpg"));
		} catch (IOException e) {
			System.err.println("Caught: " + e.getMessage());
			e.printStackTrace();
			kcBack = null;
		}
		
		//new york background
		try {
			newyorkBack = ImageIO.read(new File("src/images/new-york.jpeg"));
		} catch (IOException e) {
			System.err.println("Caught: " + e.getMessage());
			e.printStackTrace();
			newyorkBack = null;
		}
		
		//chicago background
		try {
			oblockBack = ImageIO.read(new File("src/images/oblock_background.jpg"));
		} catch (IOException e) {
			System.err.println("Caught: " + e.getMessage());
			e.printStackTrace();
			oblockBack = null;
		}
		
		//seattle background
		try {
			seattleBack = ImageIO.read(new File("src/images/seattle-streets-21.jpg"));
		} catch (IOException e) {
			System.err.println("Caught: " + e.getMessage());
			e.printStackTrace();
			seattleBack = null;
		}
	}

	private void makeCitiesAndCityArray() {
		chicago = new Chiraq(1000, 300, oblockBack);
		denver = new Denver(550, 325, denverBack);
		atl = new Atlanta(1150, 500, atlBack);
		seattle = new Seattle(250, 50, seattleBack);
		newyork = new NewYork(1350, 245, newyorkBack);
		kansascity = new KansasCity(750, 550, kcBack);
		cityArray.add(denver);
		cityArray.add(chicago);
		cityArray.add(atl);
		cityArray.add(seattle);
		cityArray.add(newyork);
		cityArray.add(kansascity);
	}
}
