package gameData;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;


import gameData.Plants.Plants;
import gameData.Zombies.Zombies;

public class LawnMower extends Plants{
	
	private boolean active;
	private Coordinates c;
	
	//tondeuse, s'active lorsqu'un zombie l'enclenche et traverse la ligne en écrasant tout zombie sur son passage
	
	public LawnMower(int y) {
		super(10000, 100000, 0, 1, 7);
		active=false;
		c = new Coordinates(-50, y);
	}

	@Override
	public String tag() {
		return "LawnMower";
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive() {
		active=true;
		System.out.println(toString()+" d�marre");
	}
	
	public void move() {		//déplacement en fonction du mode test
		if (Bot.isActive()) {
			c = new Coordinates(c.addSpeedToX(20*Bot.getSpeed()), c.getY());
		}
		else {
			c = new Coordinates(c.addSpeedToX(20), c.getY());
		}
	}
	
	public boolean isFinished(int x) {
		return isActive() && c.getX()>=x;
	}
	
	public boolean zombieInRange(int sizeCase, Zombies z) {	//détection des zombies
		int x=z.getCoordinates().getX();
		int coeff=1;
		if (Bot.isActive() && isActive()) {
			coeff=Bot.getSpeed();
		}
		if(c.getX()+(50*coeff) >= x && c.getX()-(50*coeff)<=x && !z.canGoBack()){
			return true;
		}
		return false;
	}
	
	public void draw(Graphics2D graphics,int height,ArrayList<Image> imagesList) {
		graphics.drawImage(imagesList.get(getImageInt()),c.getX(),c.getY()*height,height,height,null);
	}
	
}
