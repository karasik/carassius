package server;

import java.util.Comparator;
import java.util.TreeMap;


public abstract class Entity {
	private int globalId;
	private static int nextGlobalId = 0;
	private TreeMap<String, String> parameters;
	private int lastMoveTime;
	
	public Entity() {
		globalId = Entity.nextGlobalId++;
		parameters = new TreeMap<String, String>(new Comparator<String>() {
			public int compare(String a, String b) {
				if (a.equals("x")) return -1;
				if (b.equals("x")) return 1;
				if (a.equals("y")) return -1;
				if (b.equals("y")) return 1;
				
				return a.compareTo(b);
			}
		});
		Map.getInstance().putEntityInMap(this);
		lastMoveTime = Integer.MIN_VALUE / 2;
	}
	
	public void putParameter(String key, String value) {
		parameters.put(key, value);
//		Map.getInstance().getChangeBuffer().add(this);
	}
	
	public String getParameter(String key) {
		if (!parameters.containsKey(key)) return null;
		return parameters.get(key);
	}
	
	public String getParameterStrings() {
		String ret = "";
		ret += globalId;
		ret += System.lineSeparator();
		ret += parameters.size();
		ret += System.lineSeparator();
		for (String key : parameters.keySet()) {
			ret += key;
			ret += System.lineSeparator();
			ret += parameters.get(key);
			ret += System.lineSeparator();
		}
		return ret;
	}
	
	public int getX() {
		return Integer.parseInt(getParameter("x"));
	}
	
	public int getY() {
		return Integer.parseInt(getParameter("y"));
	}
	
	public boolean equals(Object o) {
		return globalId == ((Entity)o).globalId;
	}
	
	public int getGlobalId() {
		return globalId;
	}
	
	protected boolean tryToMoveTo(int x, int y) {
		if (getParameter("move-delay") == null) {
			changeCoord(x, y);
			lastMoveTime = Global.time;
			return true;
		}
		if (Global.time - lastMoveTime <= Integer.parseInt(getParameter("move-delay"))) {
			return false;
		}
		changeCoord(x, y);
		lastMoveTime = Global.time;
		return true;
	}
	
	protected abstract void changeCoord(int x, int y);
	protected abstract void setCoord(int x, int y);
}
