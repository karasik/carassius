package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class TicLoopThread extends Thread {
	private ArrayList<Socket> playerSockets;
	private ArrayList<PrintWriter> playerStreams;

	public TicLoopThread(ArrayList<Socket> playerSockets) throws IOException {
		this.playerSockets = playerSockets;
		playerStreams = new ArrayList<PrintWriter>();
		for (Socket s : playerSockets) {
			// true вторым аргументом означает autoflush
			playerStreams.add(new PrintWriter(s.getOutputStream(), false));
		}
	}

	public void run() {
		try {
			sendTiles();
			while (true) {
				Map.getInstance().moveProjectiles();
				Map.getInstance().makePlayersTurns();
				sendInfo();

				Thread.sleep(Global.DELAY);
				Global.time++;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void sendTiles() {
		for (int i = 0; i < playerSockets.size(); i++) {
			PrintWriter stream = playerStreams.get(i);
			// выдаем тайлы
			stream.println(Map.getInstance().getTileMatrix().length);
			stream.println(Map.getInstance().getTileMatrix()[0].length);
			for (TileContainer tile : Map.getInstance().getTileList()) {
				stream.print(tile.getTile().getParameterStrings());
			}
			stream.println("RENDER");
		}
	}

	private void sendInfo() {
		for (int i = 0; i < playerSockets.size(); i++) {
			PrintWriter stream = playerStreams.get(i);
			// говорим кто мы (персональный подход к каждому клиенту)
			Map.getInstance().getPlayer(i).putParameter("mine", "true");
			// выдаем все клетки (без тайлов!)
			for (TileContainer tile : Map.getInstance().getTileList()) {
				if (tile.isVisibleBy(Map.getInstance().getPlayer(i))) {
					stream.print(tile.getInfo());
				}
			}
			// мы меняемся
			Map.getInstance().getPlayer(i).putParameter("mine", "false");
			stream.println("RENDER");
			stream.flush();
		}

	}

}
