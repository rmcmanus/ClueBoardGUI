package clueGame;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import clueGame.Card.CardType;

public class Board extends JFrame{
	
	Scanner console = new Scanner(System.in);
	Scanner in = null;
	FileReader reader = null;
	private ArrayList<BoardCell> cells = new ArrayList<BoardCell>();
	public static Map<Character, String> rooms = new HashMap<Character, String>();
	
	private int numRows;
	private int numColumns;
	
	HashSet<Integer> visited = new HashSet<Integer>();
	LinkedList<Integer> temp = new LinkedList<Integer>();
	HashSet<BoardCell> targets = new HashSet<BoardCell>();
	private Map<Integer, LinkedList<Integer>> adjMtx = new HashMap<Integer, LinkedList<Integer>>();
	
	String [] tempRooms;
	int tempRow = 0;
	
	//Player creation
	ArrayList<Player> players = new ArrayList<Player>();
	//Card creation
	ArrayList<Card> dealCards = new ArrayList<Card>();
	static ArrayList<Card> allCards = new ArrayList<Card>();
	
	JDialog dialog;
	
	public Board() {
		loadConfigFiles();
		loadPlayersFromFile();
		loadCardsFromFile();
		calcAdjacencies();
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(createFileMenu());
		
		setSize(new Dimension(800, 800));
		setTitle("Clue Game");
	}
	
	public void calcAdjacencies() {
		for (int x=0; x < numRows; x++)
		{
			for (int y=0; y < numColumns; y++)
			{
				BoardCell thisCell = getCellAt(x,y);
				LinkedList<Integer> a = new LinkedList<Integer>();
				if (x != 0) {
					BoardCell thatCell = getCellAt(x-1,y);
					if((thisCell.isWalkway() && thatCell.isWalkway()) ||
							(thatCell.isWalkway() && thisCell.isDoorway() && ((RoomCell)thisCell).getDoorDirection()==RoomCell.DoorDirection.UP) ||
							(thisCell.isWalkway() && thatCell.isDoorway() && ((RoomCell)thatCell).getDoorDirection()==RoomCell.DoorDirection.DOWN)) {
						a.add(calcIndex(x-1, y));
					}
				}
				if (y != 0) {
					BoardCell thatCell = getCellAt(x,y-1);
					if((thisCell.isWalkway() && thatCell.isWalkway()) ||
							(thatCell.isWalkway() && thisCell.isDoorway() && ((RoomCell)thisCell).getDoorDirection()==RoomCell.DoorDirection.LEFT) ||
							(thisCell.isWalkway() && thatCell.isDoorway() && ((RoomCell)thatCell).getDoorDirection()==RoomCell.DoorDirection.RIGHT)) {
						a.add(calcIndex(x, y-1));
					}
				}
				if (x != numRows-1) {
					BoardCell thatCell = getCellAt(x+1,y);
					if((thisCell.isWalkway() && thatCell.isWalkway()) ||
							(thatCell.isWalkway() && thisCell.isDoorway() && ((RoomCell)thisCell).getDoorDirection()==RoomCell.DoorDirection.DOWN) ||
							(thisCell.isWalkway() && thatCell.isDoorway() && ((RoomCell)thatCell).getDoorDirection()==RoomCell.DoorDirection.UP)) {
						a.add(calcIndex(x+1, y));
					}
				}
				if (y != numColumns-1) {
					BoardCell thatCell = getCellAt(x,y+1);
					if((thisCell.isWalkway() && thatCell.isWalkway()) ||
							(thatCell.isWalkway() && thisCell.isDoorway() && ((RoomCell)thisCell).getDoorDirection()==RoomCell.DoorDirection.RIGHT) ||
							(thisCell.isWalkway() && thatCell.isDoorway() && ((RoomCell)thatCell).getDoorDirection()==RoomCell.DoorDirection.LEFT)) {
						a.add(calcIndex(x, y+1));
					}
				}
				adjMtx.put(calcIndex(x, y), a);
			}
		}
	}
	
	public void calcTargets(int loc, int steps) {
	//	p("LOC: "+loc+ "/"+pc(loc)+", steps: " + steps +"\n");
		int counter = steps;
		targets.clear();
		LinkedList<Integer> middleMan;
		ListIterator<Integer> iter = adjMtx.get(loc).listIterator();
		ListIterator<Integer> iter2 = null;
		if (counter == 1)
		{
			while (iter.hasNext())
			{
				Integer i = (Integer)iter.next();
				targets.add(getCellAt(i));
			}
		}
		else
		{
			temp = (LinkedList<Integer>)adjMtx.get(loc).clone();
			while (counter > 1)
			{
				visited.clear();
				visited.add(loc);
	//			p("temp="+temp+"\n");
				middleMan = (LinkedList<Integer>)temp.clone();
				temp.clear();
				iter = middleMan.listIterator();
				while (iter.hasNext())
				{
					Integer i = iter.next();
					if(getCellAt(i).isDoorway())
						targets.add(getCellAt(i));
					if (visited.contains(i))
						continue;
					else
					{
	//					p("Scanning rooms around "+pc(i)+"\n");
						visited.add(i);
	//					p("Visited: " + visited+"\n");
						iter2 = adjMtx.get(i).listIterator();
						while (iter2.hasNext())
						{
							Integer j = (Integer) iter2.next();
	//						p("Adding "+pc(j)+"\n");
							if(j!=loc) {
								if(getCellAt(j).isDoorway()) {
									targets.add(getCellAt(j));
								}else if( adjMtx.get(j).size() == 1 && adjMtx.get(j).contains(i) && counter > 2)
									continue;
								else {
									temp.add(j);
								}
							}
						}
					}
				}
				counter--;
			}
			iter = temp.listIterator();
			while (iter.hasNext())
			{
				Integer k = (Integer) iter.next();
				targets.add(getCellAt(k));
			}	
		}
	}
	
