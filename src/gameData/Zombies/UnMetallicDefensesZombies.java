package gameData.Zombies;

import gameData.Coordinates;

public abstract class UnMetallicDefensesZombies extends Zombies{

	protected int healthDefenseLess;
	protected boolean defense;
	protected int imageDefenceLess;
	
	public UnMetallicDefensesZombies(int health, int speed, int delay, Coordinates coordinates, int image,int healthDefense,int imageDefenceLess) {
		super(health, speed, delay, coordinates, image);
		this.imageDefenceLess=imageDefenceLess;
		healthDefenseLess=health-healthDefense;
		defense=true;
	}
	
	//zombies à défenses, change éventuellement d'image lorsque celle-ci est détruite
	
	@Override
	public boolean removeDefense() {
		if (health<=healthDefenseLess && defense==true) {
			defense=false;
			image=imageDefenceLess;
			return true;
		}
		return false;
	}

}
