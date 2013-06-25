package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	private Map map;
	private ArrayList<Socket> playerSockets;
	private char[] button;
	private boolean[] wasButton;
	
	public Server(int n, int m) {
		map = new Map(n, m);
		playerSockets = new ArrayList<Socket>();
		button = new char[Global.NUM_PLAYERS];
		wasButton = new boolean[Global.NUM_PLAYERS];
	}
	
	public void start() throws IOException, InterruptedException {
		// поднимаю сервер и жду пока все клиенты подключатся
		ServerSocket s = new ServerSocket(8080);
		for (int i=0; i<Global.NUM_PLAYERS; i++) {
			playerSockets.add(s.accept());
			// здесь нужно сделать игрока соответствующего класса
			map.addWarrior(i);
			new ListenClientThread(i, playerSockets.get(i), button, wasButton).start();
		}
		// ура! у меня есть все клиенты и теперь я с ними могу работать
		new TicLoopThread(map, playerSockets, button, wasButton).start();
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		new Server(20, 20).start();
	}
}
