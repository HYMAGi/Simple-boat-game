import java.util.*;
import java.io.*;

public class PlayerScore implements Comparable<PlayerScore> {
    //attributes
	private String name;
	private int score;

    //setters and getters
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}

    //other methods
	@Override
	public String toString() {
		return String.format("%s %d", name, score);
	}

	@Override
	public int compareTo(PlayerScore o) {
		int compare = Integer.compare(score, o.score);
		return compare;
	}
}
