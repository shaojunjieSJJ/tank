package com.mashibing.tank;

import java.awt.Graphics;

import com.mashibing.tank.abstractfactory.BaseExplode;

public class Explode extends BaseExplode{

	public static int WIDTH = ResourceMgr.explodes[0].getWidth();
	public static int HEIGHT = ResourceMgr.explodes[0].getHeight();
	
	private int x,y;
	private TankFrame tf = null;
//	private boolean living = true;
	
	private int step = 0;
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Explode(int x, int y, TankFrame tf) {
		this.x = x;
		this.y = y;
		this.tf = tf;
		
		new Thread(() -> new Audio("audio/explode.wav").play()).start();
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(ResourceMgr.explodes[step++], x, y, null);
		
		if (step >= ResourceMgr.explodes.length) {
			tf.explodes.remove(this);
		}
		
	}
	


	
	
}
