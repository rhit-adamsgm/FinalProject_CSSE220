package cityRampage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.awt.Graphics2D;

import javax.imageio.ImageIO;

public class MechBot extends Linkage {
	private Graphics2D g2;
	private Link torso, legF1, legF2, legB1, legB2, footF, footB;
	private BufferedImage torsoImage, legImage, footImage;
	public MechBot(Graphics2D g2) {
		//Import Graphics package------------------------------------------------------------------------//
		this.g2 = g2;
		//-----------------------------------------------------------------------------------------------//
		//Define Torso Link------------------------------------------------------------------------------//
		double[][] torsoVerts = new double[][] { {0, 13, 13, 13.5, 12.25, 10, 0.5, 0, -1.25, -1.25, -0.5},
												{0, 0,  7,  8.75, 9.75,  9,  3,   1, 0.75,  -0.6,  -0.6} };
		double[][] torsoPins = new double[][] { {1,   9.5, 11},
												{0.5, 0.5, 9} };
		try {
			torsoImage = ImageIO.read(new File("src/images/TorsoLinkImage.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		torso = new Link(torsoVerts, torsoPins, torsoImage, g2);
		//-----------------------------------------------------------------------------------------------//
		//Define Leg Links-------------------------------------------------------------------------------//
		double[][] legVerts = new double[][] { {0, 0,   7,   7},
											   {0, 1.5, 1.5, 0} };
		double[][] legPins = new double[][] { {-0.5,  7.5},
											  {-0.75, -0.75} };
	    try {
	  		legImage = ImageIO.read(new File("src/images/LegLinkImage.png"));
	   	} catch (IOException e) {
		   	// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    legF1 = new Link(legVerts, legPins, legImage, g2);
	    legF2 = new Link(legVerts, legPins, legImage, g2);
	    legB1 = new Link(legVerts, legPins, legImage, g2);
	    legB2 = new Link(legVerts, legPins, legImage, g2);
	    //-----------------------------------------------------------------------------------------------//
	    //Define Leg Links-------------------------------------------------------------------------------//
  		double[][] footVerts = new double[][] { {0, 5, 5, 3, 2, 0},
  											    {0, 0, 1, 2, 2, 1} };
  		double[][] footPins = new double[][] { {2.5},
  											   {1.5} };
  	    try {
  	  		footImage = ImageIO.read(new File("src/images/FootLinkImage.png"));
  	   	} catch (IOException e) {
  		   	// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  	    footF = new Link(footVerts, footPins, footImage, g2);
  	    footB = new Link(footVerts, footPins, footImage, g2);
  	    //-----------------------------------------------------------------------------------------------//
	}
}
