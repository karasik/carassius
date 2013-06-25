package server;

import java.util.TreeMap;


public class Entity {
	private int globalId;
	private static int nextGlobalId = Integer.MIN_VALUE;
	private TreeMap<String, String> parameters;
	
	public Entity() {
		globalId = Entity.nextGlobalId++;
		parameters = new TreeMap<String, String>();
	}
	
	protected void putParameter(String key, String value) {
		parameters.put(key, value);
	}
	
	protected String getParameter(String key) {
		if (!parameters.containsKey(key)) return null;
		return parameters.get(key);
	}
	
	public String getParameterStrings() {
		String ret = "";
		ret += globalId;
		ret += System.lineSeparator();
		for (String key : parameters.keySet()) {
			ret += key;
			ret += System.lineSeparator();
			ret += parameters.get(key);
			ret += System.lineSeparator();
		}
		return ret;
	}
	
}
