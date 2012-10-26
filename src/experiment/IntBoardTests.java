package experiment;

import java.util.LinkedList;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IntBoardTests {
	
	IntBoard ib;
	
	@Before public void init() {
		ib= new IntBoard();
	}
	
	@Test public void testIndex() {
		Assert.assertEquals(0, IntBoard.calcIndex(0,0));
		Assert.assertEquals(2, IntBoard.calcIndex(0,2));
		Assert.assertEquals(5, IntBoard.calcIndex(1,1));
		Assert.assertEquals(7, IntBoard.calcIndex(1,3));
		Assert.assertEquals(14, IntBoard.calcIndex(3,2));
	}


	@Test
	public void testAdjacency0()
	{
		LinkedList<Integer> testList = ib.getAdjList(0);
		Assert.assertTrue(testList.contains(4));
		Assert.assertTrue(testList.contains(1));
		Assert.assertEquals(2, testList.size());
	}

	@Test
	public void testTargets0_3()
	{
		ib.calcTargets(0, 3);
		TreeSet<Integer> targets= ib.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(12));
		Assert.assertTrue(targets.contains(9));
		Assert.assertTrue(targets.contains(1));
		Assert.assertTrue(targets.contains(6));
		Assert.assertTrue(targets.contains(3));
	}
	
	@Test
	public void testAdjacency5()
	{
		LinkedList<Integer> testList = ib.getAdjList(5);
		Assert.assertTrue(testList.contains(4));
		Assert.assertTrue(testList.contains(1));
		Assert.assertTrue(testList.contains(6));
		Assert.assertTrue(testList.contains(9));
		Assert.assertEquals(4, testList.size());
	}

	@Test
	public void testTargets1_1()
	{
		ib.calcTargets(1, 1);
		TreeSet<Integer> targets= ib.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(0));
		Assert.assertTrue(targets.contains(2));
		Assert.assertTrue(targets.contains(5));
	}
	
	@Test
	public void testAdjacency15()
	{
		LinkedList<Integer> testList = ib.getAdjList(15);
		Assert.assertTrue(testList.contains(14));
		Assert.assertTrue(testList.contains(11));
		Assert.assertEquals(2, testList.size());
	}

	@Test
	public void testTargets3_1()
	{
		ib.calcTargets(3, 1);
		TreeSet<Integer> targets= ib.getTargets();
		Assert.assertEquals(2, targets.size());
		Assert.assertTrue(targets.contains(2));
		Assert.assertTrue(targets.contains(7));
	}

}