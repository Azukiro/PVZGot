package gameData.Plants;

import gameData.ground.Cells;
import gameData.ground.PlantsCell;

public class FlowerPot extends Plants{

	public FlowerPot() {
		super(0, 300, 0, 0, 58);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean activePlant(Cells cells) {
		if(!cells.haveBase()) {//Lorqu'il est activé il se met sur la case
			try {
				((PlantsCell<FlowerPot>)cells).putBase(this);//Try catch pour éviter erreur s'il est posé sur un autre terrain que le toit
			}catch (Exception e) {
				
			}
			
		}
		return false;
	}
	
	@Override
	public String tag() {
		// TODO Auto-generated method stub
		return "FlowerPot";
	}

}
