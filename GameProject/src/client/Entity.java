package client;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.TreeMap;

public class Entity {
	int 	globalId;
	int 	type;
	String 	description;
	Tile 	tile;
	
	TreeMap<String, String> parametrs;
	
	static int width = 50;
	static int height = 50;
	
	Entity() {
		parametrs = new TreeMap<String, String>();
	}
	
	void setTile(Tile tile) {
		this.tile = tile;
	}
	
	void setPosition(Point point) {
		parametrs.put("coordx", point.x + "");
		parametrs.put("coordy", point.y + "");
	}
	
	void setParametr(String key, String value) {
		parametrs.put(key, value);
	}
	
	String getParametr(String key) {
		return parametrs.get(key);
	}
	
	void setX(String x) {
		parametrs.put("coordx", x);
	}
	
	void setY(String y) {
		parametrs.put("coordy", y);
	}
	
	Point getPosition() {
		return new Point(
				Integer.parseInt(parametrs.get("coordx")),
				Integer.parseInt(parametrs.get("coordy"))
				);
	}
	
	Rectangle getPositionRect() {
		return new Rectangle(
				Integer.parseInt(parametrs.get("coordx")), 
				Integer.parseInt(parametrs.get("coordy")), 
				width,
				height
			);
	}
}
