package clueGame;

import java.awt.Graphics;

public class WalkwayCell extends BoardCell {
	
	public boolean isWalkway() {
		return true;
	}
	
	public void draw(Graphics g) {
		g.drawRect((this.row*25), (this.col*25), 100, 100);
	}
}
