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
	private static int weaponCounter, roomCounter, playerCounter;
	
	@BeforeClass
	public static void setBoardAndPlayer() {
		board = new Board();
		
		playerOne = board.players.get(0);
		computerPlayerOne = board.players.get(1);
		computerPlayerTwo = board.players.get(2);
		
		weaponCard = board.dealCards.get(3);
		roomCard = board.dealCards.get(6);
		playerCard = board.dealCards.get(0);
		
		weaponCounter = 0;
		roomCounter = 0;
		playerCounter = 0;
		for(int i = 0; i < board.dealCards.size(); i++) {
			if(board.dealCards.get(i).getCardType() == CardType.WEAPON) {
				weaponCounter++;
			}
			else if(board.dealCards.get(i).getCardType() == CardType.ROOM) {
				roomCounter++;
			}
			else if(board.dealCards.get(i).getCardType() == CardType.PLAYER) {
				playerCounter++;
			}
		}
	}
	
	@Test
	public void testLoadingPlayers() {
		//Test human player input
		Assert.assertEquals(playerOne.getName(), "Taylor");
		Assert.assertEquals(playerOne.getColor(), "Burgandy");
		//Test loading first computer player
		Assert.assertEquals(computerPlayerOne.getName(), "Borg Unit 1");
		Assert.assertEquals(computerPlayerOne.getColor(), "Orange");
		//Test loading last computer player
		Assert.assertEquals(computerPlayerTwo.getName(), "Borg Unit 5");
		Assert.assertEquals(computerPlayerTwo.getColor(), "Brown");
	}
	
	@Test
	public void testLoadingCards() {
		//Test size of deck
		Assert.assertEquals(board.dealCards.size(), 9);
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
		board.deal();
		//Test all cards are dealt
		Assert.assertEquals(board.dealCards.size(), 9);
		//Test players have same amount of cards
		System.out.println(playerOne.getMyCards().size());
		System.out.println(computerPlayerOne.getMyCards().size());
		System.out.println(computerPlayerTwo.getMyCards().size());
		
		Assert.assertEquals(playerOne.getMyCards().size(), 3);
		Assert.assertEquals(computerPlayerOne.getMyCards().size(), 3);
		Assert.assertEquals(computerPlayerTwo.getMyCards().size(), 3);
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
