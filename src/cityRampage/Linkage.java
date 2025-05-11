package cityRampage;

import java.util.ArrayList;

public abstract class Linkage {
	private ArrayList<Link> links;
	private ArrayList<Constraint> constraints; //all constraints, in order of succession
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
	
	public void draw() {
		for (int i = 0;i < links.size();i++) {
			links.get(i).draw();
		}
	}
	
	
	
}
