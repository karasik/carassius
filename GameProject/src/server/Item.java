package server;

public class Item extends Entity {


	public void changeCoord(int x, int y) {
		TileContainer[][] tileMatrix = Map.getInstance().getTileMatrix();
		tileMatrix[getX()][getY()].removeItem(this);
		putParameter("x", x + "");
		putParameter("y", y + "");
		tileMatrix[getX()][getY()].addItem(this);
	}

	public void setCoord(int x, int y) {
		TileContainer[][] tileMatrix = Map.getInstance().getTileMatrix();
		putParameter("x", x + "");
		putParameter("y", y + "");
		tileMatrix[getX()][getY()].addItem(this);
	}

}
