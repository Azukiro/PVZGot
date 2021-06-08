package gameData.ground;

import gameData.Plants.FlowerPot;
import gameData.Plants.Plants;

public class RoofCell extends PlantsCell<FlowerPot> {
	
	public RoofCell() {//Just pour dire que ce sont des cellule ayant comme base un flower pot
		
	}
		
	public RoofCell(FlowerPot p) {
		setPlants(p);
	}
	
	@Override
	public boolean setPlants(Plants plants) {
				
		return super.setPlants(plants);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+base+"JE SUIS UNE ROOF CELL";
	}
}
