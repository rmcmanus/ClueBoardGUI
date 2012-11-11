package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class Player {
	private String name;
	private String color;
	private Set<Card> myCards = new HashSet<Card>();
	private BoardCell currentLocation = new RoomCell();
	private RoomCell lastRoom = new RoomCell();
	public Set<Card> seenCards;
	
	public BoardCell pickLocation(Set<BoardCell> targets) {
		Random gen = new Random();
		
		for(BoardCell b: targets) {
			if((b.isDoorway() == true) && !b.equals(lastRoom)) {
				return b;
			}
		}
		int randTarget = gen.nextInt(targets.size());
		int i = 0;
		for(BoardCell t: targets) {
			if(i == randTarget) {
				return t; 
			}
			i += 1;
		}
		return currentLocation;
	}
	public void addCard(Card card) {
		myCards.add(card);
	}
	public void updateSeen(Card seen) {
		seenCards.add(seen);
	}
	
	public void draw(Graphics g) {
		g.setColor(this.convertColor(this.color));
		g.fillOval(currentLocation.col*Board.CELLSIZE, currentLocation.row*Board.CELLSIZE, Board.CELLSIZE, Board.CELLSIZE);
	}
	
	public void setCurrentLocation(BoardCell currentLocation) {
		this.currentLocation = currentLocation;
	}
	// Be sure to trim the color, we don't want spaces around the name
	public Color convertColor(String strColor) {
		Color color; 
		try {     
			// We can use reflection to convert the string to a color
			Field field = Class.forName("java.awt.Color").getField(strColor.trim());     
			color = (Color)field.get(null); } 
		catch (Exception e) {  
			color = null; // Not defined } 
		}
		return color;
	}
	
	//GETTERS and SETTERS for testing only
	
	public void setName(String name) {
		this.name = name;
	}
	public Set<Card> getMyCards() {
		return myCards;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public BoardCell getVisited() {
		return currentLocation;
	}
	public void setVisited(BoardCell visited) {
		this.currentLocation = visited;
	}
	public String getName() {
		return name;
	}
	public void setMyCards(Set<Card> myCards) {
		this.myCards = myCards;
	}
	public String getColor() {
		return color;
	}
	public ArrayList<Card> createSuggestion() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Card disproveSuggestion(String player, String room, String weapon) {
		//TODO
		return new Card(player, room, weapon);
	}
}
