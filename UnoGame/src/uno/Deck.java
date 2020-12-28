package uno;

import java.util.*;

public class Deck {

	static ArrayList<Card> deck; // deck of cards

	// creates deck shuffles it
	public Deck() {

		deck = new ArrayList<Card>();

		createdeck();
		shuffle();

	}

	// creates deck with appropriate suits and wildcards
	private void createdeck() {

		createsuit("Red");
		createsuit("Yellow");
		createsuit("Green");
		createsuit("Blue");

		for (int i = 0; i < 8; i++) {

			if (i < 4) {
				deck.add(new Card(13));
			} else {
				deck.add(new Card(14));
			}

		}

	}

	// creates 12 cards for each suit and adds to deck
	private void createsuit(String color) {

		deck.add(new Card(color, 0));

		for (int i = 1; i <= 12; i++) {
			Card c = new Card(color, i);
			deck.add(c);
			deck.add(c);

		}

	}

	// shuffles deck
	public void shuffle() {

		for (int i = 0; i < deck.size(); i++) {

			int random = i;

			while (random == i) {
				random = (int) (Math.random() * 108);
			}

			Card temp = deck.get(random);
			deck.set(random, deck.get(i));
			deck.set(i, temp);

		}

	}

	// removes card from deck
	static Card removeCard() {
		return deck.remove(0);
	}

	// retrieves first card put down of the game
	// card is non-wild or a special card
	static Card getFirstCard() {

		int i = 0;

		while (deck.get(i).getValue() > 9 && i < deck.size()) {
			i++;
		}

		return deck.remove(i);

	}

}