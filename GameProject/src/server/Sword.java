package server;

public class Sword extends Weapon {

	public Sword(int damage) {
		// меч бьет соседнюю клетку и наискосок
		super(damage, 1.5);
	}

}
