package server;

public class Archer extends Player {
	public Archer(int belongsTo, int x, int y) {
		super(belongsTo, x, y);

		putParameter("type", "4");
		putParameter("hp", "30");
		putParameter("mp", "5");
		putParameter("move-delay", "8");
		putParameter("atack-delay", "8");
		putParameter("strength", "3");
		putParameter("agility", "10");
		putParameter("inellect", "3");
		putParameter("mine", "false");

		setWeapon(new Bow(5));
	}
}