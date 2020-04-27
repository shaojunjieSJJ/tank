package com.mashibing.tank;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		TankFrame f = new TankFrame();
		
		//初始化地方坦克
		for (int i = 0; i < 5; i++) {
			f.tanks.add(new Tank(50 + i*80, 200, Dir.DOWN, f));
		}
		while(true){
			Thread.sleep(50);
			f.repaint();
		}
	}

}
