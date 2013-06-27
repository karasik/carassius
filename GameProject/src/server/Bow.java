package server;

public class Bow extends Weapon {

	public Bow(int damage) {
		// меч бьет соседнюю клетку и наискосок
		super(damage, 8);
	}

}
