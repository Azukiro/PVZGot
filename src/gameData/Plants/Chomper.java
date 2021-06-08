package gameData.Plants;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import gameData.LivingBeing;
import gameData.Timer;

public class Chomper extends Plants{
	private final int dmgAttack=40;
	private boolean eating=false;//Propriété pour mettre k'image de quand il mange
	public Chomper() {
		super(1800, 4000,0, 1,8);
	}
	
	@Override
	public int attack(LivingBeing l) {
		if(l.getHealth()<=1800) {
			int pv=l.getHealth();//Il prend les pv de son ennemeie et ne peut plus attaquer tant qu'il n'as pas manger tout les pv
			eating=true;
			timeToAttackTimer=new Timer((pv/dmgAttack)*1500);
			return super.attack(l);
		}else {
			return super.attack(l);
		}
		
		
	}
	
	@Override
	public String tag() {
		// TODO Auto-generated method stub
		return "Chomper";
	}
	
	@Override
	public void draw(Graphics2D graphics, int x, int y, int height, ArrayList<Image> imagesList) {
		if(timeToAttackTimer.enoughDelayWithoutReset()) {
			eating=false;
		}
		if(!eating) {
			super.draw(graphics, x, y, height, imagesList);
		}else {
			graphics.drawImage(imagesList.get(9),x,y,height,height,null);
			super.drawLife(graphics, x, y, height);
		}
		
	}
}
