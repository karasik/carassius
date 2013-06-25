package client;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

class Global {
	
	static Map map;
	static Point cameraPosition;
	static Rectangle visibleFrame;
	static int tileWidth = 50;
	static int tileHeight = 50;
	
	static BufferedReader socketReader = null;
	static PrintWriter socketWriter = null;
	
	static Tile[] tiles = null;
}

public class Client {
	
	KeyProcessor keyProcessor =
			new KeyProcessor();
	
	FrameEventsProcessor eventsProcessor = 
			new FrameEventsProcessor();
	
	MouseProcessor mouseProcessor = 
			new MouseProcessor();
	
	public Client() throws IOException {
		
		Global.tiles = new Tile[] {
				new Tile("tank.jpg"),
				new Tile("grass.jpg"),
				new Tile("picture.jpg")
		};
		
//		Entity e = new Entity(100500);
//		e.setTile(Global.tiles);
//		Point point = new Point(300, 100);
//		e.setPosition(point);
		
		Global.cameraPosition = new Point(0, 0);
		Global.visibleFrame = new Rectangle(0, 0, 640, 480);
		Global.map = new Map();
		
		//map = new Map();
//		Global.map.addEntity(e);
		
		Renderer rend = new Renderer();
		rend.setVisible(true);
		
		rend.addKeyListener(keyProcessor);
		rend.addComponentListener(eventsProcessor);
		rend.addMouseListener(mouseProcessor);
		
//		SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                Renderer rend = new Renderer();
//                rend.setVisible(true);
//            }
//        });
		
		Socket socket = null;
		try {
			//socket = new Socket("localhost", 8080);
			socket = new Socket("89.249.160.150", 8080);
			Global.socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Global.socketWriter = new PrintWriter( new OutputStreamWriter( socket.getOutputStream()), true );
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		while(true) {
			
			String message = Global.socketReader.readLine();
			if(message.equals("RENDER")) {
//				System.out.println("----------");
				keyProcessor.informServer();
				rend.repaint();
			} else {
				int globalId = Integer.parseInt(message);
				
				Entity en = Global.map.getEntity(globalId);
				
				
				int N = Integer.parseInt(Global.socketReader.readLine());
				
				for(int i = 0; i<N; i++) {
					String key = Global.socketReader.readLine();
					String value = Global.socketReader.readLine();
					
					en.setParametr(key, value);
					
					if(key.equals(Constants.PARAM_MINE) )
						Global.map.player = new Player(en);
				}
			}
		}
		
		
	}
	
	
	
	Map map;
	
	
	public static void main(String[] argv) throws IOException {
		Client client = new Client();
	}
}
