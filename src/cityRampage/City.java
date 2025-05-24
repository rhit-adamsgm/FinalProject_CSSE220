package cityRampage;

import java.util.ArrayList;
import java.util.List;

import java.awt.image.BufferedImage;

public class City {
	private int xMapCoord, yMapCoord;
	private String name, nickname;
	BufferedImage image;
	
	
	
	public City(int xMapCoord, int yMapCoord, String name, String nickname, BufferedImage backImage) {
		this.xMapCoord = xMapCoord;
		this.yMapCoord = yMapCoord;
		this.name = name;
		this.nickname = nickname;
		this.image = backImage;
		
	}
	
	//Getters--------------------------------------//
	public int getXMapCoord() {
		return xMapCoord;
	}
	
	public int getYMapCoord() {
		return yMapCoord;
	}
	
	public String getname() {
		return name;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public BufferedImage getImage() {
		return image;
	}
}
