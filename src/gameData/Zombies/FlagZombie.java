package gameData.Zombies;

import gameData.Coordinates;

public class FlagZombie extends Zombies{
	
	public FlagZombie(Coordinates c) {
		super(200, 3, 1000,c,5);
	}
	
	@Override
	public String tag() {
		return "FlagZombie";
	}
}
	