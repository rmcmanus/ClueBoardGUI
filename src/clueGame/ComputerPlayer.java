package clueGame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import clueGame.Card.CardType;

public class ComputerPlayer extends Player {
	private char lastRoomVisited;
	public Set<Card> seenCards = new HashSet<Card>();
	
	public String playerSuggest = null;
	public String weaponSuggest = null;
	public String roomSuggest = null;
	
	public ComputerPlayer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Card> createSuggestion() {
		Random randCard = new Random();
		ArrayList<Card> suggestList = new ArrayList<Card>();
		boolean satisfied = false;
		
		RoomCell tempBoardCell = (RoomCell) this.getVisited();
		suggestList.add(new Card(Board.rooms.get(tempBoardCell.getInitial()), CardType.ROOM));
		
		while(!satisfied) {
			int index = randCard.nextInt(Board.allCards.size());
			if(!seenCards.contains(Board.allCards.get(index)) && (Board.allCards.get(index).getCardType() == CardType.PLAYER)) {
				suggestList.add(Board.allCards.get(index));
				satisfied = true;
			}	
		}
		satisfied = false;
		
		while(!satisfied) {
			int index = randCard.nextInt(Board.allCards.size());
			if(!seenCards.contains(Board.allCards.get(index)) && (Board.allCards.get(index).getCardType() == CardType.WEAPON)) {
				suggestList.add(Board.allCards.get(index));
				satisfied = true;
			}
		}
		
		return suggestList;
	}
	
}
