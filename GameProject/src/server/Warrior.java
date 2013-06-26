package server;

public class Warrior extends Player {
	public Warrior(int belongsTo, int x, int y) {
		super(belongsTo, x, y);
		
		putParameter("type", "2");
		putParameter("hp", "50");
		putParameter("mp", "5");
		putParameter("move-delay", "10");
		putParameter("strength", "10");
		putParameter("agility", "3");
		putParameter("inellect", "3");
		putParameter("mine", "false");
		
		setWeapon(new Sword(10));
	}
}
