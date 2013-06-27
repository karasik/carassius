package server;

public class Creature extends Entity {
	private Weapon weapon;
	private boolean isAlive;
	private boolean deathSent;
	
	public Creature() {
		putParameter("walkable", "false");
		isAlive = true;
		deathSent = false;
	}
	
	public void setWeapon(Weapon w) {
		weapon = w;
	}
	
	public Weapon getWeapon() {
		return weapon;
	}

	public boolean tryToChangeCoordBy(int dx, int dy) {
		int x = this.getX(), y = this.getY();
		TileContainer[][] tileMatrix = Map.getInstance().getTileMatrix();
		if (tileMatrix[x + dx][y + dy].getTile().isWalkable() && tileMatrix[x + dx][y + dy].getCreatures().size() == 0) {
			return this.tryToMoveTo(x + dx, y + dy);
		}
		return false;
	}
	
	public String getParameterStrings() {
		if (!getDeathSent()) {
			if (!isAlive()) {
				setDeathSent(true);
				this.putParameter("alive", "false");
			}
			return super.getParameterStrings();
		}
		return "";
	}


	private void setDeathSent(boolean b) {
		deathSent = b;
	}

	private boolean getDeathSent() {
		return deathSent;
	}

	private boolean isAlive() {
		return isAlive;
	}

	public void changeCoord(int x, int y) {
		TileContainer[][] tileMatrix = Map.getInstance().getTileMatrix();
		tileMatrix[getX()][getY()].removeCreature(this);
		putParameter("x", x + "");
		putParameter("y", y + "");
		tileMatrix[getX()][getY()].addCreature(this);
	}

	public void setCoord(int x, int y) {
		TileContainer[][] tileMatrix = Map.getInstance().getTileMatrix();
		putParameter("x", x + "");
		putParameter("y", y + "");
		tileMatrix[getX()][getY()].addCreature(this);
	}

	public void hit(int damage) {
		int hp = Integer.parseInt(this.getParameter("hp"));
		hp = Math.max(0, hp - damage);
		this.putParameter("hp", hp + "");
		if (hp == 0) {
			this.putParameter("alive", "false");
		}
	}
	
}
