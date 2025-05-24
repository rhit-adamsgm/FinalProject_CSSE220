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
		//System.out.println("ECC=======================");
		
		double[][] pinPos = Link1.getPinLoc(pin1-1); //this is where the pins should be based on Link1
		//System.out.println("x pos of pin1: " + pinPos[0][0]);
		double[][] org2PinLink2 = Link2.getOrg2Pin(pin2-1);
		//System.out.println("x vec from pin2 to org2: " + (-org2PinLink2[0][0]));
		double x2 = pinPos[0][0] - org2PinLink2[0][0]; //x position of Link 2
		double y2 = pinPos[1][0] - org2PinLink2[1][0]; //y position of Link 2
		Link2.setr(new double[] {x2,y2});
		//System.out.println("Setting Link2 r to: " + x2 + " " + y2);
		//System.out.println("==================");

	}

}
