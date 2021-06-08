package gameData.Plants;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import gameData.Bot;
import gameData.Zombies.Zombies;

public class ScaredyShroom extends PuffShroom {
	private final int rangeHide=2;
	private boolean scared=false;
	
	public ScaredyShroom(boolean isActive) {
		super(20, isActive);
	}
	
	@Override
	public boolean zombieInRange(Zombies z, int sizeCase, int x) {
		//Lorqu'il à un ennemie dans la range de sa peut il se cache et on change donc d'image et on l'empeche d'attquer
		if(z!=null && (z.getCoordinates().getX()<=((x*sizeCase)+(sizeCase*rangeHide)))&& (z.getCoordinates().getX()>=((x*sizeCase)-(sizeCase*rangeHide))) || !isActive) {
			
			if (!scared) {
				image++;
			}
			scared=true;
			return false;
		}
		if(z!=null) {
			if(scared) {

				image--;
			}
			scared=false;
		}
		return super.zombieInRange(z, sizeCase, x);
	}
	
	@Override
	public void draw(Graphics2D graphics, int x, int y, int height, ArrayList<Image> imagesList) {
		
		super.draw(graphics, x, y, height, imagesList);
	}
	
	@Override
	public String tag() {
		// TODO Auto-generated method stub
		return "ScaredyShroom";
	}
	
}
