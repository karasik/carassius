package client;

import java.util.LinkedList;

public class Map {
	LinkedList<Entity> entities;
	
	Map() {
		entities = new LinkedList<Entity>(); 
	}
	
	void addEntity(Entity entity) {
		entities.add(entity);
	}
}
