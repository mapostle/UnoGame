package uno;

import java.util.ArrayList;

public abstract class Player {

	protected ArrayList<Card> hand; // cards in player's hand
	private String name;

	// constructor
	public Player(String n) {

		hand = new ArrayList<Card>();
		getCards();
		name = n;

	}

	// draws card, adds it to hand and returns specific card
	public Card draw() {

		Card c = Deck.removeCard();

		hand.add(c);

		return c;
	}

	// checks validity of hand
	public boolean validHand() {
		for (Card c : hand) {
			if (c.isValid(Game.currentCard) || c.isWildCard())
				return true;
		}
		return false;
	}

	// draws seven cards
	private void getCards() {

		for (int i = 0; i < 7; i++)
			draw();

	}

	// returns number of cards in hand
	public int getHandSize() {
		return hand.size();
	}

	public String toString() {
		return name;
	}

}