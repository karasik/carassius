package server;

import java.util.ArrayList;
import java.util.TreeMap;

public class Map {
	private TileContainer[][] tileMatrix;
	private ArrayList<TileContainer> tileList;
	private ArrayList<Player> players;
	private TreeMap<Integer, Entity> entityMap;
	private static Map instance;
	
	public static Map getInstance() {
		return instance;
	}
	
	public static void initialize(int n, int m) {
		if (instance == null) {
			instance = new Map();
			instance.generateMap(n, m);
		}
	}
	
	private Map() {
		entityMap = new TreeMap<Integer, Entity>();
		players = new ArrayList<Player>();
	}
	
	private void generateMap(int n, int m) {
		tileMatrix = MapGenerator.generateNormalMap(n, m);
		tileList = new ArrayList<TileContainer>();
		for (TileContainer[] u : tileMatrix) {
			for (TileContainer v : u) {
				tileList.add(v);
			}
		}
	}
	
	public TileContainer[][] getTileMatrix() {
		return tileMatrix;
	}
	
	public ArrayList<TileContainer> getTileList() {
		return tileList;
	}

	public void addWarrior(int i) {
		int x = 3, y = 3 + i;
		Warrior c = new Warrior(i, x, y);
		
		players.add(i, (Player)c);
		tileMatrix[x][y].addCreature((Creature)c);
	}

	public Player getPlayer(int i) {
		return players.get(i);
	}

	public boolean tryToChangeCreatureCoordDiff(Creature c, int dx, int dy) {
		int x = c.getX(), y = c.getY();
		if (tileMatrix[x + dx][y + dy].isWalkable()) {
			tileMatrix[x + dx][y + dy].addCreature(c);
			tileMatrix[x][y].removeCreature(c);
			return c.moveTo(x + dx, y + dy);
		}
		return false;
	}
	
	public void putEntityInMap(Entity e) {
		entityMap.put(e.getGlobalId(), e);
	}
	
	public Entity getEntityFromId(int globalId) {
		return entityMap.get(globalId);
	}

	public void addProjectile(Projectile pr) {
		tileMatrix[pr.getX()][pr.getY()].addProjectile(pr);
	}
	
	public void removeProjectile(Projectile pr) {
		tileMatrix[pr.getX()][pr.getY()].removeProjectile(pr);
	}
}
