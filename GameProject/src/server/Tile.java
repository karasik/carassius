package server;

import java.util.ArrayList;
import java.util.List;

public class Tile extends Entity {
	private boolean walkable;
	
	public Tile(int x, int y, boolean walkable) {
		putParameter("x", x + "");
		putParameter("y", y + "");
		this.walkable = walkable;
		putParameter("type", walkable ? "1" : "0");
	}
	
	public boolean isWalkable() {
		return walkable;
	}

	@Override
	public void setCoord(int x, int y) {
		
	}

	@Override
	public void changeCoord(int x, int y) {
		
	}

}
