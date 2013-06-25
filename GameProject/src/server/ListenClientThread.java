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
	private int[] mouseId;
	private boolean[] wasMouse;

	public ListenClientThread(int clientId, Socket s, char[] button, boolean[] wasButton, int[] mouseId, boolean[] wasMouse)
			throws IOException {
		this.clientId = clientId;
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		this.button = button;
		this.wasButton = wasButton;
		this.mouseId = mouseId;
		this.wasMouse = wasMouse;
	}

	public void run() {
		try {
			while (true) {
				String s = in.readLine();
				if (s.length() == 0) continue;
				if (s.length() == 1) { // это нажатие с клавиатуры пришло
					char c = s.charAt(0);
					button[clientId] = c;
					synchronized (wasButton) {					
						wasButton[clientId] = true;
					}
					Thread.sleep(10);
				} else { // мышка. нас интересует id куда было нажато
					String[] v = s.split(" ");
					int clickedId = Integer.parseInt(v[1]);
					mouseId[0] = clickedId;
					synchronized (wasMouse) {					
						wasMouse[0] = true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
