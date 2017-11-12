package trafficSimulator;

public class Simulator {

    public static void main(String[] args) {
        new Simulator().run();
    }

    public void run() {
        TrafficQueue noConstr = new TrafficQueue(false, 15, 75);
        TrafficQueue constr = new TrafficQueue(true, 5, 40);

        int hours = 1;
        Car c = null;
        int carCount = 0;
        
        for (int time = 0; time < hours * 60 * 60; time += 5) {
            if ((time % 10) == 0) {
            		carCount ++;
                c = new Car();
                c.ncIn = time;
                noConstr.push(c);
            }

            c = noConstr.pop(time);
            if (null != c){
                c.ncOut = time;
                c.cIn = time;
                constr.push(c);
            }

            c = constr.pop(time);
            if (null != c){
                c.cOut = time;
                log(c);
            }
        }
        System.out.println("Normal exit. " + carCount);
    }

    private void log(Car c){
        System.out.println(c.toString());
    }
    class Car {
        int ncIn = -1;
        int ncOut = -1;
        int cIn = -1;
        int cOut = -1;
        public String toString() {
        		return 	
        				"ncIn  :" + ncIn + "\n" + 
        				"ncOut :" + ncOut+ "\n" +
        				"cIn   :" + cIn + "\n" + 
        				"cOut  :" + cOut + "\n" + 
        				"TOTAL :" + (cOut - ncIn);
        }
    }

    class TrafficQueue {
        private Car queue[];
        private int top = 0;
        private int bottom = 0;
        private double distance = 0;
        private double speed = 0;
        private Double size = 0.0;
        private boolean construction = false;

        public TrafficQueue(boolean construction, double distance, double speed) {
            this.construction = construction;
            this.distance = distance;
            this.speed = speed;
            size = ((distance / speed) * 60.0 * 12.0);
            this.queue = new Car[size.intValue() + 1];

        }

        public void push(Car car) {
            if (bottom == (top + 1)) {
                log("Push failed, queue full.");
            }
            if (top > size) top = 0;
            queue[top++] = car;
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
            if (construction) secondsOnQueue = time - c.cIn;
            
            double secondsUntilDone = (distance / actualSpeed()) * 60 * 60;
            if (secondsOnQueue >= secondsUntilDone){
                bottom++;
                
                return c;
            } else {
                return null;
            }
        }

        private Double actualSpeed() {
            if (!construction) {
                return speed;
            } else {
                if (pFull() < 0.5) {
                    return speed;
                } else if (pFull() < 0.75) {
                    return speed * 0.75;
                } else if (pFull() < 0.9) {
                    return speed * 0.5;
                } else {
                    return speed * 0.25;
                }
            }
        }

        private double pFull() {
        	double ret = -1.0;
            if (top == bottom) {
                ret = 0.0;
            } else if (bottom < top) {
                ret = ((double) top - (double) bottom) / size;
            } else {
                ret = (top + (size - bottom)) / size;
            }
            System.out.println(ret);
            return ret;
        }

        private void log(String msg) {
            System.out.println(msg);
        }
    }

}
