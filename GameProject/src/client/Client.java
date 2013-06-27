package client;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

class Global {
	
	static Map map;
	static Point cameraPosition;
	static Rectangle visibleFrame;
	static int tileWidth = 50;
	static int tileHeight = 50;
	static int distanceOfView = 8;
	static int chunkSize = 8;
	static int chunksFrameToLoad = 1;
	
	static int tickCounter = 0;
	
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
				new Tile("picture.jpg"),
				new Tile("fail.jpg")
		};
		
		Global.cameraPosition = new Point(0, 0);
		Global.visibleFrame = new Rectangle(0, 0, 640, 480);
		
		
		Renderer rend = new Renderer();
		rend.setVisible(true);
		
		rend.addKeyListener(keyProcessor);
		rend.addComponentListener(eventsProcessor);
		rend.getComponent(0).addMouseListener(mouseProcessor);
		
		Socket socket = null;
		try {
			socket = new Socket("localhost", 8080);
<<<<<<< HEAD
//			socket = new Socket("89.249.160.150", 8080);
=======
			//socket = new Socket("89.249.160.150", 8080);
>>>>>>> 06ae4f7efb26e3fb180cb85ce5f30520e03cf91c
			Global.socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Global.socketWriter = new PrintWriter( new OutputStreamWriter( socket.getOutputStream()), true );
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		{
			int ysize = Integer.parseInt(Global.socketReader.readLine());
			int xsize = Integer.parseInt(Global.socketReader.readLine());
			
			ArrayList<Entity> list = new ArrayList<Entity>();
			
			for(int y =0 ; y<ysize; y++) {
				for(int x =0 ; x < xsize; x++) {
					int globalId = Integer.parseInt(Global.socketReader.readLine());
					
					Entity en = new Entity(globalId);
					
					int N = Integer.parseInt(Global.socketReader.readLine());
				
					for(int i = 0; i<N; i++) {
						String key = Global.socketReader.readLine();
						String value = Global.socketReader.readLine();
					
						en.setParametr(key, value);
					}
					en.setParametr(Constants.PARAM_TICK, Global.tickCounter+"");
					
					list.add(en);
				}
			}
			
			Global.map = new Map(ysize, xsize, list);
			
			Global.tickCounter++;
		}
		
		
		while(true) {
			String message = Global.socketReader.readLine();
			if(message.equals("RENDER")) {
				Global.tickCounter ++;
				keyProcessor.informServer();
				rend.repaint();
			} else {
				int globalId = Integer.parseInt(message);
				
				synchronized(Global.map) {
					
					Entity en = Global.map.getEntity(globalId);
				
					int N = Integer.parseInt(Global.socketReader.readLine());
				
					for(int i = 0; i<N; i++) {
						String key = Global.socketReader.readLine();
						String value = Global.socketReader.readLine();
					
						en.setParametr(key, value);
					
						if(key.equals(Constants.PARAM_MINE) && value.equals("true") )
							Global.map.player = new Player(en);
					}
					en.setParametr(Constants.PARAM_TICK, Global.tickCounter+"");
				
					Global.map.moveEntity(en, en.getPosition().y, en.getPosition().x);
				}
			}
		}
		
		
	}
	
	Map map;
	
	public static void main(String[] argv) throws IOException {
		Client client = new Client();
	}
}
