package gameData.Plants;

import gameData.LivingBeing;
import gameData.Zombies.Zombies;
import gameData.ground.Cells;

public class TangleKelp extends Plants  {
	
	public TangleKelp() {
		super(10000, 300, 0, 1, 56,0);
	}
	
	@Override
	public boolean activePlant(Cells cells) {
		if(cells.haveBase()) {//ne peut etre proser sur une cellule ayant une base car il ne va que dans l'eau
			return false;
		}
		return true;
	}
	
	@Override
	public int attack(LivingBeing l) {
		suicide();
		return super.attack(l);
	}
	
	@Override
	public boolean inHeight(Zombies z) {
		return (z.getHeight()==getHeight() || z.getHeight()==1 || z.getHeight()==-1);
	}
	
	@Override
	public String tag() {
		// TODO Auto-generated method stub
		return "TangleKelp";
	}
}
