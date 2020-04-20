package com.mashibing.tank;

import java.awt.Graphics;

public class Tank {
	private int x, y;
	private Dir dir = Dir.DOWN;
	private static final int SPEED = 10;
	
	private boolean moving = false;
	
	public Tank() {
		
	}
	public Tank(int x, int y, Dir dir) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Dir getDir() {
		return dir;
	}

	public static int getSpeed() {
		return SPEED;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}
	
	public void paint(Graphics g) {
		g.fillRect(x, y, 50, 50);
		move();
	}
	
	public boolean isMoving() {
		return moving;
	}
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	private void move() {
		if(!moving) return;
		switch (dir) {
		case LEFT:
			x-=SPEED;
			break;
		case UP:
			y-=SPEED;
			break;
		case RIGHT:
			x+=SPEED;
			break;
		case DOWN:
			y+=SPEED;
			break;
		}
	}
	
	
	
}
