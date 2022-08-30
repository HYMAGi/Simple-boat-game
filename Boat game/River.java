import java.util.ArrayList;
import java.util.Random;

public class River {
	//attributes
	private ArrayList<RiverItem> riverItems = new ArrayList<RiverItem>();
	
	//constructor
	public River(int numberOfRiverItems) {
		//to avoid having traps at the last position causing the boats to keep going back upon encountering the trap, river items are not placed at the last position
		int[] riverItemLocations = new Random().ints(1,100).distinct().limit(numberOfRiverItems).toArray();
		//a switch is used to alternate the placement of traps and currents to ensure that one is not significantly more than the other.
		boolean riverItemSwitch = true;
		for(int location:riverItemLocations) {
			if(riverItemSwitch == true) {
				riverItems.add(new Current(location));
				riverItemSwitch = false;
			}
			else {
				riverItems.add(new Trap(location));
				riverItemSwitch = true;
			}
		}
	}
	
	//other methods
	public int checkLocationForRiverItem(int location) {
		//this method returns the index of the river item in the riverItems ArrayList that is located at a particular location on the river.
		//If none are found, -1 will be returned.
		for(int i=0; i<riverItems.size();i++) {
			if(riverItems.get(i).getLocation() == location) {
				return i;
			}
		}
		return -1;
	}

    public RiverItem getRiverItem(int index) {
        return riverItems.get(index);
    }

    public void visualizeTrack(int playerOneBoatPosition, int playerTwoBoatPosition) {
		String riverCurb = " " + "=".repeat(100);
		
		//lay out the track without the players' boats
		ArrayList<String> riverVisualization = new ArrayList<String>();
		for(int i=0; i<=100; i++) {
			riverVisualization.add(" ");
		}
		for(RiverItem riverItem:riverItems) {
			if(riverItem instanceof Trap) {
				riverVisualization.set(riverItem.getLocation(), "T");
			}
			else {
				riverVisualization.set(riverItem.getLocation(), "C");
			}
		}
		
		//insert players' boats
		riverVisualization.set(playerOneBoatPosition, "1");
		riverVisualization.set(playerTwoBoatPosition, "2");
		
		//visualize the track
		System.out.println(riverCurb);
		for(String position:riverVisualization) {
			System.out.print(position);
		}
		System.out.print("\n"+riverCurb+"\n");
    }
}
