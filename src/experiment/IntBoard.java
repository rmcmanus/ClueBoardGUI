package experiment;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeSet;

public class IntBoard {
	public static int ROWS = 4;
	public static int COLS = 4;
	public static int GRID_PIECES;
	TreeSet<Integer> visited = new TreeSet<Integer>();
	LinkedList<Integer> temp = new LinkedList<Integer>();
	TreeSet<Integer> targets = new TreeSet<Integer>();
	private Map<Integer, LinkedList<Integer>> adjMtx;
	public IntBoard() {
		adjMtx = new HashMap<Integer, LinkedList<Integer>>();
		calcAdjacencies();
	}
	
	public void calcAdjacencies() {
		for (int x=0; x < ROWS; x++)
		{
			for (int y=0; y < COLS; y++)
			{
				LinkedList<Integer> a = new LinkedList<Integer>();;
				if (x != 0)
					a.add(calcIndex(x-1, y));
				if (y != 0)
					a.add(calcIndex(x, y-1));
				if (x != ROWS-1)
					a.add(calcIndex(x+1, y));
				if (y != COLS-1)
					a.add(calcIndex(x, y+1));
				adjMtx.put(calcIndex(x, y), a);
			 }
		}
	}
	
	public void calcTargets(int loc, int steps) {
		visited.clear();
		int counter = steps;
		targets.clear();
		LinkedList<Integer> middleMan;
		ListIterator<Integer> iter = adjMtx.get(loc).listIterator();
		ListIterator<Integer> iter2 = null;
		if (counter == 1)
		{
			while (iter.hasNext())
			{
				Integer i = (Integer)iter.next();
				targets.add(i);
			}
		}
		else
		{
			temp = adjMtx.get(loc);
			visited.add(loc);
			while (counter > 1)
			{
				middleMan = (LinkedList<Integer>)temp.clone();
				temp.clear();
				iter = middleMan.listIterator();
				while (iter.hasNext())
				{
					Integer i = iter.next();
					if (visited.contains(i))
						continue;
					else
					{
						visited.add(i);
						iter2 = adjMtx.get(i).listIterator();
						while (iter2.hasNext())
						{
							Integer j = (Integer) iter2.next();
							temp.add(j);
						}
					}
				}
				counter--;
			}
			iter = temp.listIterator();
			while (iter.hasNext())
			{
				Integer k = (Integer) iter.next();
				targets.add(k);
			}	
		}
		
	}
	
	public TreeSet<Integer> getTargets() {
		return targets;
	}
	
	public LinkedList<Integer> getAdjList(Integer index) {
		return adjMtx.get(index);
	}
	
	public static int calcIndex(int row, int col) {
		return row*COLS + col;
	}
	
}

