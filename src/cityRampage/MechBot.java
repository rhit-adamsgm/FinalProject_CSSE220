package cityRampage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;

public class MechBot extends Linkage {
	private Link torso, legF1, legF2, legB1, legB2, footF, footB;
	private Constraint torso2F1, torso2B1, F12F2, B12B2, F22footF, B22footB;
	private BufferedImage torsoImage, legImage, footImage;
	private double loopSpot = 0;
	private double homeX, homeY;
	private double scaleFactor;
	private double health;
	
	public MechBot(double homeX, double homeY, double scaleFactor) {
		//Set starting position and scale--------------------------------------------//
		this.homeX = homeX;
		this.homeY = homeY;
		this.scaleFactor = scaleFactor;
		//-----------------------------------------------------------------------------------------------//
		//Define Torso Link------------------------------------------------------------------------------//
		double[][] torsoVerts = new double[][] { {0, 39, 39, 40.5, 36.75, 30, 1.5, 0, -3.75, -3.75, -1.5},
												{0, 0,  21,  26.25, 29.25,  27,  9,   3, 2.25,  -1.8,  -1.8} };
		double[][] torsoPins = new double[][] { {3,   28.5, 36},
												{-1.5, -1.5, -26.5} };
		try {
			torsoImage = ImageIO.read(new File("src/images/TorsoLinkImage.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		torso = new Link(torsoVerts, torsoPins, torsoImage, scaleFactor);
		//-----------------------------------------------------------------------------------------------//
		//Define Leg Links-------------------------------------------------------------------------------//
		double[][] legVerts = new double[][] { {0, 0,   21,   21},
											   {0, -4.5, -4.5, 0} };
		double[][] legPins = new double[][] { {1.5,  20},
											  {2.25, 2.25} };
	    try {
	  		legImage = ImageIO.read(new File("src/images/LegLinkImage.png"));
	   	} catch (IOException e) {
		   	// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    legF1 = new Link(legVerts, legPins, legImage, scaleFactor);
	    legF2 = new Link(legVerts, legPins, legImage, scaleFactor);
	    legB1 = new Link(legVerts, legPins, legImage, scaleFactor);
	    legB2 = new Link(legVerts, legPins, legImage, scaleFactor);
	    //-----------------------------------------------------------------------------------------------//
	    //Define Leg Links-------------------------------------------------------------------------------//
  		double[][] footVerts = new double[][] { {0, 15, 15, 9, 6, 0},
  											    {0, 0, 3, 6, 6, 3} };
  		double[][] footPins = new double[][] { {7.5},
  											   {-4.5} };
  	    try {
  	  		footImage = ImageIO.read(new File("src/images/FootLinkImage.png"));
  	   	} catch (IOException e) {
  		   	// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  	    footF = new Link(footVerts, footPins, footImage, scaleFactor);
  	    footB = new Link(footVerts, footPins, footImage, scaleFactor);
  	    //-----------------------------------------------------------------------------------------------//
  	    //Add Constraints--------------------------------------------------------------------------------//
  	    torso2F1 = new PinConstraint(torso, legF1, 2, 1);
  	    torso2B1 = new PinConstraint(torso, legB1, 1, 1);
  	    F12F2 = new PinConstraint(legF1, legF2, 2, 1);
  	    B12B2 = new PinConstraint(legB1, legB2, 2, 1);
  	    F22footF = new PinConstraint(legF2, footF, 2, 1);
  	    B22footB = new PinConstraint(legB2, footB, 2, 1);
  	    //-----------------------------------------------------------------------------------------------//
  	    //Put Links and Constraints in lists-------------------------------------------------------------//
  	    this.links.add(torso);
  	    this.links.add(legF1);
  	    this.links.add(legF2);
  	    this.links.add(legB1);
  	    this.links.add(legB2);
  	    this.links.add(footF);
  	    this.links.add(footB);
  	    constraints.add(torso2F1);
  	    constraints.add(torso2B1);
  	    constraints.add(F12F2);
  	    constraints.add(B12B2);
  	    constraints.add(F22footF);
  	    constraints.add(B22footB);
  	    //-----------------------------------------------------------------------------------------------//
  	    //Place the torso Link---------------------------------------------------------------------------//
  	    torso.setX(homeX);
  	    torso.setY(homeY);
  	    //-----------------------------------------------------------------------------------------------//
	}
	
	public void walk() {
		if (loopSpot > 100) loopSpot = 0;
		//10*Math.sin((loopSpot/100)*Math.PI*2)
		torso.setX(homeX - 15*Math.sin(Math.PI/2 + (loopSpot/100)*Math.PI*2)); //small, slow cycles
		//torso.setY(homeY - 15*Math.sin((loopSpot/100)*Math.PI*2));
		//(Math.PI/4)*Math.sin(loopSpot/100*Math.PI)
		legF1.sett( Math.PI*(loopSpot/100) );
		legF2.sett(Math.PI*(loopSpot/50));
		legB1.sett(Math.PI*(loopSpot/100));
		
		enforceConstaintsInOrder();
		loopSpot++;
	}
	
	/**
	 * This makes the mech fall if the feet aren't on the ground
	 * @param groundLevel is the y-level of the ground
	 */
	public void physics(int groundLevel) {
		//Get info------------------------------------------------------------------------------------//
		double fy = footF.getY();
		double by = footB.getY();
		//Gravity and falling-------------------------------------------------------------------------//
		if (fy < groundLevel && by < groundLevel) {   //both off the ground
			torso.shiftY(5);
		}
		if (fy < groundLevel && by >= groundLevel) { //front off of the ground
			torso.shiftt(Math.PI/180);
		}
		if (fy >= groundLevel && by < groundLevel) { //back off of the ground
			torso.shiftt(-Math.PI/180);
		}
		//Don't let feet go past the ground-----------------------------------------------------------//
		if (fy > groundLevel) footF.setY(groundLevel);
		if (by > groundLevel) footB.setY(groundLevel);
		
		enforceConstaintsInOrder();
	}
	
	//Getters-----------------------------------------------------//
	public double getHealth() {return health;}
	
	//Setters-----------------------------------------------------//
	public void setHealth(double health) {this.health = health;}
	
	//Effectors---------------------------------------------------//
	public void doDamage(double damage) {health =- damage;}
	public void doHeal(double heal) {health =+ heal;}
	
}
