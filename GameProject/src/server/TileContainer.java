package server;

import java.util.ArrayList;
import java.util.List;

public class TileContainer {
	private ArrayList<Item> items;
	private ArrayList<Creature> creatures;
	private ArrayList<Projectile> projectiles;
	private Tile tile;

	public TileContainer(int x, int y, boolean walkable) {
		items = new ArrayList<Item>();
		projectiles = new ArrayList<Projectile>();
		creatures = new ArrayList<Creature>();
		
		tile = new Tile(x, y, walkable);
	}

	public String getInfo() {
		String ret = "";
		//ret += tile.getParameterStrings();
		for (Item e : items) {
			ret += e.getParameterStrings();
		}
		for (Projectile e : projectiles) {
			ret += e.getParameterStrings();
		}
		for (Creature e : creatures) {
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
	
	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}

	public void addItem(Item item) {
		items.add(item);
		
	}

	public void removeItem(Item item) {
		items.remove(item);
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public ArrayList<Creature> getCreatures() {
		return creatures;
	}

	public void setCreatures(ArrayList<Creature> creatures) {
		this.creatures = creatures;
	}

	public void setProjectiles(ArrayList<Projectile> projectiles) {
		this.projectiles = projectiles;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}
	
}
