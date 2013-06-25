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
	
	Entity(int id) {
		this.globalId = id;
		parametrs = new TreeMap<String, String>();
	}
	
	void setTile(Tile tile) {
		this.tile = tile;
	}
	
	void setPosition(Point point) {
		parametrs.put("x", point.x + "");
		parametrs.put("y", point.y + "");
	}
	
	void setParametr(String key, String value) {
		parametrs.put(key, value);
	}
	
	String getParametr(String key) {
		return parametrs.get(key);
	}
	
	void setX(String x) {
		parametrs.put("x", x);
	}
	
	void setY(String y) {
		parametrs.put("y", y);
	}
	
	Point getPosition() {
		return new Point(
				Integer.parseInt(parametrs.get("x")) * width,
				Integer.parseInt(parametrs.get("y")) * height
				);
	}
	
	void setTile(Tile tile0, Tile tile1) {
		tile = (parametrs.get("type").equals("0")) ? tile0 : tile1;
	}
	
	Rectangle getPositionRect() {
//		System.out.println(parametrs.get("x") + " " + parametrs.get("y"));
		Rectangle rect = new Rectangle(
				Integer.parseInt(parametrs.get("x")) * width, 
				Integer.parseInt(parametrs.get("y")) * height, 
				width,
				height
			);
//		System.out.println(rect);
		return rect;
	}
}
