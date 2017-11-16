package trafficSimulator;

public class Simulator {

    public static void main(String[] args) {
        new Simulator().run();
    }

    public void run() {
        TrafficQueue noConstr = new NonConstructionTrafficQueue(15, 75);
        TrafficQueue constr = new ConstructionTrafficQueue(5, 20);

        int hours = 2;
        Car c = null;
        int carCount = 0;
        
        for (int time = 0; time < hours * 60 * 60; time += 1) {
          //  if ((time % 2) == 0) {
            		carCount ++;
                c = new Car();
                c.ncIn = time;
                noConstr.push(c);
            //}

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

}
