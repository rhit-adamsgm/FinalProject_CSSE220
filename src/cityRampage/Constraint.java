package cityRampage;

public abstract class Constraint {
	protected Link Link1;
	protected Link Link2;
	
	public Constraint(Link Link1, Link Link2) {
		this.Link1 = Link1;
		this.Link2 = Link2;
	}
	
	public abstract void enactConstraint();
}
