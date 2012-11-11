package clueGame;

import java.awt.Color;
import java.awt.Graphics;

public class WalkwayCell extends BoardCell {
	
	public boolean isWalkway() {
		return true;
	}
	
	public void draw(Graphics g, Board board) {
		int x = board.CELLSIZE * col;
		int y = board.CELLSIZE * row;
		
		g.setColor(Color.yellow);
		g.fillRect(x, y, board.CELLSIZE, board.CELLSIZE);
		
		g.setColor(Color.black);
		g.drawRect(x, y, board.CELLSIZE, board.CELLSIZE);
		
	}
}
