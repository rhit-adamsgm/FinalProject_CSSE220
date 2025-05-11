package cityRampage;

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
	private Graphics2D g2;
	private int sizeX;
	private int sizeY;
	//-----------------------------------------//
	
	public Link(double[][] givenVertices, double[][] givenPins, BufferedImage givenImage, Graphics2D giveng2) {
		//take in the local vertice and pin info, save that raw
		vertices = givenVertices;
		pins = givenPins;
		//save the graphics package
		g2 = giveng2;
		//image scaling data and saving image
		double[] imageSize = calcSize();
		sizeX = (int)imageSize[0];
		sizeY = (int)imageSize[1];
		image = givenImage;
		//set r and t
		t = 0;
		r[0] = imageSize[2] + sizeX/2; //calculate the middle of the image as the coordinates
		r[1] = imageSize[3] + sizeY/2;
	}
	
	//GETTERS----------------------------------------------------------//
	public double[] getr() {
		return r;
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
	
	public void sett(double givent) {
		t = givent;
		//debug-------------------//
		System.out.println(givent);
		//------------------------//
	}
	
	//DRAW-------------------------------------------------------------//
	public void draw() {
		AffineTransform oldTransform = g2.getTransform();  // Save current transform
		
		g2.translate(r[0], r[1]); // Translate to the desired center of the image
		
		g2.rotate(t); // Rotate the image to the desired angle
		
		g2.drawImage(image, -sizeX/2, -sizeY/2, sizeX, sizeY, null); // Draw the image starting at the top left corner of the bounding box, with desired size
		
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
		return new double[][] { {org2Pin[0][0]+r[0]}, {org2Pin[0][1]+r[1]} };
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
	 * This function will calculate the size of the image
	 * @return a double[] that is: (x size, y size)
	 */
	public double[] calcSize() {
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
		
		//debugging---------------//
		System.out.println(maxX-minX);
		System.out.println("MaxX: " + maxY);
		System.out.println("MinY: " + minY);
		System.out.println("MaxY: " + maxY);
		System.out.println("");
		System.out.println("VertX 3 " + getVertX(3));
		//------------------------//
		return new double[] {Math.round(maxX-minX), Math.round(maxY-minY), minX, minY};
	}
	
	
	
	
	

}
