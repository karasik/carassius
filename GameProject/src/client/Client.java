package client;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.SwingUtilities;

class Global {
	
	static Map map;
	static Point cameraPosition;
	
	static Tile tile0;
	static Tile tile1;
	
}

public class Client {
	
	public Client() throws IOException {
		
		Global.tile0 = new Tile("picture.jpg");
		Global.tile1 = new Tile("grass.jpg");
		
		Entity e = new Entity(100500);
		e.setTile(Global.tile0);
		Point point = new Point(300, 100);
		e.setPosition(point);
		
		Global.cameraPosition = new Point(0, 0);
		Global.map = new Map();
		
		//map = new Map();
		Global.map.addEntity(e);
		
		Renderer rend = new Renderer();
		rend.setVisible(true);
		
//		SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                Renderer rend = new Renderer();
//                rend.setVisible(true);
//            }
//        });
		
		
		Socket socket = null;
		BufferedReader reader = null;
		try {
			socket = new Socket("localhost", 8080);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		while(true) {
			
			String message = reader.readLine();
			if(message.equals("RENDER")) {
				rend.repaint();
			} else {
				int globalId = Integer.parseInt(message);
				
				Entity en = Global.map.getEntity(globalId);
				
				
				int N = Integer.parseInt(reader.readLine());
				
				for(int i = 0; i<N; i++) {
					String key = reader.readLine();
					String value = reader.readLine();
					
					en.setParametr(key, value);
					
					//System.out.println(key + " " + value);
				}
			}
		}
		
		
	}
	
	
	
	Map map;
	
	
	public static void main(String[] argv) throws IOException {
		Client client = new Client();
	}
}
