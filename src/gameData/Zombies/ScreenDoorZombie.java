package gameData.Zombies;

import gameData.Coordinates;

public class ScreenDoorZombie extends MetallicDefensesZombies{

	public ScreenDoorZombie(Coordinates c) {
		super(1300, 2, 2000, c, 26, 1100, 3);
	}
	
	@Override
	public boolean removeDefenseMagnetic() {	
		// TODO Auto-generated method stub
		return super.removeDefenseMagnetic();
	}
	@Override
	public String tag() {
		return "ScreenDoorZombie";
	}
	
}
