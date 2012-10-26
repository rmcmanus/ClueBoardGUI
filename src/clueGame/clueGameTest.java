package clueGame;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class clueGameTest {
	
	Board b;
	
	@Before public void init() {
		b = new Board();
	}
	
	@Test public void roomsTest() {
		Assert.assertEquals(b.getCells().size(), (23*22));
	}
	
	@Test public void mappingTest() {
		Assert.assertEquals(b.getRoomName('B'), "Ballroom");
		Assert.assertEquals(b.getRoomName('C'), "Conservatory");
		Assert.assertEquals(b.getRoomName('D'), "Dining Room");
		Assert.assertEquals(b.getRoomName('H'), "Hall");
		Assert.assertEquals(b.getRoomName('K'), "Kitchen");
		Assert.assertEquals(b.getRoomName('L'), "Library");
		Assert.assertEquals(b.getRoomName('O'), "Lounge");
		Assert.assertEquals(b.getRoomName('R'), "Billiards Room");
		Assert.assertEquals(b.getRoomName('S'), "Study");
		Assert.assertEquals(b.getRoomName('X'), "Closet");
		Assert.assertEquals(b.getRoomName('W'), "Walkway");
	}
	
	@Test public void roomConfigTest() {
		Assert.assertEquals(b.getNumColumns(), 23);
		Assert.assertEquals(b.getNumRows(), 22);
	}
	
	@Test public void doorTest() {
		RoomCell bc = b.getRoomCellAt(4, 3);
		Assert.assertTrue(bc.isRoom());
		Assert.assertTrue(bc.isDoorway());
		Assert.assertEquals(bc.getDoorDirection(), RoomCell.DoorDirection.DOWN);
		bc = b.getRoomCellAt(14, 11);
		Assert.assertTrue(bc.isRoom());
		Assert.assertTrue(bc.isDoorway());
		Assert.assertEquals(bc.getDoorDirection(), RoomCell.DoorDirection.UP);
		bc = b.getRoomCellAt(9, 6);
		Assert.assertTrue(bc.isRoom());
		Assert.assertTrue(bc.isDoorway());
		Assert.assertEquals(bc.getDoorDirection(), RoomCell.DoorDirection.RIGHT);
		bc = b.getRoomCellAt(2, 13);
		Assert.assertTrue(bc.isRoom());
		Assert.assertTrue(bc.isDoorway());
		Assert.assertEquals(bc.getDoorDirection(), RoomCell.DoorDirection.LEFT);
		bc = b.getRoomCellAt(1, 1);
		Assert.assertTrue(bc.isRoom());
		Assert.assertFalse(bc.isDoorway());
		Assert.assertEquals(bc.getDoorDirection(), RoomCell.DoorDirection.NONE);
	}
	
	@Test public void initialTest() {
		RoomCell bc = b.getRoomCellAt(1, 1);
		Assert.assertEquals('C', bc.getInitial());
		bc = b.getRoomCellAt(1, 9);
		Assert.assertEquals('R', bc.getInitial());
		bc = b.getRoomCellAt(1, 14);
		Assert.assertEquals('L', bc.getInitial());
		bc = b.getRoomCellAt(1, 21);
		Assert.assertEquals('S', bc.getInitial());
		bc = b.getRoomCellAt(10, 3);
		Assert.assertEquals('B', bc.getInitial());
		bc = b.getRoomCellAt(10, 21);
		Assert.assertEquals('H', bc.getInitial());
		bc = b.getRoomCellAt(19, 1);
		Assert.assertEquals('K', bc.getInitial());
		bc = b.getRoomCellAt(19, 14);
		Assert.assertEquals('D', bc.getInitial());
		bc = b.getRoomCellAt(21, 21);
		Assert.assertEquals('O', bc.getInitial());
	}
	
	@Test public void testIndex() {
		Assert.assertEquals(0, b.calcIndex(0, 0));
		Assert.assertEquals(21, b.calcIndex(0, 21));
		Assert.assertEquals(460, b.calcIndex(20,0));
		Assert.assertEquals(481, b.calcIndex(20,21));
	}
}
