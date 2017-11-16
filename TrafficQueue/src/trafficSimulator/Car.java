package trafficSimulator;

class Car {
    int ncIn = -1;
    int ncOut = -1;
    int cIn = -1;
    int cOut = -1;
    public String toString() {
    		return 	
    				"ncIn  :" + ncIn + " " + 
    				"ncOut :" + ncOut+ " " +
    				"cIn   :" + cIn + " " + 
    				"cOut  :" + cOut + " " + 
    				"TOTAL :" + (cOut - ncIn);
    }
}