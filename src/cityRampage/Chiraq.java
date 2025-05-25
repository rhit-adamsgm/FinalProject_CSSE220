package cityRampage;

import java.awt.image.BufferedImage;

public class Chiraq extends City{

	public Chiraq(int xMapCoord, int yMapCoord, BufferedImage backImage) {
		super(xMapCoord, yMapCoord, backImage);
		name = "Chiraq";
		nickname = "King Von Lurks";
		maxEnemies = 63;
		enemyInterval = 6000; //6 seconds
	}
	
}
