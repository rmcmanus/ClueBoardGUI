package clueGame;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {
	private int x, y, counter = 0;
	private Board board;
	public DrawPanel(Board board) {
		this.board = board;
		this.x = x;
		this.y = y;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 800, 800);
		g.setColor(Color.black);
		for(BoardCell bc : board.getCells()) {
			bc.draw(g);
		}
		System.out.println("Called paint component");
	}
	public void translate(int dx, int dy) {
		x += dx;
		y += dy;
		// Must include this to see changes
		repaint();
	}
}

//g.drawRect((bc.row*25), (bc.col*25), 100, 100);