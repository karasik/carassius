package client;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseProcessor implements MouseListener {
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		Point clickPos = e.getPoint();
		
		System.out.println(clickPos);
		
		clickPos.x += 
				Global.cameraPosition.x - Global.visibleFrame.width/2;
		clickPos.y += 
				Global.cameraPosition.y - Global.visibleFrame.height/2;
		
		int id = -1;
		int type = -1;
		for(Entity entity : Global.map.entities.values()) {
			Rectangle rect = entity.getPositionRect();
			if(
				clickPos.x >= rect.x && clickPos.x < rect.x + rect.width &&
				clickPos.y >= rect.y && clickPos.y < rect.y + rect.height
				)
			{
				id = entity.globalId;
				type = entity.getType();
			}
		}
		System.out.println(id);
		System.out.println(type);
		if(id != -1) {
			synchronized (Global.socketWriter) {
				Global.socketWriter.println("m " + id);
				System.out.println("mouse send: " + id);
			}
		}
	}

}
