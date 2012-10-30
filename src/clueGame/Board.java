package clueGame;

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

import clueGame.Card.CardType;

public class Board {
	
	Scanner console = new Scanner(System.in);
	Scanner in = null;
	FileReader reader = null;
	private ArrayList<BoardCell> cells = new ArrayList<BoardCell>();
	private Map<Character, String> rooms = new HashMap<Character, String>();
	
	private int numRows;
	private int numColumns;
	
	HashSet<Integer> visited = new HashSet<Integer>();
	LinkedList<Integer> temp = new LinkedList<Integer>();
	HashSet<BoardCell> targets = new HashSet<BoardCell>();
	private Map<Integer, LinkedList<Integer>> adjMtx = new HashMap<Integer, LinkedList<Integer>>();
	
	String [] tempRooms;
	int tempRow = 0;
	
	String player = null, room = null, weapon = null;
	
	//Player creation
	ArrayList<Player> players = new ArrayList<Player>();
	//Card creation
	ArrayList<Card> cards = new ArrayList<Card>();
	
	public Board() {
		loadConfigFiles();
		loadPlayersFromFile();
		loadCardsFromFile();
		calcAdjacencies();
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
	
	public HashSet<BoardCell> getTargets() {
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
		
	/*	p("  "); for(int x=0; x<numColumns; x++) { p((x%10)+" "); } p("\n");
		for(int i=0; i< numRows; i++) {
			p((i%10)+"-");
			for(int j=0; j<numColumns; j++) {
				BoardCell bc = getCellAt(i,j);
				if(bc.isWalkway()) p("W"); else
				if(bc.isDoorway()) p("D"); else
				if(bc.isRoom()) p("R");
				p(" ");
			}
			p("\n");
		}	*/
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
	/*
	public String pc(int i) {
		int r = i/numColumns;
		int c = i%numColumns;
		return "("+r+","+c+")";
	}
	*/
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
				cards.add(tempCard);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void selectAnswer() {
		//TODO
	}
	
	public void deal() {
		Random randInt = new Random();
		ArrayList<Integer> seen = new ArrayList<Integer>();
		while(seen.size() != cards.size()) {
			int indexDealer = randInt.nextInt(cards.size());
			if(!seen.contains(indexDealer)) {
				for(int i = 0; i < players.size(); i++) {
					CardType getType = cards.get(indexDealer).getCardType();
					int goAhead = 0;
					for(Card c: players.get(i).getMyCards()) {
						if(c.getCardType() == getType) {
							++goAhead;
						}
					}
					if(goAhead == 0) {
						players.get(i).addCard(cards.get(indexDealer));
					}
				}
				seen.add(indexDealer);
			}
		}
		
	}
	public void deal(ArrayList<Card> cardList) {
		
	}
	
	public void makeAccusation() {
		
	}

	public boolean checkAccusation(String person, String room, String weapon) {
		//TODO
		return false;
	}
	public void handleSuggestion(String person, String room, String weapon) {
		//TODO
	}
	
	////////////////////////////////
	//
	//
	////////////////////////////////
	
	public static void main(String[] args) {
		Board board = new Board();
		Player playerOne = board.players.get(0);
		Player computerPlayerOne = board.players.get(1);
		Player computerPlayerTwo = board.players.get(2);
		//System.out.println(board.players.get(0).getName());
		board.deal();
		System.out.println(playerOne.getMyCards().size());
		System.out.println(board.cards.size());
	}

}
