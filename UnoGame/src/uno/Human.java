package uno;

import java.util.*;

public class Human extends Player {

	// constructor
	public Human(String n) {

		super(n);

	}

	public Card getDrawnCard() {

		return hand.get(getHandSize() - 1);

	}

	// checks validity of card at index
	// if valid, removes card from deck and updates current card
	// else throws error
	public void putDown(int index) {

		if (hand.get(index).isValid(Game.currentCard) || hand.get(index).isWildCard()) {
			Game.setCurrentCard(hand.remove(index));
		} else {
			throw new IllegalArgumentException();
		}

	}

}