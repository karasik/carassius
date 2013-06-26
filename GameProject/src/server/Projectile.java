package server;

public class Projectile extends Entity {
	private int damage;
	private double radius;
	
	private int x0, y0;
	private int x1, y1;

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public Projectile(int playerX, int playerY, int targetX, int targetY, int damage, double radius, Player player) {
		this.damage = damage;
		this.radius = radius;
		
		x0 = playerX;
		y0 = playerY;
		
		x1 = targetX;
		y1 = targetY;
		
		this.setCoord(x0, y0);
		this.putParameter("walkable", "true");
		this.putParameter("alive", "true");
		this.putParameter("move-delay", "1");
		this.putParameter("type", "0");
	}

	public void moveToTarget() {
		if (!isAlive()) return;
		TileContainer[][] tileMatrix = Map.getInstance().getTileMatrix(); 
		
		if (!tileMatrix[getX()][getY()].getTile().isWalkable() ||
				tileMatrix[getX()][getY()].getCreatures().size() > 0 ||
				Math.hypot(getX() - x0, getY() - y0) > radius) {
			dissipate();
		}
		
		int dx = Math.abs(x1 - getX());
		int dy = Math.abs(y1 - getY());
		
		if (dx >= dy) {
			tryToMoveTo(getX() + (int)Math.signum(x1 - getX()), getY());
		} else {
			tryToMoveTo(getX(), getY() + (int)Math.signum(y1 - getY()));
		}
		
	}
	
	public String getParameterStrings() {
		if (isAlive()) {
			return super.getParameterStrings();
		}
		return "";
	}
	
	private void dissipate() {
		TileContainer[][] tileMatrix = Map.getInstance().getTileMatrix(); 
		for (Creature c : tileMatrix[getX()][getY()].getCreatures()) {
			c.hit(damage);
		}
		putParameter("alive", "false");
	}
	
	public boolean isAlive() {
		return getParameter("alive").equals("true");
	}

	protected void changeCoord(int x, int y) {
		TileContainer[][] tileMatrix = Map.getInstance().getTileMatrix();
		tileMatrix[getX()][getY()].removeProjectile(this);
		putParameter("x", x + "");
		putParameter("y", y + "");
		tileMatrix[getX()][getY()].addProjectile(this);
	}

	protected void setCoord(int x, int y) {
		TileContainer[][] tileMatrix = Map.getInstance().getTileMatrix();
		putParameter("x", x + "");
		putParameter("y", y + "");
		tileMatrix[getX()][getY()].addProjectile(this);
	}
}
