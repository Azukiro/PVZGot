package gameData.Plants;

import gameData.ground.Cells;

public class Pumpkin extends AbtractDefensivePlant {
	
	public Pumpkin() {
		super(4000, 79);
	}
	
	@Override
	public String tag() {
		// TODO Auto-generated method stub
		return "Pumpkin";
	}
	
	@Override
	public boolean activePlant(Cells cells) {
		if(cells.haveBase() && !cells.havePumpkin()) {//Il se met sur la cellule en tant que pumpkin
			cells.putPumpkin(this);
		}
		return false;
	}

}
