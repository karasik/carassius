package client;

import java.awt.Point;
import java.awt.geom.Point2D;

import javax.swing.SwingUtilities;

class Global {
	
	static Map map;
	static Point cameraPosition;
	
}

public class Client {
	
	public Client() {
		Tile tile = new Tile("picture.jpg");
		Entity e = new Entity(tile);
		
		Point point = new Point(100, 100);
		e.setPosition(point);
		
		Global.cameraPosition = new Point(0, 0);
		Global.map = new Map();
		
		//map = new Map();
		Global.map.addEntity(e);
		
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Renderer rend = new Renderer();
                rend.setVisible(true);
            }
        });
	}
	
	
	
	Map map;
	
	
	public static void main(String[] argv) {
		Client client = new Client();
	}
}
