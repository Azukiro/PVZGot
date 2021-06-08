package gameData.Zombies;

import gameData.Coordinates;
import gameData.LivingBeing;
import gameData.Plants.Plants;
import gameData.ground.Cells;

public class Gargantuar extends Zombies {

	public Gargantuar(Coordinates coordinates) {
		super(3000, 1, 1000, coordinates, 87);
	}
	
	@Override
	public int attack(LivingBeing l) {//Il tue directement tout ce qu'il touche
		l.suicide();
		return 0;
	}
	@Override
	public int attackCell(Cells cells) {
		super.attackCell(cells);//Le gargantua tue la plante de base
		cells.cleanCell();
		Plants plants = cells.getPlants();//Puis il tue la base de la plante s'il y en a une
		if(plants!= null) {
			attack(plants);
		}
		cells.cleanCell();
		plants = cells.getPlants();//Puis il tue la pumpkin de la plante s'il y en a une
		if(plants!= null) {
			attack(plants);
		}
		return 0;
	}

}
