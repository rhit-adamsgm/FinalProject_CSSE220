package cityRampage;

public class PinConstraint extends Constraint {
	private Link Link1; //position determining link
	private Link Link2; //other link
	private int pin1; //pin on Link1
	private int pin2; //pin on Link2

	public PinConstraint(Link Link1, Link Link2, int pin1, int pin2) {
		super(Link1, Link2);
		this.pin1 = pin1;
		this.pin2 = pin2;
	}

	@Override
	public void enactConstraint() {
		double[][] pinPos = Link1.getPinLoc(pin1); //this is where the pins should be based on Link1
		double[][] org2PinLink2 = Link2.getOrg2Pin(pin2);
		double x2 = pinPos[0][0] - org2PinLink2[0][0]; //x position of Link 2
		double y2 = pinPos[0][1] - org2PinLink2[0][1]; //y position of Link 2
		Link2.setr(new double[] {x2,y2});
	}

}
