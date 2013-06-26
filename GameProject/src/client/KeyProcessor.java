package client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyProcessor implements KeyListener {
	long lastTime = 0;
	long stepTime = 10000000L;
	
	ArrayList<Character> keysQueue = 
			new ArrayList<Character>();

	@Override
	public void keyPressed(KeyEvent e) {
		Character ch = e.getKeyChar();
		keysQueue.add(ch);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	void informServer() {
		long currentTime = System.nanoTime();
		if(currentTime - lastTime > stepTime) {
			if(!keysQueue.isEmpty()) {
				Character ch = keysQueue.get(keysQueue.size() - 1);
				synchronized(Global.socketWriter) {
					Global.socketWriter.println(ch);
				}
				System.out.println("key send: " + ch);
			}
			keysQueue.clear();
			lastTime = currentTime;
		}
	}
	
}
