package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	private ArrayList<Socket> playerSockets;

	public Server(int n, int m) {
		Map.initialize(n, m);
		playerSockets = new ArrayList<Socket>();

	}

	public void start() throws IOException, InterruptedException {
		// поднимаю сервер и жду пока все клиенты подключатся
		ServerSocket s = new ServerSocket(8080);
		for (int i = 0; i < Global.NUM_PLAYERS; i++) {
			playerSockets.add(s.accept());
			// здесь нужно сделать игрока соответствующего класса
			Map.getInstance().addWarrior(i);
			new ListenClientThread(i, playerSockets.get(i)).start();
		}
		// ура! у меня есть все клиенты и теперь я с ними могу работать
		new TicLoopThread(playerSockets).start();
	}

	public static void main(String[] args) throws IOException,
			InterruptedException {
		new Server(20, 20).start();
	}
}
