package clueGame;

public class Card {
	public enum CardType {ROOM, WEAPON, PLAYER};
	private CardType cardType;
	private String name;
	
	public CardType getCardType() {
		return cardType;
	}
	
	//GETTERS and SETTERS for testing only
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
