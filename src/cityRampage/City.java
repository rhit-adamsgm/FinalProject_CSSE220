package cityRampage;

import java.util.ArrayList;
import java.util.List;

public class City {
	int x;
	int y;
	double scale;
	ArrayList<Integer> cityMarkers = new ArrayList<>();
	
	
	public City(int x, int y, double scale) {
		this.x = x;
		this.y = y;
		this.scale = scale;
	}
}
