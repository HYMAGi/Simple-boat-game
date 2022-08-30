public class Player {
	//attributes
	private String username;
	private Boat boat;
	private int turn = 0;
	
	//constructor
	public Player(String username) {
		this.username = username;
		boat = new Boat();
	}
	
	//getters and setters
	public String getUsername() {
		return username;
		}
	
	public int getBoatLocation() {
		return boat.getLocation();
	}
	
	public int getTurn() {
		return turn;
	}
	
	//other methods
	public void moveBoat(int steps) {
		boat.move(steps);
	}
	
	public void addOneTurn() {
	    turn += 1;
	}
}