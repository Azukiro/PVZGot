package gameData.Zombies;

import gameData.Coordinates;
import gameData.Timer;

public class AbstractWaterZombie extends Zombies implements WaterZombies{
	
	//Zombies d'eau sans défenses
	
	public AbstractWaterZombie(int health, int speed, int delay, Coordinates coordinates, int image) {
		super(health, speed, delay, coordinates, image);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean spawnInWater() {
		return true;
	}
}
