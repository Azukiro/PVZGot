package gameData.Plants;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import gameData.Bot;
import gameData.LivingBeing;
import gameData.Zombies.Zombies;

public class DoomShroom extends CherryBomb{
	private boolean isActive;//Boolean de s'il est actif ou pas en fonction du terraib
	
	public DoomShroom(boolean isActive) {
		super(4,29);
		this.isActive=isActive;
	}
	
	@Override
	public boolean zombieInRange(Zombies z, int sizeCase, int x) {
		if(isActive) {
			return super.zombieInRange(z, sizeCase, x);
		}
		return false;
		
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
	public int attack(LivingBeing l) {
		if(isActive) {
			super.attack(l);
			return 180*1000;//Il bloque la case pendant 180 sec
		}
		return 0;
	}
	
	@Override
	public int attackManyLine() {
		// TODO Auto-generated method stub
		return 3;
	}
	
	@Override
	public String tag() {
		// TODO Auto-generated method stub
		return "DoomShroom";
	}
	
	public void wakeUp() {
		isActive=true;//Pour etre révéilé par la coffeeBean
	}

	
}
