package cityRampage;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;


import javax.imageio.ImageIO;

public class Emperor {
	BufferedImage emperor;
	public int health = 100;
	
	public Emperor() {
		imageinit();
	}
	
	
	public void imageinit() {
		try {
			emperor = ImageIO.read(new File("src/images/emperor_image.png"));
		}catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public void reduceHealth(int amount) {
		health = Math.max(amount, health - amount);
	}
	
	public int getHealth() {
		return health;
	}
}
