package cityRampage;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class EnemyPerson extends Enemy {
	
	public EnemyPerson(int xstart, int ystart) {
		super();
		direction = -1;
		xpos = xstart;  // Start from right side
        ypos = ystart;
        sizeX = 50;
        sizeY = 80;
        speed = 10.0; // Movement speed in pixels per frame
	}
	
	public void imageInit() {
		try {
            image1 = ImageIO.read(new File("src/images/enemy_frame1.png"));
            image2 = ImageIO.read(new File("src/images/enemy_frame2.png"));
            image3 = ImageIO.read(new File("src/images/enemy_frame3.png"));
            image4 = ImageIO.read(new File("src/images/enemy_frame4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
