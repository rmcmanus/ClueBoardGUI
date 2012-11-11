package clueGame;

<<<<<<< HEAD
import java.awt.Color;
import java.awt.Graphics;

=======
>>>>>>> fdb94791ae4721272d4da829325aaf4e999b73da
public class WalkwayCell extends BoardCell {
	
	public boolean isWalkway() {
		return true;
	}
	
<<<<<<< HEAD
	public void draw(Graphics g, Board board) {
		int x = board.CELLSIZE * col;
		int y = board.CELLSIZE * row;
		
		g.setColor(Color.yellow);
		g.fillRect(x, y, board.CELLSIZE, board.CELLSIZE);
		
		g.setColor(Color.black);
		g.drawRect(x, y, board.CELLSIZE, board.CELLSIZE);
=======
	public void draw() {
>>>>>>> fdb94791ae4721272d4da829325aaf4e999b73da
		
	}
}
