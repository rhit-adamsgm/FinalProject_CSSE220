package cityRampage;

import java.util.ArrayList;
import java.util.List;

public class City {
	int x;
	int y;
	double scale;
	List<City> cityMarkers = new ArrayList<>();
	
	
	public City(int x, int y, double scale) {
		this.x = x;
		this.y = y;
		this.scale = scale;
	}
}
