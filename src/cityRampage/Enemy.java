package cityRampage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public abstract class Enemy {
	int frame; //cycles in between 1 and 4 ONLY EVER
	int health;
	double speed, xpos, ypos; //speed is in pixels per frame
	double level;
	int sizeX, sizeY;
	BufferedImage image1, image2, image3, image4;
	BufferedImage image;
	public int direction;  // Start moving left (-1 for left, 1 for right)
	
	public Enemy() {
		this.frame = 1;
		imageInit();
	}
	
	private void nextFrame() {
		frame += 1;
		if (frame > 4) {
			frame = 1;
		}
	}

	public void update() {
		// Update position first
        xpos += speed * direction;
        
		nextFrame();
		setImage();
	}
	
	public abstract void imageInit();
	
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
	
	public void setSize(int width, int height) {
		sizeX = width;
		sizeY = height;
	}
	
	public void draw(Graphics2D g2) {
		AffineTransform oldTransform = g2.getTransform();  // Save current transform
		
		g2.translate(xpos, ypos); // Translate to the desired center of the image
		
		g2.drawImage(image, -sizeX/2, -sizeY/2, sizeX, sizeY, null);
		
		//DEBUG - hit boxes
		g2.setColor(Color.RED);
		g2.drawRect(-sizeX/2, -sizeY/2, sizeX, sizeY);
		
		g2.setTransform(oldTransform); // Restore transform
		
		System.out.println("enemy drawn");
		System.out.println("frame: " + frame + " x-y: (" + xpos + "," + ypos + ")");
		System.out.println("Height: " + sizeY + " Width: " + sizeX);
	}
	
}