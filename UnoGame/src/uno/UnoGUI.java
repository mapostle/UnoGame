package uno;

import javax.swing.*;
import BreezySwing.*;
import java.util.*;

public class UnoGUI extends GBFrame {

	private JButton quitbutton;
	static JButton drawbutton;
	private JButton replaybutton;
	static JLabel player1label;
	static JLabel player2label;
	static JLabel player3label;
	static JLabel userlabel;
	static JTextField currentcardlabel;
	static JTextArea log;

	static JList userhand;
	static DefaultListModel list;

	static Game game;

	public UnoGUI() {

		// initializes window objects
		userlabel = addLabel("", 4, 1, 1, 1);
		player1label = addLabel("", 1, 1, 1, 1);
		player2label = addLabel("", 2, 1, 1, 1);
		player3label = addLabel("", 3, 1, 1, 1);
		currentcardlabel = addTextField("", 2, 2, 1, 1);

		// adds non-changeable labels
		addLabel("Current Card", 1, 2, 1, 1);
		addLabel("User Hand", 5, 1, 1, 1);
		addLabel("Player Log", 5, 2, 1, 1);
		addLabel("**Double Click to play card**", 7, 1, 1, 1);

		// text areas
		userhand = addList(6, 1, 1, 1);
		log = addTextArea("", 6, 2, 1, 1);

		// buttons
		drawbutton = addButton("Draw", 3, 2, 1, 1);
		quitbutton = addButton("Quit", 7, 2, 1, 1);
		replaybutton = addButton("New Game", 8, 2, 1, 1);

		String name = ""; // name of user

		// asks for user's name until they put it
		while (name.isEmpty()) {
			name = JOptionPane.showInputDialog("Type in Name"); // asks for name

			if (name == null) {
				System.exit(0);
			}

		}

		game = new Game(name); // creates a game

		DataModel.refreshUI(); // updates the current card number for each player

	}

	public void buttonClicked(JButton buttonObj) {

		// if player hits draw button
		if (buttonObj == drawbutton) {

			game.Draw(); // draws card

			game.cpuTurns(); // initiates CPU turns

			log.setText(game.getLog()); // updates log
			DataModel.refreshUI(); // refreshes the UI with new card count, current card, and user hand

			endofGame();

		} else if (buttonObj == quitbutton) {
			System.exit(0); // quits game
		} else if (buttonObj == replaybutton) {
			newgame();

		}

	}

	// if item is double clicked in list box, attempts to put down that card
	// notifies user if card is invalid
	public void listDoubleClicked(JList listObj, String itemClicked) {

		int index = listObj.getSelectedIndex();

		// if card is wildcard, asks for color
		if (game.isWildCard(index)) {

			// creates list of color titles
			String[] colors = { "Red", "Green", "Yellow", "Blue" };

			String c = (String) JOptionPane.showInputDialog(null, "Select a color", "Wildcard Color",
					JOptionPane.PLAIN_MESSAGE, null, colors, colors[0]);

			if (c != null) {
				game.setColor(index, c);
			} else {
				return;
			}

		}

		try {

			// puts down specific card and removes it from user hand
			game.putDown(index);
			list.remove(index);

			game.cpuTurns(); // initiates CPU turns

			log.setText(game.getLog()); // updates Log
			DataModel.refreshUI(); // refreshes UI

			endofGame();

		} catch (IllegalArgumentException e) {
			messageBox("Can't put that card down!");
		}

	}

	private void endofGame() {

		if (UnoGUI.game.getEnd().length() > 0) {
			messageBox(UnoGUI.game.getEnd());
			newgame();
			dispose();
			
		}

	}

	private void newgame() {

		int replay = JOptionPane.showOptionDialog(null, "Do you want to start a new game?", "New Game",
				JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

		if (replay == 0) {
			dispose();
			UnoGUI.main(null);
		}

	}

	public static void main(String args[]) {

		UnoGUI gui = new UnoGUI();

		gui.setSize(700, 600);
		gui.setTitle("Uno");
		gui.setVisible(true);
		gui.setResizable(false);

	}
}


