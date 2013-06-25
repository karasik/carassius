package server;

public class Projectile extends Entity {
	private int damage;
	private double radius;
	
	

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

	public Projectile(int damage, double radius, int targetX, int targetY, int i, double d) {

	}

}
