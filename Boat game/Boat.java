public class Boat {
	//attributes
	private int location;
	
	//constructor
	public Boat() {
		location = 0;
	}
	
	//getters and setters
	public int getLocation() {
		return location;
	}
	
	//other methods
	public void move(int steps) {
		if(location+steps > 100) {
			location = 100;
		}
		else if (location+steps < 0) {
			location = 0;
		}
		else {
			location += steps ;
		}
	}
}