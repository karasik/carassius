package client;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.TreeMap;

public class Entity {
	
	
	/////////////////////////////////////////////////////////////
	// Permanent parametrs
	/////////////////////////////////////////////////////////////
	int 	globalId;
	String 	description;
	Tile 	tile;
	
	TreeMap<String, String> parametrs;
	/////////////////////////////////////////////////////////////
	
	
	Entity(int id) {
		this.globalId = id;
		parametrs = new TreeMap<String, String>();
	}
	
	boolean isVisible() {
		return !parametrs.containsKey(Constants.PARAM_VISIBLE);
	}
	
	void setTile(Tile tile) {
		this.tile = tile;
	}
	
	void setPosition(Point point) {
		parametrs.put(Constants.PARAM_X, point.x + "");
		parametrs.put(Constants.PARAM_Y, point.y + "");
	}
	
	void setParametr(String key, String value) {
		parametrs.put(key, value);
	}
	
	String getParametr(String key) {
		return parametrs.get(key);
	}
	
	void setX(String x) {
		parametrs.put(Constants.PARAM_X, x);
	}
	
	void setY(String y) {
		parametrs.put(Constants.PARAM_Y, y);
	}
	
	Point getPosition() {
		return new Point(
				Integer.parseInt(getParametr(Constants.PARAM_X)) * Global.tileWidth,
				Integer.parseInt(getParametr(Constants.PARAM_Y)) * Global.tileHeight
				);
	}
	
	int getType() {
		return Integer.parseInt(getParametr(Constants.PARAM_TYPE));
	}
	
	void setTile(Tile[] tiles) {
		tile = tiles[Integer.parseInt(getParametr(Constants.PARAM_TYPE))%tiles.length];
		//tile = (getParametr(PARAM_TYPE).equals("1")) ? tile1 : tile0;
	}
	
	void updatePlayerPosition() {
		if(getParametr(Constants.PARAM_MINE).equals("true")) {
			Global.cameraPosition = getPosition();
		}
	}
	
	Rectangle getPositionRect() {
		Rectangle rect = new Rectangle(
				Integer.parseInt(parametrs.get(Constants.PARAM_X)) * Global.tileWidth, 
				Integer.parseInt(parametrs.get(Constants.PARAM_Y)) * Global.tileHeight, 
				Global.tileWidth,
				Global.tileHeight
			);
		return rect;
	}
}
