import java.util.Random;

public class RiverItem {
    //attributes
    private int power;
    private int location;
    
    //constructor
    public RiverItem(int location) {
        this.location = location;
        power = new Random().nextInt(10) + 1;
    }

    //getters and setters
    public int getPower() {
        return power;
    }
    
    public int getLocation() {
    	return location;
    }
}