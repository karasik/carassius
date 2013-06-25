package client;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Renderer extends JFrame {
	
	
	public Renderer() {
		DrawPanel panel = new DrawPanel();
		add(panel);
		
		setTitle("Game");
	    setSize(Global.visibleFrame.width, Global.visibleFrame.height);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}

class DrawPanel extends JPanel {
	Tile tile;
	
	public DrawPanel() {
		loadImages();
	}
	
	private void loadImages() {
		tile = new Tile("picture.jpg");
	}
	
	private void drawMap(Graphics g) {
		
		for(Entity entity : Global.map.entities.values()) {
			if(entity.tile == null)
				entity.setTile(Global.tiles);
			entity.tile.draw(g, entity.getPositionRect(), Global.visibleFrame);
		}
	}
	
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawMap(g);
    }
}
