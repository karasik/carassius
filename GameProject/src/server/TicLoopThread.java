package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class TicLoopThread extends Thread {
	private Map map;
	private ArrayList<Socket> playerSockets;
	private ArrayList<PrintWriter> playerStreams;
	private char[] button;
	private boolean[] wasButton;
	
	public TicLoopThread(Map map, ArrayList<Socket> playerSockets, char[] button, boolean[] wasButton) throws IOException {
		this.map = map;
		this.playerSockets = playerSockets;
		playerStreams = new ArrayList<PrintWriter>();
		for (Socket s : playerSockets) {
			// true вторым аргументом означает autoflush
			playerStreams.add(new PrintWriter(s.getOutputStream(), true));
		}
		this.button = button;
		this.wasButton = wasButton;
	}

	public void run() {
		try {
			while (true) {
				makePlayersTurns();
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
				
				
				Thread.sleep(10);
				Global.time++;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void makePlayersTurns() {
		for (int i=0; i<Global.NUM_PLAYERS; i++) {
			if (!wasButton[i]) continue;
			boolean success = false;
			switch (button[i]) {
			case 'w':
				success = map.tryToChangeCreatureCoordDiff(map.getPlayer(i), 0, -1);
				break;
			case 'a':
				success = map.tryToChangeCreatureCoordDiff(map.getPlayer(i), -1, 0);
				break;
			case 's':
				success = map.tryToChangeCreatureCoordDiff(map.getPlayer(i), 0, 1);
				break;
			case 'd':
				success = map.tryToChangeCreatureCoordDiff(map.getPlayer(i), 1, 0);
				break;
			default:
				success = true;
			}
			if (success) {
				synchronized (wasButton) {
					wasButton[i] = false;
				}
			}
		}
	}
}
