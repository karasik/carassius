package client;

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
	
	public void draw(Graphics g, Rectangle rect, Rectangle frame) {
		Graphics2D g2 = (Graphics2D)g;
		
		Rectangle fillRect = (Rectangle)rect.clone();
		
		if(rect.x < frame.x) {
			fillRect.width -= (frame.x - rect.x);
		}
		if(rect.y < frame.y) {
			fillRect.height -= (frame.y - rect.y);
		}
		
		TexturePaint paint = new TexturePaint(image, rect);
		
		g2.setPaint(paint);
		g2.fillRect(clamp(rect.x, frame.x, frame.x + frame.width),
					clamp(rect.y, frame.y, frame.y + frame.height),
					fillRect.width,
					fillRect.height);
	}
	
	private int clamp(int val, int l, int r) {
		return Math.max(l, Math.min(val, r));
	}
}
