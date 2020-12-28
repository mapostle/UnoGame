package uno;

public class Card {

	private String color; // color of card
	private int value; // value 1-9 10 (skip), 11 (reverse), 12 (drawtwo), 13 (wildcard),
						// 14(wildcard+4)

	// constructor initializes color cards 1-9
	public Card(String col, int val) {

		color = col;
		value = val;

	}

	// constructor initializes wildcards
	public Card(int val) {

		color = "";
		value = val;
	}

	// set color of wildcards
	public void setColor(String col) {
		color = col;
	}

	// getter methods
	public int getValue() {
		return value;
	}

	public String getColor() {
		return color;
	}

	// checks to see if card in parameter matches with card
	public boolean isValid(Card c) {

		if (c.getColor().equals(getColor()) || c.getValue() == getValue()) {
			return true;
		} else {
			return false;
		}

	}

	// is Wild Card or not
	public boolean isWildCard() {

		return value == 13 || value == 14;

	}

	// prints color and value
	public String toString() {

		String str = color;

		switch (value) {

		case 10:
			str += " Skip";
			break;

		case 11:
			str += " Reverse";
			break;

		case 12:
			str += " Draw Two";
			break;

		case 13:
			str += " Wild Card";
			break;

		case 14:
			str += " Wild Card Plus 4";
			break;

		default:
			str += " " + value;
			break;

		}

		return str;

	}

}