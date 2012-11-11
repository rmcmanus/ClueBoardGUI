package clueGame;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

<<<<<<< HEAD
import clueGame.Card.CardType;

public class DetectiveNotes extends JDialog{
	public DetectiveNotes(Board board) {
=======
public class DetectiveNotes extends JDialog{
	public DetectiveNotes() {
>>>>>>> fdb94791ae4721272d4da829325aaf4e999b73da
		setTitle("Detective Notes");
		setSize(600, 800);
		
		setLayout(new GridLayout(3, 2));
		
<<<<<<< HEAD
		add(peoplePanel(board));
		add(personGuess(board));
		add(roomPanel(board));
		add(roomGuess(board));
		add(weaponPanel(board));
		add(weaponGuess(board));
		
	}

	public JPanel peoplePanel(Board board) {
=======
		add(peoplePanel());
		add(personGuess());
		add(roomPanel());
		add(roomGuess());
		add(weaponPanel());
		add(weaponGuess());
		
	}

	public JPanel peoplePanel() {
>>>>>>> fdb94791ae4721272d4da829325aaf4e999b73da
		JPanel people = new JPanel();
		
		people.setBorder(new TitledBorder(new EtchedBorder(), "People"));
		people.setLayout(new GridLayout(3, 2));
		
<<<<<<< HEAD
		for(Player p : board.players) {
			JCheckBox player = new JCheckBox(p.getName());
			people.add(player);
		}
=======
		JCheckBox scarlet = new JCheckBox("Miss Scarlet");
		people.add(scarlet);
		JCheckBox mustard = new JCheckBox("Colonel Mustard");
		people.add(mustard);
		JCheckBox green = new JCheckBox("Mr Green");
		people.add(green);
		JCheckBox white = new JCheckBox("Mrs White");
		people.add(white);
		JCheckBox peacock = new JCheckBox("Mrs Peacock");
		people.add(peacock);
		JCheckBox plum = new JCheckBox("Professor Plum");
		people.add(plum);
>>>>>>> fdb94791ae4721272d4da829325aaf4e999b73da
		
		return people;
	}
	
<<<<<<< HEAD
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
=======
	public JPanel roomPanel() {
		JPanel room = new JPanel();
		
		room.setBorder(new TitledBorder(new EtchedBorder(), "Rooms"));
		room.setLayout(new GridLayout(0, 2));
		
		JCheckBox kitchen = new JCheckBox("Kitchen");
		room.add(kitchen);
		JCheckBox diningRoom = new JCheckBox("Dining Room");
		room.add(diningRoom);
		JCheckBox lounge = new JCheckBox("Lounge");
		room.add(lounge);
		JCheckBox ballroom = new JCheckBox("Ballroom");
		room.add(ballroom);
		JCheckBox conservatory = new JCheckBox("Conservatory");
		room.add(conservatory);
		JCheckBox hall = new JCheckBox("Hall");
		room.add(hall);
		JCheckBox study = new JCheckBox("Study");
		room.add(study);
		JCheckBox library = new JCheckBox("Library");
		room.add(library);
		JCheckBox billiardRoom = new JCheckBox("Billiard Room");
		room.add(billiardRoom);
		
		return room;
	}
	
	public JPanel weaponPanel() {
		JPanel weapon = new JPanel();
		
		weapon.setBorder(new TitledBorder(new EtchedBorder(), "Weapons"));
		weapon.setLayout(new GridLayout(3, 2));
		
		JCheckBox rope = new JCheckBox("Rope");
		weapon.add(rope);
		JCheckBox candlestick = new JCheckBox("Candlestick");
		weapon.add(candlestick);
		JCheckBox knife = new JCheckBox("Knife");
		weapon.add(knife);
		JCheckBox leadPipe = new JCheckBox("Lead Pipe");
		weapon.add(leadPipe);
		JCheckBox revolver = new JCheckBox("Revolver");
		weapon.add(revolver);
		JCheckBox wrench = new JCheckBox("Wrench");
		weapon.add(wrench);
		
		return weapon;
	}
	
	private JComboBox personGuess() {
		JComboBox peopleCombo = new JComboBox();
		peopleCombo.setBorder(new TitledBorder(new EtchedBorder(), "People Guess"));
		
		peopleCombo.addItem("Miss Scarlet");
		peopleCombo.addItem("Colonel Mustard");
		peopleCombo.addItem("Mr Green");
		peopleCombo.addItem("Mrs White");
		peopleCombo.addItem("Mrs Peacock");
		peopleCombo.addItem("Professor Plum");
>>>>>>> fdb94791ae4721272d4da829325aaf4e999b73da
		
		return peopleCombo;
	}
	
<<<<<<< HEAD
	private JComboBox roomGuess(Board board) {
		JComboBox roomCombo = new JComboBox();
		roomCombo.setBorder(new TitledBorder(new EtchedBorder(), "Room Guess"));
		
		roomCombo.addItem("Not Sure");
		for(Character s : board.rooms.keySet()) {
			if(!(board.rooms.get(s).equalsIgnoreCase("Walkway") || board.rooms.get(s).equalsIgnoreCase("Closet")))
				roomCombo.addItem(board.rooms.get(s));
		}
=======
	private JComboBox roomGuess() {
		JComboBox roomCombo = new JComboBox();
		roomCombo.setBorder(new TitledBorder(new EtchedBorder(), "Room Guess"));
		
		roomCombo.addItem("Kitchen");
		roomCombo.addItem("Dining");
		roomCombo.addItem("Lounge");
		roomCombo.addItem("Ballroom");
		roomCombo.addItem("Conservatory");
		roomCombo.addItem("Hall");
		roomCombo.addItem("Study");
		roomCombo.addItem("Library");
		roomCombo.addItem("Billiard Room");
>>>>>>> fdb94791ae4721272d4da829325aaf4e999b73da
		
		return roomCombo;
	}
	
<<<<<<< HEAD
	private JComboBox weaponGuess(Board board) {
		JComboBox weaponCombo = new JComboBox();
		weaponCombo.setBorder(new TitledBorder(new EtchedBorder(), "Weapon Guess"));
		
		weaponCombo.addItem("Not Sure");
		for(Card c : board.dealCards) {
			if(c.getCardType() == CardType.WEAPON) {
				weaponCombo.addItem(c.getName());
			}
		} 
=======
	private JComboBox weaponGuess() {
		JComboBox weaponCombo = new JComboBox();
		weaponCombo.setBorder(new TitledBorder(new EtchedBorder(), "Weapon Guess"));
		
		weaponCombo.addItem("Candlestick");
		weaponCombo.addItem("Knife");
		weaponCombo.addItem("Lead Pipe");
		weaponCombo.addItem("Revolver");
		weaponCombo.addItem("Rope");
		weaponCombo.addItem("Wrench");
>>>>>>> fdb94791ae4721272d4da829325aaf4e999b73da
		
		return weaponCombo;
	}
}
