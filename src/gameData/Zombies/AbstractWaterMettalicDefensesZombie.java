package gameData.Zombies;

import gameData.Coordinates;

public class AbstractWaterMettalicDefensesZombie extends MetallicDefensesZombies implements WaterZombies{
	
	//Zombies d'eau à défenses métalliques

	public AbstractWaterMettalicDefensesZombie(int health, int speed, int delay, Coordinates coordinates, int image,
			int healthDefense, int imageDefenceLess) {
		super(health, speed, delay, coordinates, image, healthDefense, imageDefenceLess);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean removeDefenseMagnetic() {
		return super.removeDefenseMagnetic();
	}
	
	@Override
	public boolean spawnInWater() {
		return true;
	}

}
