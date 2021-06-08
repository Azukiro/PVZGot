package gameData.Plants;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import gameData.Bot;
import gameData.LivingBeing;
import gameData.Zombies.Zombies;

public class IceShroom extends CherryBomb {
	private boolean isActive;
	public IceShroom(boolean isActive) {
		super(10,32);
		this.isActive=isActive;
	}
	
	@Override
	public int attack(LivingBeing l) {
		if(!isActive) {return 0;}
		l.slow(3250, 1,20000,2);//Slow les ennemi totalement pendant 3,25 seconde et de moitié pendant 20 sec
		return 0;
	}
	@Override
	public int attackManyLine() {
		if(!isActive) {return 0;}
		return 6;
	}
	@Override
	public boolean zombieInRange(Zombies z, int sizeCase, int x) {
		if(!isActive) {return false;}
		return super.zombieInRange(z, sizeCase, x);
	}
	
	@Override
	public void draw(Graphics2D graphics, int x, int y, int height, ArrayList<Image> imagesList) {
		if(!isActive) {
			super.drawWithouGrow(graphics, x, y, height, imagesList);
		}else {
			super.draw(graphics, x, y, height, imagesList);
		}
	}
	@Override
	public String tag() {
		// TODO Auto-generated method stub
		return "IceShroom";
	}
	
	public void wakeUp() {
		resetTimer();
		isActive=true;
	}
	
}
