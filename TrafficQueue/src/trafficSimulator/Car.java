package trafficSimulator;

class Car {
    
	int carName = -1;
	int ncIn = -1;
    int ncOut = -1;
    int cIn = -1;
    int cOut = -1;
    double speed = 0.0;
    public Car(int name, int in){
    	this.carName = name;
    	this.ncIn = in;
    }
    public String toString() {
    		return 	
    				"carName : "+ carName + " " +
    				"ncIn    :" + ncIn + " " + 
    				"ncOut   :" + ncOut+ " " +
    				"cIn     :" + cIn + " " + 
    				"cOut    :" + cOut + " " + 
    				"speed   :" + speed + " " + 
    				"TOTAL   :" + (cOut - ncIn);
    }
}