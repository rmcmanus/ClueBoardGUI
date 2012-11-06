package clueGame;

public class RoomCell extends BoardCell{
	
	public enum DoorDirection {UP, DOWN, LEFT, RIGHT, NONE};
	private DoorDirection doorDirection;
	private char initial;
	
	public boolean isRoom() {
		return true;
	}
	
	public boolean isDoorway(){
		if (doorDirection == DoorDirection.NONE)
			return false;
		else
			return true;
	}
	
	public void draw(){
		
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
		else
			doorDirection = DoorDirection.NONE;
	}
}
