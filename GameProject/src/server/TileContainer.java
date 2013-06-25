package server;

import java.util.ArrayList;
import java.util.List;

public class TileContainer {
	private List<Item> items;
	private List<Creature> creatures;
	private List<Projectile> projectiles;
	private Tile tile;

	public TileContainer(int x, int y, boolean walkable) {
		items = new ArrayList<Item>();
		projectiles = new ArrayList<Projectile>();
		creatures = new ArrayList<Creature>();
		
		tile = new Tile(x, y, walkable);
	}

	public String getInfo() {
		String ret = "";
		ret += tile.getParameterStrings();
		for (Entity e : items) {
			ret += e.getParameterStrings();
		}
		for (Entity e : projectiles) {
			ret += e.getParameterStrings();
		}
		for (Entity e : creatures) {
			ret += e.getParameterStrings();
		}
		return ret;
	}
}
