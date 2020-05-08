package com.mashibing.tank;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		TankFrame f = new TankFrame();
		
		int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));
		
		//初始化地方坦克
		for (int i = 0; i < initTankCount; i++) {
			f.tanks.add(f.gf.createTank(50 + i*80, 200, Dir.DOWN, Group.BAD, f));
		}
		
		new Thread(() -> new Audio("audio/war1.wav").play()).start();
		
		while(true){
			Thread.sleep(50);
			f.repaint();
		}
	}

}
