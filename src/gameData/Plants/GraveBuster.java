package gameData.Plants;

import gameData.Bot;
import gameData.LivingBeing;
import gameData.Zombies.Zombies;
import gameData.ground.Cells;
import gameData.ground.TombCell;

public class GraveBuster extends Plants {
	public GraveBuster() {
		super(0, 300,5000, 0,31);
	}
	
	
	@Override
	public boolean activePlant(Cells cells) {
		if(cells.haveTomb()) {//Ne peut s'activer que sur la tombe
			((TombCell)cells).removeTomb();
		}
		return false;
	}
	
	@Override
	public boolean zombieInRange(Zombies z, int sizeCase, int x) {
		return super.canAttack();
	}
	
	@Override
	public int attack(LivingBeing l) {
		return 1;
	}
	@Override
	public String tag() {
		// TODO Auto-generated method stub
		return "GraveBuster";
	}
}
