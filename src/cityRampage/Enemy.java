package cityRampage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Enemy {
	int frame; //cycles in between 1 and 4 ONLY EVER
	int health;
	double speed, xpos, ypos;
	double level;
	BufferedImage image1, image2, image3, image4;
	BufferedImage image;
	
	public Enemy() {
		this.frame = 1;
	}
	
	private void nextFrame() {
		frame += 1;
		if (frame > 4) {
			frame = 1;
		}
	}

	
	public void update() {
		nextFrame();
		draw();
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
	
	private void setImage() {
		switch (frame) {
		case 1:
			image = image1;
			break;
		case 2:
			image = image2;
			break;
		case 3:
			image = image3;
			break;
		case 4:
			image = image4;
			break;
		}
	}
	
	public void draw() {
		setImage();
	}
	
}