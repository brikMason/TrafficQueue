package trafficSimulator;

class ConstructionTrafficQueue extends TrafficQueue {

	public ConstructionTrafficQueue(double distance, double speed) {
		super(distance, speed);
		size = 30;
		this.queue = new Car[size];

	}
	
	public Car pop(int time) {

		if (bottom%size == top%size) {
			return null;
		}
		if (bottom < 0 || bottom >= queue.length) {
			bottom = 0;
		}

		Car c = queue[bottom];
		double secondsOnQueue = time - c.cIn;
		double aSpeed = actualSpeed();
		
		double secondsUntilDone = (distance / aSpeed) * 60 * 60;
		if (secondsOnQueue >= secondsUntilDone) {
			queue[bottom] = null;
			bottom++;
			c.speed = aSpeed;
			return c;
		} else {
			return null;
		}
	}

	protected double actualSpeed() {
		double aSpeed = -1.0d;
		double percentFull = pFull();
		
		if (percentFull < 0.5) {
			aSpeed = speed;
		} else if (percentFull < 0.75) {
			aSpeed = speed * 0.75;
		} else if (percentFull < 0.9) {
			aSpeed = speed * 0.5;
		} else {
			aSpeed = speed * 0.25;
		}
		
		return aSpeed;
	}
}