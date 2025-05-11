package cityRampage;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.lang.Math;

public class Link {
	//fields-----------------------------------//
	private double[][] vertices, pins;
	private double[] r;
	private double t;
	private java.awt.image.BufferedImage image;
	private Graphics2D g2;
	//-----------------------------------------//
	
	public Link(double[][] givenVertices, double[][] givenPins, BufferedImage givenImage, Graphics2D giveng2) {
		//take in the local vertice and pin info, save that raw
		vertices = givenVertices;
		pins = givenPins;
		//save the graphics package
		g2 =giveng2;
		//scale the image to the size it should be PER the given vertice info
		double[] imageSize = calcSize();
		image = new BufferedImage((int)imageSize[0],(int)imageSize[1],givenImage.getType()); //take a 2nd look at the image stuff later
		
		//set r and t
		t = 0;
		r = new double[2];
		r[0] = imageSize[2] + imageSize[0]/2; //calculate the middle of the image in local coordinates
		r[1] = imageSize[3] + imageSize[1]/2;
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
	}
	
	//DRAW-------------------------------------------------------------//
	public void draw() {
		
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
		for (int i = 1;i < pins[0].length;i++) {
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
		return new double[] {Math.round(maxX-minX), Math.round(maxY-minY), minX, minY};
	}
	
	//CALCULATION SETTERS----------------------------------------------//
	public void setOrgFromPin(double[][] inputPin) {
		
	}
	
	
	
	
	

}
