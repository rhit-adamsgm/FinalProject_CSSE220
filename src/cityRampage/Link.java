package cityRampage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import java.lang.Math;

public class Link {
	//fields-----------------------------------//
	private double[][] vertices, pins;
	private double[] r = new double[2];
	private double t;
	private java.awt.image.BufferedImage image;
	private int sizeX, sizeY, gapX, gapY; //'gap' is the gap in between the (0,0) origin and the top left corner of the image
	private double scaleFactor;
	//-----------------------------------------//
	
	public Link(double[][] givenVertices, double[][] givenPins, BufferedImage givenImage) {
		//take in the local vertice info, copied
		int vertn = givenVertices[0].length;
		vertices = new double[2][vertn];
		System.arraycopy(givenVertices[0], 0, vertices[0], 0, vertn);
		System.arraycopy(givenVertices[1], 0, vertices[1], 0, vertn);
		//take in the local pin info, copied
		int pinn = givenPins[0].length;
		pins = new double[2][pinn];
		System.arraycopy(givenPins[0], 0, pins[0], 0, pinn);
		System.arraycopy(givenPins[1], 0, pins[1], 0, pinn);
		//image scaling data and saving image
		calcSize();
		image = givenImage;
		//set r and t
		t = 0;
		r[0] = 0;
		r[1] = 0;
		//OLD
		//r[0] = imageSize[2] + sizeX/2; //calculate the middle of the image as the coordinates
		//r[1] = imageSize[3] + sizeY/2;
	}
	
	public Link(double[][] givenVertices, double[][] givenPins, BufferedImage givenImage, double scaleFactor) {
		//take in the local vertice info, copied
		int vertn = givenVertices[0].length;
		vertices = new double[2][vertn];
		System.arraycopy(givenVertices[0], 0, vertices[0], 0, vertn);
		System.arraycopy(givenVertices[1], 0, vertices[1], 0, vertn);
		//take in the local pin info, copied
		int pinn = givenPins[0].length;
		pins = new double[2][pinn];
		System.arraycopy(givenPins[0], 0, pins[0], 0, pinn);
		System.arraycopy(givenPins[1], 0, pins[1], 0, pinn);
		//scale the link to what we want it---------------------------------//
		this.scaleFactor = scaleFactor;
		scaleSize(this.scaleFactor);
		//-----------------------------------------------------------------//
		//image scaling data and saving image
		calcSize();
		image = givenImage;
		//set r and t
		t = 0;
		r[0] = 0;
		r[1] = 0;
		//------------QUESTION THIS HARD---------------------------------------------------------//
		//OLD
		//r[0] = imageSize[2] + sizeX/2; //calculate the middle of the image as the coordinates
		//r[1] = imageSize[3] + sizeY/2;
	}
	
	//GETTERS----------------------------------------------------------//
	public double[] getr() {
		return r;
	}
	
	public double getX() {
		return r[0];
	}
	
	public double getY() {
		return r[1];
	}
	
	public double gett() {
		return t;
	}
	
	private double getPinX(int i) {
		return pins[0][i];
	}
	
	private double getPinY(int i) {
		return pins[1][i];
	}
	
	private double getVertX(int i) {
		return vertices[0][i];
	}
	
	private double getVertY(int i) {
		return vertices[1][i];
	}
	
	//SETTERS----------------------------------------------------------//
	public void setr(double[] givenr) {
		r = givenr;
	}
	
	public void setX(double x) {
		r[0] = x;
	}
	
	public void setY(double y) {
		r[1] = y;
	}
	
	public void sett(double givent) {
		t = givent;
	}
	
	//SHIFTERS---------------------------------------------------------//
	public void shiftX(double deltaX) {
		r[0] =+ r[0] + deltaX;
	}
	
	public void shiftY(double deltaY) {
		r[1] =+ r[1] + deltaY;
	}
	
	public void shiftt(double deltat) {
		t =+ t + deltat;
	}
	
