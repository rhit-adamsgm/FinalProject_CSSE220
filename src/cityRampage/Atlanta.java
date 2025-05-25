package cityRampage;

import java.awt.image.BufferedImage;

public class Atlanta extends City{

	public Atlanta(int xMapCoord, int yMapCoord, BufferedImage backImage) {
		super(xMapCoord, yMapCoord, backImage);
		name = "Atlanta";
		nickname = "Hawks in 5";
		maxEnemies = 12;
		enemyInterval = 3000; //3 seconds
	}

}
