package client;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Renderer extends JFrame {
	JTextField textField = null;
	DrawPanel panel = null;
	
	public Renderer() {
		panel = new DrawPanel();
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
		
		Point plus = new Point(
				- Global.map.player.playerEntity.getPosition().x + Global.visibleFrame.width/2 - Global.tileWidth/2,
				- Global.map.player.playerEntity.getPosition().y + Global.visibleFrame.height/2 - Global.tileHeight/2);
		
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
			
			boolean visible = entity.getTick() == Global.tickCounter-1;
			
			entity.tile.draw(g, rect, Global.visibleFrame, visible);
		}
		
		
		Graphics2D g2 = (Graphics2D)g;
		String message = "HP: " + Global.map.player.getHP();
		Font f = new Font("Dante", 1, 20);
		g.setFont(f);
		g2.setPaint(getForeground());
		g.drawChars(message.toCharArray(), 0, message.length(), 20, 20);
	}
	
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawMap(g);
    }
}
