package client;

import java.util.ArrayList;
import java.util.TreeMap;

public class Map {
	TreeMap<Integer, Entity> entities;
	Player player = null;
	
	Map() {
		entities = new TreeMap<Integer, Entity>(); 
	}
	
	void addEntity(Entity entity) {
		entities.put(entity.globalId, entity);
	}
	
	Entity getEntity(int index) {
		if(!entities.containsKey(index)) {
			entities.put(index, new Entity(index));
		}
		return entities.get(index);
	}
}
