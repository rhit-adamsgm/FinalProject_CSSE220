package cityRampage;

import java.util.ArrayList;
import java.util.List;

public class City {
	private int xMapCoord, yMapCoord;
	private String name, nickname;
	
	String scale;
	ArrayList<Integer> cityMarkers = new ArrayList<>();
	
	
	public City(int xMapCoord, int yMapCoord, String name, String nickname) {
		this.xMapCoord = xMapCoord;
		this.yMapCoord = yMapCoord;
		this.name = name;
		this.nickname = nickname;
	}
	
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
}
