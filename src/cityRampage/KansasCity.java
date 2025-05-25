package cityRampage;

import java.awt.image.BufferedImage;

public class KansasCity extends City {

	public KansasCity(int xMapCoord, int yMapCoord, BufferedImage backImage) {
		super(xMapCoord, yMapCoord, backImage);
		name = "Kansas City";
		nickname = "Chief's Nation";
		maxEnemies = 22;
		enemyInterval = 10000; //10 seconds
		
	}
	
	
}
