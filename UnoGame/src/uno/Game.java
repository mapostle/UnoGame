package uno;

public class Game {

	private Player[] players; // holds order of players
	static Card currentCard; // current card in the game
	private int turn; // current turn in array of players
	static Player human, cpu1, cpu2, cpu3; // players
	private String gamelog;
	
	private String end;

	// creates new deck, creates and adds players to array, draws first card, and
	// starts game
	public Game(String name) {

		new Deck();
		createplayers(name);
		turn = 0;
		setCurrentCard(Deck.getFirstCard());
		gamelog = "";
		end = "";

	}

	// creates and adds players to array
	private void createplayers(String name) {

		players = new Player[4];

		human = new Human(name);
		cpu1 = new CPU("Player 1");
		cpu2 = new CPU("Player 2");
		cpu3 = new CPU("Player 3");

		players[0] = human;
		players[1] = cpu1;
		players[2] = cpu2;
		players[3] = cpu3;

	}

	// loops through each player's turn, evaluates current card and lets player play
	// turns
	public void cpuTurns() {

		gamelog = "";
		increaseturn(); // increases turn to next CPU turn
		evaluatecard(); // evaluates current card (one put down by player)

		// loops through all three CPU turns
		while (players[turn] instanceof CPU) {

			((CPU) players[turn]).play(); // calls CPU to play turn
			addLog(((CPU) players[turn]).getPlayerLog()); // adds CPU activity to log

			determineend();
			
			if (end.length() > 0) {
				return;
			}
			
			increaseturn(); // goes to next turn
			evaluatecard(); // evaluates current card

		}

	}

	// sets current card
	static void setCurrentCard(Card c) {
		currentCard = c;
	}

	// evaluates current card and decides which player's turn is next
	private void evaluatecard() {

		int val = currentCard.getValue(); // holds value of current card

		if (val == 10) {

			increaseturn(); // if skip card, skips turn

		} else if (val == 11) {

			// goes back a player's turn
			turn--;

			if (turn < 0) {
				turn = 3;
			}

			reverseorder(); // if reverse card, reverse order of players and skips turn

		} else if (val == 12) {

			// add two cards to player
			for (int i = 0; i < 2; i++) {
				players[turn].draw();
			}

			increaseturn(); // skips turn

		} else if (val == 14) {

			// add four cards to player
			for (int i = 0; i < 4; i++) {
				players[turn].draw();
			}

			increaseturn(); // skips turn
		}

	}

	// loops through player array
	private void increaseturn() {

		turn++;

		if (turn == 4) {
			turn = 0;
		}

	}

	// reverse order of players in the array
	private void reverseorder() {

		for (int i = 0; i < players.length / 2; i++) {

			Player temp = players[i];
			players[i] = players[players.length / 2 + 1 - i];
			players[players.length / 2 + 1 - i] = temp;

		}

	}

	// adds given CPU activity to log
	private void addLog(String l) {

		gamelog += l + " The current card is: " + currentCard + "\n";

	}

	// returns log
	public String getLog() {
		return gamelog;

	}

	// returns current card
	public Card getCurrentCard() {
		return currentCard;
	}

	// puts down user card and ends turn
	// put down throws an error if card is invalid
	public void putDown(int index) {

		((Human) human).putDown(index);
		
		determineend();

	}

	// human draws card from deck
	public void Draw() {

		human.draw();

	}

	// returns card drawn by the User
	public Card getDrawn() {

		return ((Human) human).getDrawnCard();

	}

	// checks to see if card is wild card
	public boolean isWildCard(int index) {

		return human.hand.get(index).isWildCard();

	}

	// sets color of wildcard chosen by human
	public void setColor(int index, String col) {
		human.hand.get(index).setColor(col);
	}


	public void determineend() {
		
		if (players[turn].getHandSize() == 0) {
			end = players[turn] + " wins!";
		}else if (Deck.deck.size() == 0) {
			end = "It's a draw :(";
		}
		
	}
	
	public String getEnd() {
		return end;
	}

}
