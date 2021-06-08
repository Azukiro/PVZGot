package gameData.Zombies;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import gameData.Coordinates;
import gameData.LivingBeing;

public class PoleVaulterZombie extends Zombies{
	private int canJump=0;
	
	public PoleVaulterZombie(Coordinates coordinates) {
		super(340, 3, 2000, coordinates, 16);
	}
	
	@Override
	public int attack(LivingBeing l) {		//si plante à proximité, saute
		if(canJump==0) {
			if(l.getHeight()<1) {
				canJump=1;
				return 0;
			}
			
		}
		return super.attack(l);
	}
	
	@Override
	public void draw(Graphics2D graphics, int height, ArrayList<Image> imagesList) {	//si il peut sauter, saute et ne peut plus sauter
		if(canJump==1 && !back) {
			canJump=2;
			getCoordinates().addSpeedToX(-((int)(height*1.5)));
			image++;
		}
		super.draw(graphics, height, imagesList);
	}
	
	@Override
	public String tag() {
		return "PoleVaulterZombie";
	}
	
	
}
