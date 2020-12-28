package uno;

import java.util.ArrayList;

public class CPU extends Player {

	private String playerlog;
	
	public CPU(String n) {
		super(n);
		playerlog = "";

	}

	// Method called every CPU turn; makes decisions
	public void play() {

		playerlog = this.toString() + "'s turn: \n";

		// index of valid card
		int putdown = findvalidCard(Game.currentCard);

		// if no valid card, draw and checks if draw is valid
		if (putdown == -1) {

			draw(); // draws card
			
			playerlog += "Draws a card \n";

			putdown = findvalidCard(Game.currentCard); // evaluates card

		}

		// if valid card, putdown card
		if (putdown != -1) {

			playerlog += "Puts down a card \n";

			// if wildcard, determine color
			if (hand.get(putdown).isWildCard()) {
				hand.get(putdown).setColor(determinecolor());
			}

			Game.setCurrentCard(hand.remove(putdown));
		}

	}
	
	public String getPlayerLog() {
		return playerlog;
	}

	// Either finds index of first valid card compared to current OR returns
	// wildcard OR nothing
	private int findvalidCard(Card current) {

		int wildcard = -1;

		for (int i = 0; i < hand.size(); i++) {

			if (hand.get(i).isValid(current)) {
				return i;
			}

			if (hand.get(i).isWildCard()) {
				wildcard = i;
			}

		}

		return wildcard;

	}

	// determines the color of the wildcard based on
	private String determinecolor() {

		int red = colorcount("Red");
		int green = colorcount("Green");
		int yellow = colorcount("Yellow");
		int blue = colorcount("Blue");

		// determines color
		if (Math.max(Math.max(red, green), Math.max(yellow, blue)) == red)
			return "Red";
		else if (Math.max(Math.max(red, green), Math.max(yellow, blue)) == green)
			return "Green";
		else if (Math.max(Math.max(red, green), Math.max(yellow, blue)) == yellow)
			return "Yellow";
		else
			return "Blue";

	}

	// counts the number of each color card in hand
	private int colorcount(String col) {

		int count = 0;

		for (Card c : hand) {

			if (c.getColor().equals(col)) {
				count++;
			}

		}

		return count;

	}

}