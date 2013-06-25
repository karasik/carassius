package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class TicLoopThread extends Thread {
	private Map map;
	private ArrayList<Socket> playerSockets;
	private ArrayList<PrintWriter> playerStreams;
	
	public TicLoopThread(Map map, ArrayList<Socket> playerSockets) throws IOException {
		this.map = map;
		this.playerSockets = playerSockets;
		playerStreams = new ArrayList<PrintWriter>();
		for (Socket s : playerSockets) {
			// true вторым аргументом означает autoflush
			playerStreams.add(new PrintWriter(s.getOutputStream(), true));
		}
	}

	public void run() {
		try {
			while (true) {
				
				for (PrintWriter stream : playerStreams) {
					for (TileContainer tile : map.getTileList()) {
						stream.print(tile.getInfo());
					}
					stream.println("RENDER");
				}				
				
				
				Thread.sleep(100000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