	public Set<BoardCell> getTargets() {
		return targets;
	}
	
	public LinkedList<Integer> getAdjList(Integer index) {
		return adjMtx.get(index);
	}
	
	
	public void loadConfigFiles() {
		tempRow = 0;
		
		int firstRowLength;
		int secondRowLength;
				
		try{
			reader = new FileReader("Legend.txt");
			in = new Scanner(reader);
		}
		catch (FileNotFoundException e){
			System.out.println(e.getMessage());
		}
		try{
			while (in.hasNextLine())
			{
				String room = in.nextLine();
				if (room.indexOf(", ") == -1) {
					throw new BadConfigFormatException(room);	
				}
				String roomChar = room.substring(0, room.indexOf(", "));
				String roomType = room.substring(room.indexOf(", ")+2, room.length());
				char type = roomChar.charAt(0);
				rooms.put(type, roomType);
			}
		}
		catch (BadConfigFormatException e){
			System.out.println(e.getMessage() + ", is not a valid room");
		}
		try{
			reader = new FileReader("Config.txt");
			in = new Scanner(reader);
		}
		catch (FileNotFoundException e){
			System.out.println(e.getMessage());
		}
		try{
			String room = in.nextLine();
			tempRooms = room.split(",");
			firstRowLength = tempRooms.length;
			loadBoardCells(tempRooms.length);
			while (in.hasNextLine())
			{
				room = in.nextLine();
				tempRooms = room.split(",");
				secondRowLength = tempRooms.length;
				if (secondRowLength != firstRowLength) {
					throw new BadConfigFormatException(room);	
				}
				loadBoardCells(tempRooms.length);
			}
		}
		catch (BadConfigFormatException e){
			System.out.println(e.getMessage() + ", is not a valid set of rooms");
		}
		numColumns = tempRooms.length;
		numRows = tempRow;
	}
	
	public int calcIndex(int row, int col) {
		return row*numColumns + col;
	}
	
	public BoardCell getCellAt(int x, int y) {
		return cells.get(calcIndex(x, y));
	}
	
	public BoardCell getCellAt(int i) {
		return cells.get(i);
	}
	
