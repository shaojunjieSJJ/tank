package com.mashibing.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import com.mashibing.tank.abstractfactory.BaseBullet;
import com.mashibing.tank.abstractfactory.BaseExplode;
import com.mashibing.tank.abstractfactory.BaseTank;
import com.mashibing.tank.abstractfactory.DefaultFactory;
import com.mashibing.tank.abstractfactory.GameFactory;
import com.mashibing.tank.abstractfactory.RectFactory;

public class TankFrame extends Frame{
	
	public Tank myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD, this);
	public List<BaseBullet> bullets = new ArrayList<>();
	public List<BaseTank> tanks = new ArrayList<>();
	public Bullet b = new Bullet(300, 300, Dir.DOWN, Group.GOOD, this);
	public List<BaseExplode> explodes  = new ArrayList<>();
	
	public GameFactory gf = new DefaultFactory();
	
	public static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;
	public TankFrame() {
		
		setSize(GAME_WIDTH, GAME_HEIGHT);
		setVisible(true); //
		setResizable(false); // 窗口是否可拖放
		setTitle("tank war");
		
		this.addKeyListener(new MyKeyListener());
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	Image offScreenImage = null;
	@Override
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics goffScreen = offScreenImage.getGraphics();
		Color c = goffScreen.getColor();
		goffScreen.setColor(Color.BLACK);
		goffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		goffScreen.setColor(c);
		paint(goffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
		
		
	}
	
	@Override
	public void paint(Graphics g) {
		
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("子弹的数量" + bullets.size(), 10, 60);
		g.drawString("敌人的数量" + tanks.size(), 10, 80);
		g.drawString("爆炸的数量" + explodes.size(), 10, 100);
		g.setColor(c);
		myTank.paint(g);
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).paint(g);
		}
		for (int i = 0; i < tanks.size(); i++) {
			tanks.get(i).paint(g);
		}
		for (int i = 0; i < explodes.size(); i++) {
			explodes.get(i).paint(g);
		}
		for(int i=0; i<bullets.size(); i++) {
			for(int j=0; j<tanks.size(); j++) {
				bullets.get(i).collideWith(tanks.get(j));
			}
		}
		
		for(int i=0; i<bullets.size(); i++) {
			for(int j=0; j<tanks.size(); j++) {
				bullets.get(i).collideWith(tanks.get(j));
			}
		}
		
		
		
//		for (Iterator<Bullet> it = bullets.iterator(); it.hasNext();) {
//			Bullet b = it.next();
//			if (!b.live) it.remove();
//		}
		
//		explodes.paint(g);
	}
	
	/*
	 * 键盘监听处理器
	 */
	class MyKeyListener extends KeyAdapter{

		boolean bL = false;
		boolean bR = false;
		boolean bU = false;
		boolean bD = false;
		
		@Override
		public void keyPressed(KeyEvent e) {
//			System.out.println("Key pressed");
//			x += 200;
//			repaint();
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_LEFT:
				bL = true;
				break;
			case KeyEvent.VK_UP:
				bU = true;
				break;
			case KeyEvent.VK_RIGHT:
				bR = true;
				break;
			case KeyEvent.VK_DOWN:
				bD = true;
				break;

			default:
				break;
			}
			
			setMainTankDir();
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_LEFT:
				bL = false;
				break;
			case KeyEvent.VK_UP:
				bU = false;
				break;
			case KeyEvent.VK_RIGHT:
				bR = false;
				break;
			case KeyEvent.VK_DOWN:
				bD = false;
				break;
			case KeyEvent.VK_CONTROL:
				myTank.fire();
				break;

		 	default:
				break;
			}
			
			setMainTankDir();
		}

		private void setMainTankDir() {
			myTank.setMoving(true);
			if(bL) myTank.setDir(Dir.LEFT);
			if(bU) myTank.setDir(Dir.UP);
			if(bR) myTank.setDir(Dir.RIGHT);  
			if(bD) myTank.setDir(Dir.DOWN);
			
			if (!bL && !bU && !bR && !bD) myTank.setMoving(false);
		}	
	}
	
	
}
