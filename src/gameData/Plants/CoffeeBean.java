package gameData.Plants;

import gameData.ground.Cells;

public class CoffeeBean extends Plants {

	public CoffeeBean() {
		super(0, 1, 0, 2, 61);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public boolean activePlant(Cells cells) {
		if(cells.havePlants()) {//Quand on l'active elle réveille les plante

			cells.getReallyPlants().wakeUp();
		}
		return false;
	}
	@Override
	public String tag() {
		// TODO Auto-generated method stub
		return "CoffeeBean";
	}
	

}
