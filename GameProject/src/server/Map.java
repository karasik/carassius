package server;

import java.util.ArrayList;
import java.util.TreeMap;

public class Map {
	private TileContainer[][] tileMatrix;
	private ArrayList<TileContainer> tileList;
	private ArrayList<Player> players;
	private TreeMap<Integer, Tile> tileMap;
	
	public Map(int n, int m) {
		tileMatrix = MapGenerator.generateNormalMap(n, m);
		tileList = new ArrayList<TileContainer>();
		for (TileContainer[] u : tileMatrix) {
			for (TileContainer v : u) {
				tileList.add(v);
			}
		}
		players = new ArrayList<Player>();
		tileMap = new TreeMap<Integer, Tile>();
		for (TileContainer c : tileList) {
			tileMap.put(c.getTile().getGlobalId(), c.getTile());
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
}