	//DRAW-------------------------------------------------------------//
	public void draw(Graphics2D g2) {
		AffineTransform oldTransform = g2.getTransform();  // Save current transform
		
		g2.translate(r[0], r[1]); // Translate to the desired center of the image
		
		g2.rotate(t); // Rotate the image to the desired angle
		
		g2.drawImage(image, gapX, gapY, sizeX, sizeY, null); // Draw the image starting at the top left corner of the bounding box, with desired size
		
		//g2.drawImage(image, -sizeX/2, -sizeY/2, sizeX, sizeY, null);
		//DEBUG______________________________//
		if (pins[0].length == 1) {
			g2.setColor(Color.RED);
			g2.drawOval((int)(getPinX(0)-2.5), (int)(getPinY(0)-2.5), 5, 5);
		}
		if (pins[0].length == 2) {
			g2.setColor(Color.BLUE);
			g2.drawOval((int)getPinX(0)-5, (int)getPinY(0)-5, 10, 10);
			g2.fillOval((int)getPinX(1)-5, (int)getPinY(1)-5, 10, 10);
		}
		if (pins[0].length == 3) {
			g2.setColor(Color.GREEN);
			g2.drawOval((int)getPinX(0)-5, (int)getPinY(0)-5, 10, 10);
			g2.drawOval((int)getPinX(1)-5, (int)getPinY(1)-5, 10, 10);
			g2.drawOval((int)getPinX(2)-5, (int)getPinY(2)-5, 10, 10);
		}
		//MORE DEBUG - hit boxes
		g2.drawRect(gapX, gapY, sizeX, sizeY);
		//MORE INFO! - local link origins
		g2.drawLine(0, 7, 0, -7);//y coord. line
		g2.drawLine(7, 0, -7, 0);//x coord. line
		//___++++++++++++++++++++++++++++____________________________________===============//
		
		g2.setTransform(oldTransform); // Restore transform
	}
	
	//CALCULATION GETTERS----------------------------------------------//
	/**
	 * This function calculates the global location of a pin
	 * @param i is the pin index you want to find
	 * @return the global coordinates of the pin in question
	 */
	public double[][] getPinLoc(int i) {
		double[][] org2Pin = getOrg2Pin(i);
		return new double[][] { {org2Pin[0][0]+r[0]}, {org2Pin[1][0]+r[1]} };
	}
	
	/**
	 * This function calculates the vector from the link origin to the pin
	 * @param i is the pin you want
	 * @return the vector from the link origin to the pin
	 */
	public double[][] getOrg2Pin(int i) {
		double xComp = getPinX(i)*Math.cos(t) - getPinY(i)*Math.sin(t);
		double yComp = getPinX(i)*Math.sin(t) + getPinY(i)*Math.cos(t);
		return new double[][] {{xComp},{yComp}};
	}
	
	/**
	 * This function will calculate the size of the image, setting several important fields for drawing the link
	 * sizeX and sizeY size the image
	 * gapX and gapY tell where the top left corner of the image needs to be relative to its mathematical origin
	 */
	public void calcSize() {
		double minX = getVertX(0);
		double minY = getVertY(0);
		double maxX = getVertX(0);
		double maxY = getVertY(0);
		for (int i = 1;i < vertices[0].length;i++) {
			if (getVertX(i) < minX) {
				minX = getVertX(i);
			}
			if (getVertX(i) > maxX) {
				maxX = getVertX(i);
			}
			if (getVertY(i) < minY) {
				minY = getVertY(i);
			}
			if (getVertY(i) > maxY) {
				maxY = getVertY(i);
			}
		}
		this.sizeX = (int)Math.round(maxX-minX);
		this.sizeY = (int)Math.round(maxY-minY);
		this.gapX = (int)minX;
		this.gapY = (int)-(sizeY + minY); //because the Y the user enters the pins as is opposite direction of graphics y
	}
	
	//OTHER----------------------------------------------//
		public void scaleSize(double scaleFactor) {
			for (int i = 0;i < vertices[0].length;i++) {
				this.vertices[0][i] =  this.vertices[0][i]*scaleFactor;
				this.vertices[1][i] =  this.vertices[1][i]*scaleFactor;
			}
			for (int i = 0;i < pins[0].length;i++) {
				this.pins[0][i] =  this.pins[0][i]*scaleFactor;
				this.pins[1][i] =  this.pins[1][i]*scaleFactor;
			}
		}
	
	
	

}
