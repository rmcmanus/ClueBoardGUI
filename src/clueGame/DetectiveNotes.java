package clueGame;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clueGame.Card.CardType;

public class DetectiveNotes extends JDialog{
	public DetectiveNotes(Board board) {
		setTitle("Detective Notes");
		setSize(600, 800);
		
		setLayout(new GridLayout(3, 2));
		
		add(peoplePanel(board));
		add(personGuess(board));
		add(roomPanel(board));
		add(roomGuess(board));
		add(weaponPanel(board));
		add(weaponGuess(board));
		
	}

	public JPanel peoplePanel(Board board) {
		JPanel people = new JPanel();
		
		people.setBorder(new TitledBorder(new EtchedBorder(), "People"));
		people.setLayout(new GridLayout(3, 2));
		
		for(Player p : board.players) {
			JCheckBox player = new JCheckBox(p.getName());
			people.add(player);
		}
		
		return people;
	}
	
	public JPanel roomPanel(Board board) {
		JPanel rooms = new JPanel();
		
		rooms.setBorder(new TitledBorder(new EtchedBorder(), "Rooms"));
		rooms.setLayout(new GridLayout(0, 2));
		
		for(Character s : board.rooms.keySet()) {
			JCheckBox room = new JCheckBox(board.rooms.get(s));
			if(!(board.rooms.get(s).equalsIgnoreCase("Walkway") || board.rooms.get(s).equalsIgnoreCase("Closet")))
				rooms.add(room);
		}
		
		return rooms;
	}
	
	public JPanel weaponPanel(Board board) {
		JPanel weapons = new JPanel();
		
		weapons.setBorder(new TitledBorder(new EtchedBorder(), "Weapons"));
		weapons.setLayout(new GridLayout(3, 2));
		
		for(Card c : board.dealCards) {
			if(c.getCardType() == CardType.WEAPON) {
				JCheckBox weapon = new JCheckBox(c.getName());
				weapons.add(weapon);
			}
		} 
		
		return weapons;
	}
	
	private JComboBox personGuess(Board board) {
		JComboBox peopleCombo = new JComboBox();
		peopleCombo.setBorder(new TitledBorder(new EtchedBorder(), "People Guess"));
		
		peopleCombo.addItem("Not Sure");
		for(Player p : board.players) {
			peopleCombo.addItem(p.getName());
		}
		
		return peopleCombo;
	}
	
	private JComboBox roomGuess(Board board) {
		JComboBox roomCombo = new JComboBox();
		roomCombo.setBorder(new TitledBorder(new EtchedBorder(), "Room Guess"));
		
		roomCombo.addItem("Not Sure");
		for(Character s : board.rooms.keySet()) {
			if(!(board.rooms.get(s).equalsIgnoreCase("Walkway") || board.rooms.get(s).equalsIgnoreCase("Closet")))
				roomCombo.addItem(board.rooms.get(s));
		}
		
		return roomCombo;
	}
	
	private JComboBox weaponGuess(Board board) {
		JComboBox weaponCombo = new JComboBox();
		weaponCombo.setBorder(new TitledBorder(new EtchedBorder(), "Weapon Guess"));
		
		weaponCombo.addItem("Not Sure");
		for(Card c : board.dealCards) {
			if(c.getCardType() == CardType.WEAPON) {
				weaponCombo.addItem(c.getName());
			}
		} 
		
		return weaponCombo;
	}
}
