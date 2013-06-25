package client;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

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
		
		if(Global.map.player != null)
			Global.map.player.playerEntity.updatePlayerPosition();	
		
		//System.out.println(Global.cameraPosition);
		
		Point plus = new Point(
				- Global.map.player.playerEntity.getPosition().x + Global.visibleFrame.width/2,
				- Global.map.player.playerEntity.getPosition().y + Global.visibleFrame.height/2);
		
		for(Entity entity : Global.map.entities.values()) {
			if(entity.tile == null) {
				entity.setTile(Global.tiles);
			}
			
			Rectangle rect = new Rectangle(
					entity.getPositionRect().x + plus.x,
					entity.getPositionRect().y + plus.y,
					entity.getPositionRect().width,
					entity.getPositionRect().height
					);
			
			entity.tile.draw(g, rect, Global.visibleFrame);
		}
	}
	
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawMap(g);
    }
}
