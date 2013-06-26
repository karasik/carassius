package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class TicLoopThread extends Thread {
	private ArrayList<Socket> playerSockets;
	private ArrayList<PrintWriter> playerStreams;
	private char[] button;
	private boolean[] wasButton;
	private int[] mouseId;
	private boolean[] wasMouse;

	public TicLoopThread(ArrayList<Socket> playerSockets, char[] button,
			boolean[] wasButton, int[] mouseId, boolean[] wasMouse)
			throws IOException {
		this.playerSockets = playerSockets;
		playerStreams = new ArrayList<PrintWriter>();
		for (Socket s : playerSockets) {
			// true вторым аргументом означает autoflush
			playerStreams.add(new PrintWriter(s.getOutputStream(), false));
		}
		this.button = button;
		this.wasButton = wasButton;
		this.mouseId = mouseId;
		this.wasMouse = wasMouse;
	}

	public void run() {
		try {
			sendTiles();
			while (true) {
				moveProjectiles();
				makePlayersTurns();
				sendInfo();
				
				Thread.sleep(Global.DELAY);
				Global.time++;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void sendTiles() {
		for (int i=0; i<playerSockets.size(); i++) {
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
			Map.getInstance().getPlayer(i)
					.putParameter("mine", "false");
			stream.println("RENDER");
			stream.flush();
		}

		
	}

	private void moveProjectiles() {
		// обрабатываем все летящие хрени
		for (Projectile p : Map.getInstance().getAllProjectiles()) {
			p.moveToTarget();
		}
		if (Global.time % Global.REFRESH_PROJECTILE_TIME == 0) {
			Map.getInstance().refreshProjectiles();
		}
	}

	private void makePlayersTurns() {

		// обрабатываем мышь
		for (int i = 0; i < Global.NUM_PLAYERS; i++) {
			if (!wasMouse[i])
				continue;
			Player p = Map.getInstance().getPlayer(i);
			Weapon w = p.getWeapon();
			if (w == null)
				continue; // если нет оружия, то щито поделать десу
			Entity e = Map.getInstance().getEntityFromId(mouseId[i]);

			int playerX = p.getX(), playerY = p.getY();
			int targetX = e.getX(), targetY = e.getY();
			
			if (playerX == targetX && playerY == targetY) continue; // не хотет стрелять в себя

			Projectile pr = new Projectile(playerX, playerY, targetX, targetY,
					w.getDamage(), w.getRadius(), p);

			synchronized (wasMouse) {
				wasMouse[i] = false;
			}
		}
		// обрабатываем клавиатуру
		for (int i = 0; i < Global.NUM_PLAYERS; i++) {
			if (!wasButton[i])
				continue;
			boolean success = false;
			switch (button[i]) {
			case 'w':
				success = Map.getInstance().getPlayer(i)
						.tryToChangeCoordBy(0, -1);
				break;
			case 'a':
				success = Map.getInstance().getPlayer(i)
						.tryToChangeCoordBy(-1, 0);
				break;
			case 's':
				success = Map.getInstance().getPlayer(i)
						.tryToChangeCoordBy(0, 1);
				break;
			case 'd':
				success = Map.getInstance().getPlayer(i)
						.tryToChangeCoordBy(1, 0);
				break;
			default:
				success = true;
				break;
			}
			if (success) {
				synchronized (wasButton) {
					wasButton[i] = false;
				}
			}
		}
	}
}
