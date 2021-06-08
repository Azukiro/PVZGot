package gameData.Zombies;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import gameData.Coordinates;
import gameData.LivingBeing;

public class DolphinRiderZombie extends AbstractWaterZombie{
	private int canJump=0;	
	
	public DolphinRiderZombie(Coordinates c) {
		super(340, 3, 2000, c, 48);
	}
	
	@Override
	public int attack(LivingBeing l) {	//indique le saut
		if(canJump==0) {
			if(l.getHeight()<1) {
				canJump=1;
				return 0;
			}
			
		}
		return super.attack(l);
	}
	
	@Override
	public void draw(Graphics2D graphics, int height, ArrayList<Image> imagesList) {	//changement de l'image après saut
		if(canJump==1 && !back) {
			canJump=2;
			getCoordinates().addSpeedToX(-((int)(height*1.5)));
			image++;
		}
		super.draw(graphics, height, imagesList);
	}
	
	@Override
	public String tag() {
		// TODO Auto-generated method stub
		return "DolphinRiderZombie";
	}
}
