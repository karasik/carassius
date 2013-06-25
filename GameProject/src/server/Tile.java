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

}
