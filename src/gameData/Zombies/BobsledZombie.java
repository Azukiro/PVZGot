package gameData.Zombies;

import gameData.Coordinates;

public class BobsledZombie extends Zombies{
	
	public BobsledZombie(Coordinates c) {
		super(200, 2, 2000, c, 55);
	}
	
	@Override
	public String tag() {
		return "BobsledZombie";
	}
}
