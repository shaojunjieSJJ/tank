package com.mashibing.tank.abstractfactory;

import com.mashibing.tank.Dir;
import com.mashibing.tank.Group;
import com.mashibing.tank.TankFrame;

/**
 * 
 * @author shao
 * @date 2020年5月7日 下午3:47:37
 *	生产坦克，子弹，爆炸
 */
public abstract class GameFactory {
	
	public abstract BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf);
	public abstract BaseExplode createExplode(int x, int y, TankFrame tf);
	public abstract BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf);
	
}
