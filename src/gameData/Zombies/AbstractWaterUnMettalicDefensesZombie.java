package gameData.Zombies;

import gameData.Coordinates;

public class AbstractWaterUnMettalicDefensesZombie extends UnMetallicDefensesZombies implements WaterZombies{

	//Zombies d'eau à défenses non métalliques
	
	public AbstractWaterUnMettalicDefensesZombie(int health, int speed, int delay, Coordinates coordinates, int image,
			int healthDefense, int imageDefenceLess) {
		super(health, speed, delay, coordinates, image, healthDefense, imageDefenceLess);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean spawnInWater() {
		return true;
	}

}
