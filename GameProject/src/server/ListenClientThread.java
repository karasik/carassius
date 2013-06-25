package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ListenClientThread extends Thread {
	private int clientId;
	private BufferedReader in;
	private char[] button;
	private boolean[] wasButton;

	public ListenClientThread(int clientId, Socket s, char[] button, boolean[] wasButton)
			throws IOException {
		this.clientId = clientId;
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		this.button = button;
		this.wasButton = wasButton;
	}

	public void run() {
		try {
			while (true) {
				String s = in.readLine();
				if (s.length() == 0) continue;
				char c = s.charAt(0);
				button[clientId] = c;
				synchronized (wasButton) {					
					wasButton[clientId] = true;
				}
				
				Thread.sleep(10);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