	public RoomCell getRoomCellAt(int x, int y) {
		return (RoomCell) cells.get(calcIndex(x, y));
	}
	
	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}
	
	public Map<Character, String> getRooms() {
		return rooms;
	}
	
	public String getRoomName(char c) {
		String value = rooms.get(c);
			if (value == null)
				return "Invalid Room";
			else
				return value;
	}
	
	public ArrayList<BoardCell> getCells() {
		return cells;
	}
	
	public void loadBoardCells(int num){
		for (int i = 0; i < num; i++){
			BoardCell cell;
			if (tempRooms[i].charAt(0) == 'W'){
				cell = new WalkwayCell();
			}
			else{
				cell = new RoomCell();
				((RoomCell) cell).setInitial(tempRooms[i].charAt(0));
				if (tempRooms[i].length() > 1){
					((RoomCell) cell).setDoorDirection(tempRooms[i].charAt(1));
				}
				else
					((RoomCell) cell).setDoorDirection('Z');
			}
			cell.setRow(tempRow);
			cell.setCol(i);
			cells.add(cell);
		}
		tempRow++;
	}

	public static void p(Object o) {
		System.out.print(o);
	}
	
	////////////////////////////////
	//STUBLAND
	//YE WHO ENTER BE WARNED
	//STUBS LAY BEYOND
	////////////////////////////////
	
	public void loadPlayersFromFile() {
		try {
			FileReader playerReader = new FileReader("Player.txt");
			Scanner playerIn = new Scanner(playerReader);
			String humanName = playerIn.nextLine();
			Player tempPlayer = new HumanPlayer();
			tempPlayer.setName(humanName);
			String humanColor = playerIn.nextLine();
			tempPlayer.setColor(humanColor);
			players.add(tempPlayer);
			while(playerIn.hasNextLine()) {
				String tempName = playerIn.nextLine();
				tempPlayer = new ComputerPlayer();
				tempPlayer.setName(tempName);
				String tempColor = playerIn.nextLine();
				tempPlayer.setColor(tempColor);
				players.add(tempPlayer);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void loadCardsFromFile() {
		try {
			FileReader cardReader = new FileReader("Card.txt");
			Scanner cardIn = new Scanner(cardReader);
			while(cardIn.hasNextLine()) {
				String cardType = cardIn.nextLine();
				CardType tempType;
				if(cardType.equals("player")) {
					tempType = CardType.PLAYER;
				}
				else if(cardType.equals("weapon")) {
					tempType = CardType.WEAPON;
				}
				else {
					tempType = CardType.ROOM;
				}
				String cardName = cardIn.nextLine();
				Card tempCard = new Card(cardName, tempType);
				dealCards.add(tempCard);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void selectAnswer() {
		ArrayList<Card> playerList = new ArrayList<Card>();
		ArrayList<Card> weaponList = new ArrayList<Card>();
		ArrayList<Card> roomList = new ArrayList<Card>();
		
		Random gen = new Random();
		
		allCards = dealCards;
		//fill temp lists for solution cardTypes
		for(Card c: dealCards) {
			if(c.getCardType() == CardType.PLAYER) {
				playerList.add(c);
			}else if(c.getCardType() == CardType.WEAPON) {
				weaponList.add(c);
			}else if(c.getCardType() == CardType.ROOM) {
				roomList.add(c);
			}
		}
			
		//select player
		int playerIndex = gen.nextInt(dealCards.size() / 3);	
		Solution.person = playerList.get(playerIndex).getName();
		playerList.remove(playerIndex);
		//select weapon
		int weaponIndex = gen.nextInt(dealCards.size() / 3);	
		Solution.weapon = weaponList.get(weaponIndex).getName();
		weaponList.remove(weaponIndex);
		//select room
		int roomIndex = gen.nextInt(dealCards.size() / 3);	
		Solution.room = roomList.get(roomIndex).getName();
		roomList.remove(roomIndex);
		
		//Iterate through temp lists to create new card list
		ArrayList<Card> tempCards = new ArrayList<Card>();
		
		for(Card p: playerList) {
			tempCards.add(p);
		}
		for(Card w: weaponList) {
			tempCards.add(w);
		}
		for(Card r: roomList) {
			tempCards.add(r);
		}
		
		dealCards = tempCards;
		
	}
	
	public void deal() {
		Random randInt = new Random();
		ArrayList<Integer> seen = new ArrayList<Integer>();
		while(seen.size() != dealCards.size()) {
			int indexDealer = randInt.nextInt(dealCards.size());
			if(!seen.contains(indexDealer)) {
				for(int i = 0; i < players.size(); i++) {
					CardType getType = dealCards.get(indexDealer).getCardType();
					int goAhead = 0;
					for(Card c: players.get(i).getMyCards()) {
						if(c.getCardType() == getType) {
							++goAhead;
						}
					}
					if(goAhead == 0) {
						players.get(i).addCard(dealCards.get(indexDealer));
					}
				}
				seen.add(indexDealer);
			}
		}
		
	}
	public void deal(ArrayList<Card> cardList) {
		
	}
	

	public boolean checkAccusation(String person, String room, String weapon) {
		if(person.equalsIgnoreCase(Solution.person) && weapon.equalsIgnoreCase(Solution.weapon) && room.equalsIgnoreCase(Solution.room)) {
			return true;
		}else {
			return false;
		}
		
	}
	public void handleSuggestion(Player currentPlayer, ArrayList<Card> suggestCards) {
		for(Player p : players)
			if(!p.getName().equals(currentPlayer.getName()))
				for(Card c : p.getMyCards())
					if(suggestCards.contains(c)) {
						currentPlayer.updateSeen(c);
						break;
					}
	}
	
	////////////////////////////////
	//
	//DRAW FUNCTIONS
	//
	////////////////////////////////
	
	private JMenu createFileMenu()
	{
		JMenu menu = new JMenu("File");
		menu.add(createDetectiveNotes());
		menu.add(createFileExitItem());
		return menu;
	}

	private JMenuItem createFileExitItem()
	{
		JMenuItem item = new JMenuItem("Exit");
		class MenuItemListener implements ActionListener {
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		}
		item.addActionListener(new MenuItemListener());
		return item;
	}
	private JMenuItem createDetectiveNotes() {
		JMenuItem detectiveNotes = new JMenuItem("Detective Notes");
		class MenuItemListener implements ActionListener {
			public void actionPerformed(ActionEvent e)
			{
				dialog = new DetectiveNotes();
				dialog.setVisible(true);
			}
		}
		detectiveNotes.addActionListener(new MenuItemListener());
		return detectiveNotes;
	}

	public static void main(String[] args) {
		Board board = new Board();
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.setVisible(true);
	}
	
	////////////////////////////////
	//
	//
	////////////////////////////////

}
