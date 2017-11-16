package trafficSimulator;

class NonConstructionTrafficQueue extends TrafficQueue {

	public NonConstructionTrafficQueue(double distance, double speed) {
		super(distance, speed);

		size = ((distance / speed) * 60.0 * 60.0 * 60.0);
		this.queue = new Car[size.intValue() + 1];
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
		double secondsOnQueue = time - c.ncIn;

		double secondsUntilDone = (distance / actualSpeed()) * 60 * 60;
		if (secondsOnQueue >= secondsUntilDone) {
			bottom++;

			return c;
		} else {
			return null;
		}
	}

	protected Double actualSpeed() {
		return speed;
	}
}