package trafficSimulator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Simulator {

	public static void main(String[] args) {
		new Simulator().run();
	}

	public void run() {
		TrafficQueue noConstr = new NonConstructionTrafficQueue(15, 75);
		TrafficQueue constr = new ConstructionTrafficQueue(5, 40);

		Car c = null;
		int carCount = 1;
		int time = 0;
		boolean once = true;
		BufferedWriter pOut = null;

		int NUMBER_OF_CARS = 120;
		int CAR_FREQUENCY = 60; // seconds

		try {
			pOut = new BufferedWriter(new FileWriter("traffic_" + NUMBER_OF_CARS + "_" + CAR_FREQUENCY + ".csv"));
			pOut.write("name, ncIn, ncOut, cIn, cOut, speed, time\n");

			while (once || !noConstr.isEmpty() || !constr.isEmpty()) {
				time++;

				if (carCount < NUMBER_OF_CARS) {
					if ((time % CAR_FREQUENCY) == 0) {
						noConstr.push(new Car(carCount, time));
						carCount++;
						once = false;
					}
				}

				if (!constr.isFull()) {
					c = noConstr.pop(time);
					if (null != c) {
						c.ncOut = time;
						c.cIn = time;
						constr.push(c);
					}
				}

				c = constr.pop(time);
				if (null != c) {
					c.cOut = time;
					// log("exited Construction: " + c.toString());
					pOut.write(c.carName + ", " + c.ncIn + ", " + c.ncOut + ", " + c.cIn + ", " + c.cOut + ", "
							+ c.speed + ", " + (c.cOut - c.ncIn) + "\n");
				}
			}
		} catch (Exception e) {
			log(e.getMessage());
		} finally {
			if (pOut != null) {
				try {
					pOut.flush();
					pOut.close();
				} catch (IOException e) {
					log(e.getMessage());
				}
			}
		}

		System.out.println("Normal exit. " + carCount + " " + time);
	}

	private void log(String m) {
		System.out.println(m);
	}

}
