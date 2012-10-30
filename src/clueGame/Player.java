package clueGame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public abstract class Player {
	private String name;
	private String color;
	private Set<Card> myCards = new HashSet<Card>();
	private BoardCell visited;
	public Object seenCards;
	
	public Card disproveSuggestion(String player, String room, String weapon) {
		//TODO
		return new Card(player, room, weapon);
	}
	public BoardCell pickLocation(HashSet<BoardCell> targets) {
		
		return new WalkwayCell();
	}
	public void addCard(Card card) {
		myCards.add(card);
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
		return visited;
	}
	public void setVisited(BoardCell visited) {
		this.visited = visited;
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
}
