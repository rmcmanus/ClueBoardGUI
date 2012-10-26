package clueGame;

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
	
	protected void draw() {
		
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
