package gameData.Plants;

import gameData.ground.Cells;
import gameData.ground.PlantsCell;

public class Lillypad extends Plants{
	
	public Lillypad() {
		super(0, 300,0, 0,35);
	}
	
	@Override
	public boolean activePlant(Cells cells) {
		if(!cells.haveBase()) {
			try {

				((PlantsCell<Lillypad>)cells).putBase(this);
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return false;
	}
	@Override
	public String tag() {
		return "LilyPad";
	}
}
