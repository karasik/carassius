package server;

public class Weapon {
	public double radius;
	public int damage;
	
	public Weapon(int damage, double radius) {
		this.damage = damage;
		this.radius = radius;
	}
	
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
}
