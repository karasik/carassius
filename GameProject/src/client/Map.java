package client;

import java.util.ArrayList;
import java.util.LinkedList;

public class Map {
	//LinkedList<Entity> entities;
	
	ArrayList<Entity> entities;
	
	Map() {
		entities = new ArrayList<Entity>(); 
	}
	
	void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	Entity getEntity(int index) {
		while(index >= entities.size()) {
			entities.add(new Entity());
		}
		return entities.get(index);
	}
}
