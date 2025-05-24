package cityRampage;

import java.awt.image.BufferedImage;

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
	
	private void setImage() {
		switch (frame) {
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		}
	}
	
	public void draw() {
		setImage();
	}
	
}