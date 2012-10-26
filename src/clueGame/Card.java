package clueGame;

public class Card {
	public enum CardType {ROOM, WEAPON, PLAYER};
	private CardType cardType;
	private String name;
	private String player, room, weapon;
	
	public Card() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Card(String name, CardType cardType) {
		super();
		this.cardType = cardType;
		this.name = name;
	}
	
	public Card(String player, String room, String weapon) {
		super();
		this.player = player;
		this.room = room;
		this.weapon = weapon;
	}

	public CardType getCardType() {
		return cardType;
	}
	
	//GETTERS and SETTERS for testing only
	
	public String getName() {
		return name;
	}
	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getWeapon() {
		return weapon;
	}

	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}

	public void setName(String name) {
		this.name = name;
	}

}
