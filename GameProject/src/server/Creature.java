package server;

public class Creature extends Entity {
	private Weapon weapon;
	private int lastMoveTime;
	
	public Creature() {
		lastMoveTime = Integer.MIN_VALUE / 2;
	}
	
	public void setWeapon(Weapon w) {
		weapon = w;
	}
	
	public Weapon getWeapon() {
		return weapon;
	}

	public boolean moveTo(int x, int y) {
		if (getParameter("walk-delay") == null) {
			setCoord(x, y);
			lastMoveTime = Global.time;
			return true;
		}
		if (Global.time - lastMoveTime <= Integer.parseInt(getParameter("walk-delay"))) {
			return false;
		}
		setCoord(x, y);
		lastMoveTime = Global.time;
		return true;
	}
}
