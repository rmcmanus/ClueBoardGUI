package clueGame;

import java.awt.Color;
import java.awt.Graphics;

public class RoomCell extends BoardCell{
	
	public enum DoorDirection {UP, DOWN, LEFT, RIGHT, NAME, NONE};
	private DoorDirection doorDirection;
	private char initial;
	public final int DOOR_WIDTH = 5;
	
	public boolean isRoom() {
		return true;
	}
	
	public boolean isDoorway(){
		if (doorDirection == DoorDirection.NONE)
			return false;
		else
			return true;
	}
	
	public void draw(Graphics g, Board board){
		int x = board.CELLSIZE * col;
		int y = board.CELLSIZE * row;
		
		g.setColor(Color.lightGray);
		g.fillRect(x, y, board.CELLSIZE, board.CELLSIZE);
		
		g.setColor(Color.blue);
		switch(doorDirection) {
		
		case UP:
			g.fillRect(x, y, board.CELLSIZE, DOOR_WIDTH);
			break;
		case DOWN:
			g.fillRect(x, y+board.CELLSIZE - DOOR_WIDTH, board.CELLSIZE, DOOR_WIDTH);
			break;
		case LEFT:
			g.fillRect(x, y, DOOR_WIDTH, board.CELLSIZE);
			break;
		case RIGHT:
			g.fillRect(x+board.CELLSIZE - DOOR_WIDTH, y, DOOR_WIDTH, board.CELLSIZE);
			break;
		case NAME:
			g.drawString(board.rooms.get(initial), x-1, y-3);
		default:
			break;
		}	
	}
	
	public DoorDirection getDoorDirection() {
		return doorDirection;
	}
	
	public char getInitial() {
		return initial;
	}
	
	public void setInitial(char initial) {
	    this.initial = initial;
	}

	public void setDoorDirection(char dir) {
		if (dir == 'U')
			doorDirection = DoorDirection.UP;
		else if (dir == 'D')
			doorDirection = DoorDirection.DOWN;
		else if (dir == 'L')
			doorDirection = DoorDirection.LEFT;
		else if (dir == 'R')
			doorDirection = DoorDirection.RIGHT;
		else if (dir == 'N')
			doorDirection = DoorDirection.NAME;
		else
			doorDirection = DoorDirection.NONE;
	}
}
