package cityRampage;

public class RelAngleConstraint extends Constraint {
	private Link Link1; //Link that determines other angle
	private Link Link2; //Link that has angle driven
	private double theta0; //angle difference

	public RelAngleConstraint(Link Link1, Link Link2, double theta0) {
		super(Link1, Link2);
		this.theta0 = theta0;
	}

	@Override
	public void enactConstraint() {
		Link2.sett(Link1.gett() + theta0);
	}
	
	public void setAngleDif(double theta0) {
		this.theta0 = theta0;
	}

}
