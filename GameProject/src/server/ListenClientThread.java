package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ListenClientThread extends Thread {
	private int clientId;
	private BufferedReader in;

	public ListenClientThread(int clientId, Socket s)
			throws IOException {
		this.clientId = clientId;
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
	}

	public void run() {
		try {
			while (true) {
				String s = in.readLine();
				if (s.length() == 0) continue;
				if (s.length() == 1) { // это нажатие с клавиатуры пришло
					char c = s.charAt(0);
					Map.getInstance().putButton(clientId, c);
					Thread.sleep(Global.DELAY);
				} else { // мышка. нас интересует id куда было нажато
					String[] v = s.split(" ");
					int clickedId = Integer.parseInt(v[1]);
					Map.getInstance().putMouse(clientId, clickedId);
					Thread.sleep(Global.DELAY);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
