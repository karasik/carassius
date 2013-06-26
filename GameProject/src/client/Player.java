package client;

import java.awt.Point;

public class Player {
	Entity playerEntity;
	
	Player(Entity entity) {
		playerEntity = entity;
	}
	
	int getHP() {
		return playerEntity.getHP();
	}
	
	Point getPosition() {
		return playerEntity.getPosition();
	}
}
