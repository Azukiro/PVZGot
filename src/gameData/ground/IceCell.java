package gameData.ground;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import gameData.Timer;
import gameData.Plants.Plants;

public class IceCell extends Cells{
	private final int image=53;
	private final Timer duration;//Timer pour indiquer la duré de la glace sur la cellule
	
	public IceCell() {
		super();
		duration = new Timer(60000);
	}
	
	@Override
	public boolean setPlants(Plants plants) {
		return false;//Quand il s'agit d'une cellule de glace aucune plante ne peut être posé
	}
	
	public boolean hasExpired() {
		return duration.enoughDelayWithoutReset();
	}
	
	@Override
	public void drawCells(Graphics2D graphics, int x, int y, int height, ArrayList<Image> listiImages) {
		if (isFog()) {
			graphics.drawImage(listiImages.get(57),x-height/3,y-height/3,height+height/2,height+height/2,null);
		}
		else {
			graphics.drawImage(listiImages.get(image),x,y,height,height,null);
		}
	}
	
	@Override
	public boolean isIceCell() {
		return true;
	}
}
