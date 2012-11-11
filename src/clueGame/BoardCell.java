package clueGame;

<<<<<<< HEAD
import java.awt.Graphics;

=======
>>>>>>> fdb94791ae4721272d4da829325aaf4e999b73da
public abstract class BoardCell {
	
	protected int row;
	protected int col;
	
	protected boolean isWalkway() {
		return false;
	}

	protected boolean isRoom() {
		return false;
	}
	
	protected boolean isDoorway() {
		return false;
	}
	
<<<<<<< HEAD
	protected void draw(Graphics g, Board board) {
=======
	protected void draw() {
		
>>>>>>> fdb94791ae4721272d4da829325aaf4e999b73da
	}
	
	protected void setRow(int row){
		this.row = row;
	}
	
	protected void setCol(int col){
		this.col = col;
	}
	
	public String toString() {
		return "("+row+","+col+")";
	}
}
