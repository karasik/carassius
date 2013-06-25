package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	private Map map;
	private static final int NUM_PLAYERS = 1;
	private ArrayList<Socket> playerSockets;
	
	public Server(int n, int m) {
		map = new Map(n, m);
		playerSockets = new ArrayList<Socket>();
	}
	
	public void start() throws IOException, InterruptedException {
		// поднимаю сервер и жду пока все клиенты подключатся
		ServerSocket s = new ServerSocket(8080);
		for (int i=0; i<NUM_PLAYERS; i++) {
			playerSockets.add(s.accept());
		}
		// ура! у меня есть все клиенты и теперь я с ними могу работать
		new TicLoopThread(map, playerSockets).start();
		new ListenClientsThread().start();
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		new Server(100, 100).start();
	}
}
