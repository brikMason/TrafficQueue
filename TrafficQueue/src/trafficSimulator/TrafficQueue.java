package trafficSimulator;

abstract class TrafficQueue {
	protected Car queue[];
	protected int top = 0;
	protected int bottom = 0;
	protected Double size = 0.0;
	protected double distance = 0;
	protected double speed = 0;
	
	
	public TrafficQueue(double distance, double speed) {
		this.distance = distance;
		this.speed = speed;

	}

	public void push(Car car) {
		if (queue.length <= top) {
			log("Push failed, queue full.");
		}
		if (top >= size)
			top = 0;
		queue[top++] = car;
	}

	abstract protected Car pop(int time);

	abstract protected Double actualSpeed();

	protected double pFull() {
		double ret = -1.0;
		if (top == bottom) {
			ret = 0.0;
		} else if (bottom < top) {
			ret = ((double) top - (double) bottom) / size;
		} else {
			ret = (top + (size - bottom)) / size;
		}
		//System.out.println(ret);
		return ret;
	}

	protected void log(String msg) {
		System.out.println(msg);
	}
}