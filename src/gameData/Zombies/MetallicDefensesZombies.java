package gameData.Zombies;

import gameData.Coordinates;

public abstract class MetallicDefensesZombies extends UnMetallicDefensesZombies{
	
	public MetallicDefensesZombies(int health, int speed, int delay, Coordinates coordinates, int image,int healthDefense,int imageDefenceLess) {
		super(health, speed, delay, coordinates, image, healthDefense,imageDefenceLess);
	}
	
	//spécifique au champignon magnétique
	
	public boolean removeDefenseMagnetic() { 
		if (health>healthDefenseLess) {
			health=healthDefenseLess;
			defense=false;
			image=imageDefenceLess;
			return true;
		}
		return false;
	}
	
	public boolean haveDefense() {
		return health>healthDefenseLess;
	}
	
}
