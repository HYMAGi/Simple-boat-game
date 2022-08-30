import java.util.*;
import java.io.*;

public class Scoreboard {
    //attributes
	private Scanner input;
	private Formatter output;
	private ArrayList<PlayerScore> playerScores = new ArrayList<PlayerScore>();
	
    //methods
	public void loadScore() {
		input = openInputFile("playerScores.txt");
		readPlayerScoreFile();
		closeInputFile(input);
	}
	
	public void storeScore(String newWinnerName, int newWinnerScore) {
		output = openOutputFile("playerScores.txt");
		writePlayerScoreFile(newWinnerName, newWinnerScore);
		closeOutputFile(output);
	}
	
	public Scanner openInputFile(String filename) {
		Scanner tempinput = null;
		try {
			File file = new File(filename);
			file.createNewFile();
			tempinput = new Scanner(new File(filename));
		}
		catch (FileNotFoundException fileNotFoundException) {
			System.err.println("Error opening file.");
			System.exit(1);
		}
		catch (NoSuchElementException ex) {
			System.out.println("File improperly formed.");
		} 
        catch (IOException e) {
			e.printStackTrace();
		}
		return tempinput;
	}
	
	public Formatter openOutputFile(String filename) {
		Formatter tempoutput = null;
		try {
			tempoutput = new Formatter(filename);
		}
		catch (FileNotFoundException fileNotFoundException) {
			System.err.println("Error opening file");
			System.exit(1);
		}
		catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return tempoutput;
	}
	
    public void closeInputFile(Scanner input) {
		if (input!=null)
			input.close();
	}
	
	public void closeOutputFile(Formatter output) {
		if (output != null) {
			output.close();
		}
	}

	public void readPlayerScoreFile() {
		try {			
			while (input.hasNext()) {
				PlayerScore prec = new PlayerScore();
				prec.setName(input.next());
				prec.setScore(input.nextInt());
				
				playerScores.add(prec);
			}
		}
		catch(NoSuchElementException elementException) {
			System.err.println("File improperly formed.");
			input.close();
			System.exit(1);
		}
		catch(IllegalStateException stateException) {
			System.err.println("Error reading from file.");
			System.exit(1);
		}
	}

	public void writePlayerScoreFile(String newWinnerName, int newWinnerScore) {
		PlayerScore prec = new PlayerScore();
		prec.setName(newWinnerName);
		prec.setScore(newWinnerScore);
		playerScores.add(prec);
		
		Collections.sort(playerScores);
		
		for (int k = 0; k < playerScores.size(); k++) {
			if (k == 5) {
				break;
			}
			output.format("%s %d\n", playerScores.get(k).getName(), playerScores.get(k).getScore());
		}
	}
	
	public void displayScoreboard() {
		input = openInputFile("playerScores.txt");
		try {	
			while (input.hasNext()) {
				PlayerScore prec = new PlayerScore();
				prec.setName(input.next());
				prec.setScore(input.nextInt());
				System.out.println(prec);
			}
            System.out.println();
		}
		catch(NoSuchElementException elementException) {
			System.err.println("File improperly formed.");
			input.close();
			System.exit(1);
		}
		catch(IllegalStateException stateException) {
			System.err.println("Error reading from file.");
			System.exit(1);
		}
	}
}