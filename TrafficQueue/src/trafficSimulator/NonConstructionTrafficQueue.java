package trafficSimulator;

class NonConstructionTrafficQueue extends TrafficQueue {

	public NonConstructionTrafficQueue(double distance, double speed) {
		super(distance, speed);

		size = 120; 
		this.queue = new Car[size];
	}

	public Car pop(int time) {

		if (bottom%size == top%size) {
			// log("Pop failed, queue empty.");
			return null;
		}
		if (bottom < 0 || bottom >= queue.length) {
			bottom = 0;
		}

		Car c = queue[bottom];
		double secondsOnQueue = time - c.ncIn;

		double secondsUntilDone = (distance / actualSpeed()) * 60 * 60;
		if (secondsOnQueue >= secondsUntilDone) {
			bottom++;

			return c;
		} else {
			return null;
		}
	}

	protected double actualSpeed() {
		return speed;
	}
}