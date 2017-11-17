package trafficSimulator;

abstract class TrafficQueue {
	protected Car queue[];
	protected int top = 0;
	protected int bottom = 0;
	protected int size = 0;
	protected double distance = 0;
	protected double speed = 0;
	
	
	public TrafficQueue(double distance, double speed) {
		this.distance = distance;
		this.speed = speed;

	}

	public boolean isEmpty(){
		return top == bottom;
	}
	
	public boolean isFull(){
		return  (((top + 1)%size) == bottom);
	}
	
	public void push(Car car) {
		queue[top++] = car;
		top = top % size;
	}

	abstract protected Car pop(int time);

	abstract protected double actualSpeed();

	protected double pFull() {
		double ret = -1.0;
		if (top == bottom) {
			ret = 0.0;
		} else if (bottom < top) {
			ret = ((double) top - (double) bottom) / size;
		} else {
			ret = (top + (size - bottom)) / size;
		}

		return ret;
	}

	protected void log(String msg) {
		System.out.println("__"+msg);
	}
}