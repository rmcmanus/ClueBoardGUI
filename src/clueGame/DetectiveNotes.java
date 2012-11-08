package clueGame;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class DetectiveNotes extends JDialog{
	public DetectiveNotes() {
		setTitle("Detective Notes");
		setSize(600, 800);
		
		setLayout(new GridLayout(3, 2));
		
		add(peoplePanel());
		add(personGuess());
		add(roomPanel());
		add(roomGuess());
		add(weaponPanel());
		add(weaponGuess());
		
	}

	public JPanel peoplePanel() {
		JPanel people = new JPanel();
		
		people.setBorder(new TitledBorder(new EtchedBorder(), "People"));
		people.setLayout(new GridLayout(3, 2));
		
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
		
		return people;
	}
	
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
		peopleCombo.addItem("Not Sure");
		
		return peopleCombo;
	}
	
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
		roomCombo.addItem("Not Sure");
		
		return roomCombo;
	}
	
	private JComboBox weaponGuess() {
		JComboBox weaponCombo = new JComboBox();
		weaponCombo.setBorder(new TitledBorder(new EtchedBorder(), "Weapon Guess"));
		
		weaponCombo.addItem("Candlestick");
		weaponCombo.addItem("Knife");
		weaponCombo.addItem("Lead Pipe");
		weaponCombo.addItem("Revolver");
		weaponCombo.addItem("Rope");
		weaponCombo.addItem("Wrench");
		weaponCombo.addItem("Not Sure");
		
		return weaponCombo;
	}
}
