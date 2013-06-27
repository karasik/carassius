package client;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile {	
	public Tile(String name) {
		loadImage(name);
	}
	
	private void loadImage(String name) {
		try {
			image = ImageIO.read(this.getClass().getResource("../img/" + name));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private BufferedImage image;
	
	public void draw(Graphics g, Rectangle rect, Rectangle frame, boolean visible, boolean upToDate, boolean isTile, boolean isExplored) {
		if(!isExplored || (visible && !upToDate && !isTile) )
			return;
		
		Graphics2D g2 = (Graphics2D)g;
		
		Rectangle fillRect = (Rectangle)rect.clone();
		
		if(rect.x < frame.x) {
			fillRect.width -= (frame.x - rect.x);
		}
		if(rect.y < frame.y) {
			fillRect.height -= (frame.y - rect.y);
		}
		
		TexturePaint paint = new TexturePaint(image, rect);
		
		Composite originalComposite = g2.getComposite();
		if(!visible) {
			float alpha = 0.5f;
			int rule = AlphaComposite.SRC_OVER;
			
			g2.setComposite(AlphaComposite.getInstance(rule, alpha));
		}
		
		
		g2.setPaint(paint);
	
		
		g2.fillRect(clamp(rect.x, frame.x, frame.x + frame.width),
					clamp(rect.y, frame.y, frame.y + frame.height),
					fillRect.width,
					fillRect.height);
		
		g2.setComposite(originalComposite);
	}
	
	private int clamp(int val, int l, int r) {
		return Math.max(l, Math.min(val, r));
	}
}
