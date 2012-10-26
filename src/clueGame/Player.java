package clueGame;

import java.util.Set;

public abstract class Player {
	private String name;
	private String color;
	private Set<Card> myCards;
	
	public Card disproveSuggestion(String person, String room, String weapon) {
		//TODO
		return new Card();
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
	
	public String getName() {
		return name;
	}
	public void setMyCards(Set<Card> myCards) {
		this.myCards = myCards;
	}
	public String getColor() {
		return color;
	}
}
