package client;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.TreeMap;

public class Entity {
	/////////////////////////////////////////////////////////////
	// String constants
	/////////////////////////////////////////////////////////////
	final String PARAM_VISIBLE = "visible";
	final String PARAM_X = "x";
	final String PARAM_Y = "y";
	final String PARAM_WALKABLE = "walkable";
	final String PARAM_TYPE = "type";
	final String PARAM_MINE = "mine";
	/////////////////////////////////////////////////////////////
	
	
	/////////////////////////////////////////////////////////////
	// Permanent parametrs
	/////////////////////////////////////////////////////////////
	int 	globalId;
	int 	type;
	String 	description;
	Tile 	tile;
	
	TreeMap<String, String> parametrs;
	/////////////////////////////////////////////////////////////
	
	
	Entity(int id) {
		this.globalId = id;
		parametrs = new TreeMap<String, String>();
	}
	
	boolean isVisible() {
		return !parametrs.containsKey(PARAM_VISIBLE);
	}
	
	void setTile(Tile tile) {
		this.tile = tile;
	}
	
	void setPosition(Point point) {
		parametrs.put(PARAM_X, point.x + "");
		parametrs.put(PARAM_Y, point.y + "");
	}
	
	void setParametr(String key, String value) {
		parametrs.put(key, value);
	}
	
	String getParametr(String key) {
		return parametrs.get(key);
	}
	
	void setX(String x) {
		parametrs.put(PARAM_X, x);
	}
	
	void setY(String y) {
		parametrs.put(PARAM_Y, y);
	}
	
	Point getPosition() {
		return new Point(
				Integer.parseInt(getParametr(PARAM_X)) * Global.tileWidth,
				Integer.parseInt(getParametr(PARAM_Y)) * Global.tileHeight
				);
	}
	
	void setTile(Tile[] tiles) {
		tile = tiles[Integer.parseInt(getParametr(PARAM_TYPE))%tiles.length];
		//tile = (getParametr(PARAM_TYPE).equals("1")) ? tile1 : tile0;
	}
	
	void updatePlayerPosition() {
		if(getParametr(PARAM_MINE).equals("true")) {
			Global.cameraPosition = getPosition();
		}
	}
	
	Rectangle getPositionRect() {
		Rectangle rect = new Rectangle(
				Integer.parseInt(parametrs.get(PARAM_X)) * Global.tileWidth, 
				Integer.parseInt(parametrs.get(PARAM_Y)) * Global.tileHeight, 
				Global.tileWidth,
				Global.tileHeight
			);
		return rect;
	}
}
