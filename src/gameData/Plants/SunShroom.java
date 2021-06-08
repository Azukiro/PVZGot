package gameData.Plants;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import gameData.Bot;
import gameData.Timer;
import gameData.Zombies.Zombies;

public class SunShroom extends SunFlower  {
	private final Timer timeToGrow= new Timer(120000);
	private boolean  isActive;
	public SunShroom(boolean isActive) {
		super(28,24000,15);
		this.isActive=isActive;
	}
	
	@Override
	public boolean zombieInRange(Zombies z, int sizeCase, int x) {
		if(!isActive) {return false;}
		return super.zombieInRange(z, sizeCase, x);
	}
	
	@Override
	public int newSun() {//Produit au début des soleil plus petit puis quand il grandit ses soleil font de meme
		if(!isActive) {return 0;}
		if(timeToGrow.enoughDelayWithoutReset()) {
			costSun=25;
		}
		return super.newSun();
	}
	
	@Override
	public void draw(Graphics2D graphics, int x, int y, int height, ArrayList<Image> imagesList) {
		int offset=height;
		height=(int) (height*((double)(costSun)/25.0));
		offset-=height;
		offset/=2;
		super.draw(graphics, x+offset, y+offset, height, imagesList);
	}
	@Override
	public String tag() {
		// TODO Auto-generated method stub
		return "SunShroom";
	}
	
	public void wakeUp() {
		isActive=true;
	}
}
