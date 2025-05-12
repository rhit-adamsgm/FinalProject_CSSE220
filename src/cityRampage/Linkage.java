package cityRampage;

import java.util.ArrayList;
import java.awt.Graphics2D;

public abstract class Linkage {
	protected ArrayList<Link> links = new ArrayList<>();
	protected ArrayList<Constraint> constraints = new ArrayList<>(); //all constraints, in order of succession
	//this will likely be all PinConstraints, but could be more
	
	
	public void addLink(Link link) {
		links.add(link);
	}
	
	public void addConstraint(Constraint constraint) {
		constraints.add(constraint);
	}
	
	public void enforceConstaintsInOrder() {
		for (int i = 0;i < constraints.size();i++) {
			constraints.get(i).enactConstraint();
		}
	}
	
	public void draw(Graphics2D g2) {
		for (int i = 0;i < links.size();i++) {
			links.get(i).draw(g2);
		}
	}
	
	public void scaleSize(double scaleFactor) {
		for (int i = 0;i < links.size();i++) {
			links.get(i).scaleSize(scaleFactor);
		}
	}
	
	
	
}
