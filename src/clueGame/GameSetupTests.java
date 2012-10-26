package clueGame;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Card.CardType;

public class GameSetupTests {

	private static Board board;
	private static Player playerOne, computerPlayerOne, computerPlayerTwo;
	private static Card weaponCard, roomCard, playerCard;
	private static ArrayList<Card> deck;
	private static int weaponCounter, roomCounter, playerCounter;
	private ArrayList<String> dealtCard;
	
	@BeforeClass
	public static void setBoardAndPlayer() {
		board = new Board();
		
		playerOne = new HumanPlayer();
		computerPlayerOne = new ComputerPlayer();
		computerPlayerTwo = new ComputerPlayer();
		
		weaponCard = new Card();
		roomCard = new Card();
		playerCard = new Card();
		
		deck = new ArrayList<Card>();
		
		weaponCounter = 0;
		roomCounter = 0;
		playerCounter = 0;
		for(int i = 0; i < deck.size(); i++) {
			if(deck.get(i).getCardType() == CardType.WEAPON) {
				weaponCounter++;
			}
			else if(deck.get(i).getCardType() == CardType.ROOM) {
				roomCounter++;
			}
			else if(deck.get(i).getCardType() == CardType.PLAYER) {
				playerCounter++;
			}
		}
	}
	
	@Test
	public void testLoadingPlayers() {
		//Test human player input
		Assert.assertTrue(playerOne.getName() == "Taylor");
		Assert.assertTrue(playerOne.getName() == "Burgandy");
		//Test loading first computer player
		Assert.assertTrue(playerOne.getName() == "Borg Unit 1");
		Assert.assertTrue(playerOne.getName() == "Orange");
		//Test loading last computer player
		Assert.assertTrue(playerOne.getName() == "Borg Unit 5");
		Assert.assertTrue(playerOne.getName() == "Brown");
	}
	
	@Test
	public void testLoadingCards() {
		
		//Test size of deck
		Assert.assertEquals(deck.size(), 9);
		//Check deck for certain numbers of cards
		Assert.assertEquals(weaponCounter, 3);
		Assert.assertEquals(roomCounter, 3);
		Assert.assertEquals(playerCounter, 3);
		//Check if weaponCard is weapon
		Assert.assertTrue(weaponCard.getCardType() == CardType.WEAPON);
		//Check if roomCard is room
		Assert.assertTrue(roomCard.getCardType() == CardType.ROOM);
		//Check if playerCard is player
		Assert.assertTrue(playerCard.getCardType() == CardType.PLAYER);	
	}
	
	@Test
	public void testDealCards() {
		board.deal(dealtCard);
		//Test all cards are dealt
		Assert.assertEquals(dealtCard.size(), 9);
		//Test players have same amount of cards
		Assert.assertEquals(playerOne.getMyCards(), 3);
		Assert.assertEquals(computerPlayerOne.getMyCards(), 3);
		Assert.assertEquals(computerPlayerTwo.getMyCards(), 3);
		//Make sure players don't have multiple cards
		//TODO Add more tests here when more players are added
		if(computerPlayerOne.getMyCards().contains("Lead Pipe")) {
			Assert.assertFalse(playerOne.getMyCards().contains("Lead Pipe"));
			Assert.assertFalse(computerPlayerTwo.getMyCards().contains("Lead Pipe"));
		}
		if(computerPlayerOne.getMyCards().contains("Kitchen")) {
			Assert.assertFalse(playerOne.getMyCards().contains("Kitchen"));
			Assert.assertFalse(computerPlayerTwo.getMyCards().contains("Kitchen"));
		}
		if(computerPlayerOne.getMyCards().contains("Taylor")) {
			Assert.assertFalse(playerOne.getMyCards().contains("Taylor"));
			Assert.assertFalse(computerPlayerTwo.getMyCards().contains("Taylor"));
		}
	}

}
