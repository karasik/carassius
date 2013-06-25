package server;

import java.util.ArrayList;

public class Map {
	private TileContainer[][] tileMatrix;
	private ArrayList<TileContainer> tileList;
	private ArrayList<Player> players;
	
	public Map(int n, int m) {
		tileMatrix = MapGenerator.generateNormalMap(n, m);
		tileList = new ArrayList<TileContainer>();
		for (TileContainer[] u : tileMatrix) {
			for (TileContainer v : u) {
				tileList.add(v);
			}
		}
		players = new ArrayList<Player>();
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
}
