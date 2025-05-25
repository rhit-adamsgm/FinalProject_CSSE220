package cityRampage;

import java.awt.image.BufferedImage;

public class Denver extends City{

	public Denver(int xMapCoord, int yMapCoord, BufferedImage backImage) {
		super(xMapCoord, yMapCoord, backImage);
		name = "Denver";
		nickname = "The Hazy City";
		maxEnemies = 42;
		enemyInterval = 5000; //5 seconds
	}
	
}
