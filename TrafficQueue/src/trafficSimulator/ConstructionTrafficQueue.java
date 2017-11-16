package trafficSimulator;

class ConstructionTrafficQueue extends TrafficQueue {

	public ConstructionTrafficQueue(double distance, double speed) {
		super(distance, speed);
		size = ((distance / speed) * 60.0 * 60.0 );
		size = 26.0;
		this.queue = new Car[26];

	}

	public Car pop(int time) {

		if (bottom == top) {
			// log("Pop failed, queue empty.");
			return null;
		}
		if (bottom < 0 || bottom >= queue.length) {
			bottom = 0;
		}

		Car c = queue[bottom];
		double secondsOnQueue = time - c.cIn;

		double secondsUntilDone = (distance / actualSpeed()) * 60 * 60;
		if (secondsOnQueue >= secondsUntilDone) {
			bottom++;

			return c;
		} else {
			return null;
		}
	}

	protected Double actualSpeed() {
		Double aSpeed = -1.0d;
		Double percentFull = pFull();
		
		if (percentFull < 0.5) {
			aSpeed = speed;
		} else if (percentFull < 0.75) {
			aSpeed = speed * 0.75;
		} else if (percentFull < 0.9) {
			aSpeed = speed * 0.5;
		} else {
			aSpeed = speed * 0.25;
		}
		
		System.out.println(aSpeed + ":"+ percentFull);
		
		return aSpeed;
	}
}