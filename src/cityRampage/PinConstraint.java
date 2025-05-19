package cityRampage;

public class PinConstraint extends Constraint {
	private int pin1; //pin on Link1
	private int pin2; //pin on Link2

	public PinConstraint(Link Link1, Link Link2, int pin1, int pin2) {
		super(Link1, Link2);
		this.pin1 = pin1;
		this.pin2 = pin2;
	}

	@Override
	public void enactConstraint() {
		double[][] pinPos = Link1.getPinLoc(pin1-1); //this is where the pins should be based on Link1
		System.out.println(pinPos[0][0] + " " + pinPos[1][0]);
		double[][] org2PinLink2 = Link2.getOrg2Pin(pin2-1);
		double x2 = pinPos[0][0] - org2PinLink2[0][0]; //x position of Link 2
		double y2 = pinPos[1][0] - org2PinLink2[1][0]; //y position of Link 2
		Link2.setr(new double[] {x2,y2});
		System.out.println("enact constraint called");
		System.out.println("SHOULD BE: " + x2 + " " + y2);
		System.out.println("       IS: " + Link2.getr()[0] + " " + Link2.getr()[1]);
	}

}
