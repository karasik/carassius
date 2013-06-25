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

	public boolean isVisibleBy(Entity entity) {
		return Math.hypot(tile.getX() - entity.getX(), tile.getY() - entity.getY()) <= Global.VISIBLE_DISTANCE;
	}

	public void addCreature(Creature c) {
		creatures.add(c);
	}

	public boolean isWalkable() {
		return tile.getParameter("walkable").equals("true");
	}

	public void removeCreature(Creature c) {
		creatures.remove(c);
	}
	
	public Tile getTile() {
		return tile;
	}

	public void addProjectile(Projectile pr) {
		projectiles.add(pr);
	}
	
	public void removeProjectile(Projectile pr) {
		projectiles.remove(pr);
	}
}
