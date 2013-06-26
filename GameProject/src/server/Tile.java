package server;

import java.util.ArrayList;
import java.util.List;

public class Tile extends Entity {
	public Tile(int x, int y, boolean walkable) {
		putParameter("x", x + "");
		putParameter("y", y + "");
		putParameter("walkable", walkable + "");
		putParameter("visible", "true");
		putParameter("type", walkable ? "1" : "0");
	}
	
	public boolean isWalkable() {
		return this.getParameter("walkable").equals("true");
	}

	@Override
	public void setCoord(int x, int y) {
		
	}

	@Override
	public void changeCoord(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
