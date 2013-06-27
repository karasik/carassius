package server;

public class Player extends Creature {
	private int belongsTo;

	public Player(int belongsTo, int x, int y) {
		this.belongsTo = belongsTo;
		setCoord(x, y);
	}

	public int getClientNumber() {
		return belongsTo;
	}
}
