//This is the driver class of the program
public class GameTest {

	public static void main(String[] args) {
		Scoreboard scoreboard = new Scoreboard();
        Dice dice = new Dice();
		Game game = new Game(dice, scoreboard);
		game.start();
	}

}
