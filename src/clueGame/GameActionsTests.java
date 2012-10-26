package clueGame;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Card.CardType;

public class GameActionsTests {
	
	private static Board board;
	private static Player playerOne, computerPlayerOne, computerPlayerTwo;
	private static Card weaponCard, roomCard, playerCard;
	private static ArrayList<Card> deck;
	private static int weaponCounter, roomCounter, playerCounter;
	private static Solution solution;
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
		
		solution = new Solution("Taylor", "Kitchen", "Lead Pipe");
	}

	@Test
	public void testAccusations() {
		board.selectAnswer();
		//Asserts accusation is correct
		Assert.assertTrue(board.checkAccusation(board.player, board.room, board.weapon));
		//Asserts parts or all of accusation are wrong
		board.player = "Ryan";
		Assert.assertFalse(board.checkAccusation(board.player, board.room, board.weapon));
		board.room = "Master Bedroom";
		Assert.assertFalse(board.checkAccusation(board.player, board.room, board.weapon));
		board.weapon = "Nightstick";
		Assert.assertFalse(board.checkAccusation(board.player, board.room, board.weapon));
	}
	
	@Test
	public void testTargetLocation() {
		int index = board.calcIndex(10, 7);
		board.calcTargets(index, 1);
		//Tests that a door is selected within target every time
		BoardCell selected = computerPlayerOne.pickLocation(board.getTargets());
		for(int i = 0; i < 15; i++) {
			Assert.assertTrue(selected.isRoom());
		}
		index = board.calcIndex(17, 14);
		board.calcTargets(index, 3);
		//Tests that we don't return to a room we were just in
		selected = computerPlayerTwo.pickLocation(board.getTargets());
		for(int i = 0; i < 15; i++) {
			Assert.assertFalse(selected == computerPlayerTwo.getVisited());
		}
	}
	
	@Test
	public void testTargetRandomSelection() {
		ComputerPlayer player = new ComputerPlayer();
		// Pick a location with no rooms in target, just three targets
		board.calcTargets(board.calcIndex(21, 16), 2);
		int loc_20_15Tot = 0;
		int loc_20_17Tot = 0;
		int loc_19_14Tot = 0;
		// Run the test 100 times
		for (int i=0; i<100; i++) {
			BoardCell selected = player.pickLocation(board.getTargets());
			if (selected == board.getCellAt(20, 15))
				loc_20_15Tot++;
			else if (selected == board.getCellAt(20, 17))
				loc_20_17Tot++;
			else if (selected == board.getCellAt(19, 14))
				loc_19_14Tot++;
			else
				fail("Invalid target selected");
		}
		// Ensure we have 100 total selections (fail should also ensure)
		assertEquals(100, loc_20_15Tot + loc_20_17Tot + loc_19_14Tot);
		// Ensure each target was selected more than once
		assertTrue(loc_20_15Tot > 10);
		assertTrue(loc_20_17Tot > 10);
		assertTrue(loc_19_14Tot > 10);							
	}
	
	@Test
	public void testCreateSuggestion() {
		computerPlayerOne.createSuggestion();
	}

}
