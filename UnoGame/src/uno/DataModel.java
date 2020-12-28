package uno;

import java.awt.Color;
import java.util.*;
import javax.swing.*;
import BreezySwing.*;

public class DataModel extends GBFrame {

	// updates the number of cards for each player after every turn
	static void refreshUI() {

		UnoGUI.userlabel.setText(((Human) UnoGUI.game.human) + ": " + UnoGUI.game.human.getHandSize());
		UnoGUI.player1label.setText("Player 1: " + UnoGUI.game.cpu1.getHandSize());
		UnoGUI.player2label.setText("Player 2: " + UnoGUI.game.cpu2.getHandSize());
		UnoGUI.player3label.setText("Player 3: " + UnoGUI.game.cpu3.getHandSize());

		displaycurrentcard();
		displayhand();
		enabledrawbutton();

	}

	static void enabledrawbutton() {

		// enable or disable draw button
		if (UnoGUI.game.human.validHand())
			UnoGUI.drawbutton.setEnabled(false);
		else if (UnoGUI.game.human.validHand() == false)
			UnoGUI.drawbutton.setEnabled(true);

	}

	// displays all the cards in human hand
	static void displayhand() {

		UnoGUI.list = (DefaultListModel) UnoGUI.userhand.getModel();

		UnoGUI.list.clear();

		ArrayList<Card> a = UnoGUI.game.human.hand;

		for (Card c : a) {
			UnoGUI.list.addElement(c.toString());
		}

		UnoGUI.userhand = new JList<Card>(UnoGUI.list);

	}

	// displays current card and appropriate color
	static void displaycurrentcard() {

		UnoGUI.currentcardlabel.setText(UnoGUI.game.getCurrentCard().toString());

		switch (UnoGUI.game.getCurrentCard().getColor()) {

		case "Red":
			UnoGUI.currentcardlabel.setBackground(Color.red);

			UnoGUI.currentcardlabel.setForeground(Color.white);

			break;

		case "Green":
			UnoGUI.currentcardlabel.setBackground(Color.green);

			UnoGUI.currentcardlabel.setForeground(Color.black);

			break;

		case "Blue":
			UnoGUI.currentcardlabel.setBackground(Color.blue);

			UnoGUI.currentcardlabel.setForeground(Color.white);

			break;

		default:
			UnoGUI.currentcardlabel.setBackground(Color.yellow);

			UnoGUI.currentcardlabel.setForeground(Color.black);

			break;

		}

	}

}
