package clueGame;

import java.lang.reflect.Array;
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
