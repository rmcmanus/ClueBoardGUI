package clueGame;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PartIITests {

	Board b;
	
	@Before public void init() {
		b = new Board();
	}

	@Test public void walkwayTest() {
		
		LinkedList<Integer> adjs = b.getAdjList(b.calcIndex(5, 6));
		Assert.assertEquals(4, adjs.size());
		adjs = b.getAdjList(b.calcIndex(5, 0));
		Assert.assertEquals(2, adjs.size());
		adjs = b.getAdjList(b.calcIndex(0, 12));
		Assert.assertEquals(1, adjs.size());
		adjs = b.getAdjList(b.calcIndex(3, 12));
		Assert.assertEquals(2, adjs.size());
		adjs = b.getAdjList(b.calcIndex(14, 2));
		Assert.assertEquals(3, adjs.size());
		adjs = b.getAdjList(b.calcIndex(5, 3));
		Assert.assertEquals(4, adjs.size());
		adjs = b.getAdjList(b.calcIndex(14, 4));
		Assert.assertEquals(4, adjs.size());
	}
	
@Test public void roomAdjTests() {
		
		LinkedList<Integer> adjs = b.getAdjList(b.calcIndex(21, 0));
		Assert.assertEquals(0, adjs.size());
		adjs = b.getAdjList(b.calcIndex(21, 10));
		Assert.assertEquals(0, adjs.size());
		adjs = b.getAdjList(b.calcIndex(0, 22));
		Assert.assertEquals(0, adjs.size());
		adjs = b.getAdjList(b.calcIndex(11, 6));
		Assert.assertEquals(1, adjs.size());
		adjs = b.getAdjList(b.calcIndex(10, 17));
		Assert.assertEquals(1, adjs.size());
		adjs = b.getAdjList(b.calcIndex(5, 15));
		Assert.assertEquals(1, adjs.size());
	}
	
@Test public void walkwayTargetTest() {
	b.calcTargets(b.calcIndex(6, 4), 1);
	Set<BoardCell> targets= b.getTargets();
	Assert.assertEquals(3, targets.size());
	Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(5, 4))));
	Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(6, 3))));
	Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(6, 5))));
	b.calcTargets(b.calcIndex(13, 22), 2);
	targets= b.getTargets();
	Assert.assertEquals(2, targets.size());
	Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(14, 21))));
	Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(13, 20))));
	b.calcTargets(b.calcIndex(13, 22), 3);
	targets= b.getTargets();
	Assert.assertEquals(4, targets.size());
	Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(13, 21))));
	Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(14, 22))));
	Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(13, 19))));
	Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(14, 20))));
	b.calcTargets(b.calcIndex(13, 22), 5);
	targets= b.getTargets();
	Assert.assertEquals(6, targets.size());
	Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(13, 21))));
	Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(14, 22))));
	Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(13, 19))));
	Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(14, 20))));
	Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(13, 17))));
	Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(14, 18))));
	}

@Test public void doorwayTargetTest() {
	b.calcTargets(b.calcIndex(6, 3), 2);
	Set<BoardCell> targets= b.getTargets();
	Assert.assertEquals(5, targets.size());
	Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(4, 3))));
	b.calcTargets(b.calcIndex(5, 8), 2);
	targets= b.getTargets();
	Assert.assertEquals(6, targets.size());
	Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(4, 8))));
	Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(6, 9))));
	Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(6, 7))));
	b.calcTargets(b.calcIndex(6, 3), 3);
	targets= b.getTargets();
	Assert.assertEquals(9, targets.size());
	Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(4, 3))));
	Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(4, 4))));
	Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(5, 3))));
	Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(6, 0))));
	b.calcTargets(b.calcIndex(5, 8), 1);
	targets= b.getTargets();
	Assert.assertEquals(4, targets.size());
	Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(5, 9))));
	Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(5, 7))));
	Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(6, 8))));
	Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(4, 8))));
	}
	
	public static void p(Object o) {
		System.out.println(o);
	}
}
