import java.util.Scanner;

public class Game {
	//attributes
	private Player player1;
	private Player player2;
	private River river;
    private Dice dice;
	private Scoreboard scoreboard;
	
	//constructors
	public Game(Dice dice, Scoreboard scoreboard) {
        this.dice = dice;
		this.scoreboard = scoreboard;
	}
	
	//other methods
	public void start() {
		Scanner input = new Scanner(System.in);
        scoreboard.loadScore();
		while(true) {
			System.out.println("Select an option:\n1. Play Game\n2. View High Scores\n3. Exit");
			String userSelection = input.next();
			switch(userSelection) {
				case "1":
					playGame();
					break;
				case "2":
					//display scoreboard
                    scoreboard.displayScoreboard();
					break;
				case "3":
					System.exit(0);
					break;
				default:
					System.out.println("Please enter a valid option");
			}
		}
	}

	private void playGame() {
		Scanner input = new Scanner(System.in);
		int playerToRollDice = 1;
		int numberOfStepsForward;
		RiverItem encounteredRiverItem;
		Player winner;
		//get player names
		System.out.printf("Enter name for player 1 (Less than 10 characters and no whitespaces): ");
		String playerOneName = input.next();
		player1 = new Player(playerOneName);
		System.out.printf("\nEnter name for player 2 (Less than 10 characters and no whitespaces): ");
		String playerTwoName = input.next();
		player2 = new Player(playerTwoName);
		
		//get number of river items and create river
		System.out.printf("\nEnter the number of river items (Less than 100): ");
		int numberOfRiverItems = input.nextInt();
        //this reads the carriage return inputted along with the integer to prevent automatically rolling the dice for player 1.
        input.nextLine();
		river = new River(numberOfRiverItems);
		
		//a switch (playerToRollDice) is used to alternate dice rolling between both players. Loop exits when a player's boat reaches position 100
		while(player1.getBoatLocation()!=100 && player2.getBoatLocation()!=100) {
			//show track
            river.visualizeTrack(player1.getBoatLocation(), player2.getBoatLocation());
            
			if(playerToRollDice==1) {
				System.out.println("Player 1 to roll the dice (Press 'enter' to roll)");
				input.nextLine();
				numberOfStepsForward = dice.roll();
				System.out.printf("You rolled a %d! Moving forward by %d steps\n\n", numberOfStepsForward, numberOfStepsForward);
				player1.moveBoat(numberOfStepsForward);
				checkForRiverItemAndMoveAccordingly(player1);
				player1.addOneTurn();
				playerToRollDice = 2;
			}
			else if(playerToRollDice==2) {
				System.out.println("Player 2 to roll the dice (Press 'enter' to roll)");
				input.nextLine();
				numberOfStepsForward = dice.roll();
				System.out.printf("You rolled a %d! Moving forward by %d steps\n\n", numberOfStepsForward, numberOfStepsForward);
				player2.moveBoat(numberOfStepsForward);
				checkForRiverItemAndMoveAccordingly(player2);
				player2.addOneTurn();
				playerToRollDice = 1;
			}
		}
		
		//Congratulate winner
		winner = playerToRollDice==1?player2:player1;
		System.out.printf("Congratulations %s, you have won the game!\n\n", winner.getUsername());
		
		//update scoreboard
        scoreboard.storeScore(winner.getUsername(), winner.getTurn());
	}
	
    private void checkForRiverItemAndMoveAccordingly(Player player) {
		int indexOfRiverItemInArrayList;
		RiverItem encounteredRiverItem;
        //indexOfRiverItemInArrayList is -1 if there are no river items in that position.
        indexOfRiverItemInArrayList = river.checkLocationForRiverItem(player.getBoatLocation());
        //Move the boat if there is a river item in the position.
        //The boat will not move again if the next position still has a river item. This is to avoid jumping 
        //back and forth between a trap and current infinitely.
        if(indexOfRiverItemInArrayList != -1) {
            encounteredRiverItem = river.getRiverItem(indexOfRiverItemInArrayList);
            if(encounteredRiverItem instanceof Trap) {
                System.out.printf("You hit a trap! Moving backwards by %d steps.\n\n", encounteredRiverItem.getPower());
                player.moveBoat(encounteredRiverItem.getPower()*-1);
            }
            else {
                System.out.printf("You hit a current! Moving forward by %d steps.\n\n", encounteredRiverItem.getPower());
                player.moveBoat(encounteredRiverItem.getPower());
            }
        }
    }
}