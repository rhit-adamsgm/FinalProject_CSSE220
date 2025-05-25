package cityRampage;

import java.awt.image.BufferedImage;

public class NewYork extends City{

	public NewYork(int xMapCoord, int yMapCoord, BufferedImage backImage) {
		super(xMapCoord, yMapCoord, backImage);
		name = "New York";
		nickname = "The Big Apple";
		maxEnemies = 16;
		enemyInterval = 4000; //4 seconds
	}

}
