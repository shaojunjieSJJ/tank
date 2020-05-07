package com.mashibing.tank;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Tank {
	int x, y;
	Dir dir = Dir.DOWN;
	private static final int SPEED = 5;
	
	public static int WIDTH = ResourceMgr.goodTankU.getWidth();
	public static int HEIGHT = ResourceMgr.goodTankU.getHeight();
	// 控制敌方坦克是否动
	private boolean moving = true;
	Group group = Group.BAD;
	private Random random = new Random();
	
	Rectangle rect = new Rectangle();
	FireStrategy fs;
	
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	TankFrame tf = null;
	private boolean living = true;
	
	public Tank() {
		
	}
	public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.tf = tf;
		
		rect.x = this.x;
		rect.y = this.y;
		rect.width = WIDTH;
		rect.height = HEIGHT;
		
		if (group == Group.GOOD) {
			
			String goodFSName = (String)PropertyMgr.get("goodFS");
			
			try {
				fs = (FireStrategy)Class.forName(goodFSName).newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			fs = new DefaultFireStrategy();
		}
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
		if(!living) tf.tanks.remove(this);
		switch (dir) {
		case LEFT:
			g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
			break;
		case UP:
			g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
			break;
		case RIGHT:
			g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
			break;
		case DOWN:
			g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
			break;
		}
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
		
		if (this.group == Group.BAD && random.nextInt(100) > 95) 
			this.fire();
		
		if (this.group == Group.BAD && random.nextInt(100) > 95)
			randomDir();
		
		// 边界检测
		boundsCheck();
		
		//update rect
		rect.x = this.x;
		rect.y = this.y;
	}
	
	private void boundsCheck() {
		if (this.x < 0) x = 0;
		if (this.y < 30) y = 30;
		if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH) x = TankFrame.GAME_WIDTH - Tank.WIDTH;
		if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT;
	}
	private void randomDir() {
		this.dir = Dir.values()[random.nextInt(4)];
	}
	
	public void fire() {
		fs.fire(this);
	}
	public void die() {
		this.living = false;
	}
	
	
	
}
