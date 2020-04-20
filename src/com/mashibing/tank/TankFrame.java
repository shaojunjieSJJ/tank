package com.mashibing.tank;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame{

	int x= 200, y = 200;
	
	public TankFrame() {
		
		setSize(800, 600);
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
	
	@Override
	public void paint(Graphics g) {
		g.fillRect(x, y, 50, 50);
//		x += 10; 
//		y += 10; 
	}
	
	/*
	 * 键盘监听处理器
	 */
	class MyKeyListener extends KeyAdapter{

		boolean bL = true;
		boolean bR = true;
		boolean bU = true;
		boolean bD = true;
		
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

			default:
				break;
			}
		}
		
		
		
		
	}
	
	
}
