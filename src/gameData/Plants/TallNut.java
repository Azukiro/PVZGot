package gameData.Plants;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

public class TallNut extends AbtractDefensivePlant{
	
	public TallNut() {
		super(8000,38,1);
	}
	
	@Override
	public boolean canAttack() {
		return false;
	}
	@Override
	public String tag() {
		return "TallNut";
	}
	
	@Override
	public void draw(Graphics2D graphics, int x, int y, int height, ArrayList<Image> imagesList) {
		super.draw(graphics, x, y, height, imagesList);
	}
}
