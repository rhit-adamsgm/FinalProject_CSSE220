package cityRampage;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class CodeTestClass {
	private static BufferedImage image;

	public static void main(String[] args) {
		try {
			image = ImageIO.read(new File("src/images/LegLinkImage.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JFrame frame = new JFrame("Test Frame");
		frame.setSize(new Dimension(1000,750));
		
		DrawingPanel drawingPanel = new DrawingPanel(image);
		frame.add(drawingPanel);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	}
}

class DrawingPanel extends JPanel {
	
	private BufferedImage image;
	private Timer baseUnitTimer;
	private MechBot odob;
	private Graphics2D g2;
	private int groundLevel;
	
	public DrawingPanel(BufferedImage image) {
		super();
		this.image = image;
		odob = new MechBot(500, 375, 3);
		groundLevel = 650; //level of the ground in the game
		
		int baseDelay = 64; //64 milliseconds will be the base frame rate of our game
		baseUnitTimer = new Timer(baseDelay, e -> handleBaseFrame(odob));
		baseUnitTimer.start();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g2 = (Graphics2D) g;
	    odob.draw(g2);
	    
	    g2.setColor(Color.DARK_GRAY);
	    g2.fillRect(0, groundLevel, getWidth(), getHeight()-groundLevel);
	    
	    
	    
	    
	    
	    /**
	    //Make the test link--------------------------//
	    double[][] verts1 = new double[][] { {0, 0, 140, 140},
	    									 {0, 30, 30, 0} };
	    double[][] pins1 = new double[][] { {-10, 150}, {14, 14} };
	    
	    
	    Link testLink = new Link(verts1, pins1, image, g2);
	    testLink.setr(new double[] {500,375});
	    testLink.draw();
	    double[] testLinkr = new double[] {500, 375};
	    for (int i=0;i<20;i++) {
	    	testLinkr[1] = 375 + i*10;
	    	testLink.setr(testLinkr);
	    	testLink.sett(i*Math.PI/12);
	    	testLink.draw();
	    }
	    */
	}
	
	private void handleBaseFrame(MechBot odob) {
		odob.walk();
		odob.physics(groundLevel);
		repaint();
		
	}
}
