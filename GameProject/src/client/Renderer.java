package client;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Renderer extends JFrame {
	public Renderer() {
		DrawPanel panel = new DrawPanel();
		add(panel);
		
		setTitle("Simple example");
	    setSize(640, 480);
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
		
//		tile.draw(g, new Rectangle(500, -50, 200, 200), new Rectangle(0, 0, 640, 480));
		
		Rectangle frame = new Rectangle(0, 0, 640, 480);
		for(Entity entity : Global.map.entities.values()) {
			if(entity.tile == null)
				entity.setTile(Global.tile0, Global.tile1);
			entity.tile.draw(g, entity.getPositionRect(), frame);
		}
	}
	
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawMap(g);
    }
}
