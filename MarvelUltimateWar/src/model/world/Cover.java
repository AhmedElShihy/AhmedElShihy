package model.world;

import java.awt.Point;

public class Cover {
	private int currentHP; // current hit points of cover .. always >= 0
	private Point location; // holds the Cover’s location on the board
	
	public Cover (int x , int y) {
		this.location.x = x;
		this.location.y = y;
		this.currentHP = (int) Math.random() + (1000 - 100 + 1) + 100;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
	}

	public Point getLocation() {
		return location;
	}
	
}
