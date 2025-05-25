package cityRampage;

import java.awt.image.BufferedImage;

public class Seattle extends City{

	public Seattle(int xMapCoord, int yMapCoord, BufferedImage backImage) {
		super(xMapCoord, yMapCoord, backImage);
		name =  "Seattle";
		nickname = "The Land of Blue Hair";
		maxEnemies = 30;
		enemyInterval = 10000; //10 seconds
	}

}
