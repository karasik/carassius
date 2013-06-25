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
				
				for (int i=0; i<playerSockets.size(); i++) {
					PrintWriter stream = playerStreams.get(i);
					// говорим кто мы (персональный подход к каждому клиенту)
					map.getPlayer(i).putParameter("mine", "true");
					// выдаем все клетки
					for (TileContainer tile : map.getTileList()) {
						if (tile.isVisibleBy(map.getPlayer(i))) {
							stream.print(tile.getInfo());
						}
					}
					// мы меняемся
					map.getPlayer(i).putParameter("mine", "false");
					stream.println("RENDER");
				}				
				
				
				Thread.sleep(100000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
