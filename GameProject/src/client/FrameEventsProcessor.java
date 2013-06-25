package client;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class FrameEventsProcessor implements ComponentListener {

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent e) {
		Global.visibleFrame.width = e.getComponent().getWidth();
		Global.visibleFrame.height = e.getComponent().getHeight();
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

}
