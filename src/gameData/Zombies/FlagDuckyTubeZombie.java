package gameData.Zombies;

import gameData.Coordinates;

public class FlagDuckyTubeZombie extends AbstractWaterZombie{
	public FlagDuckyTubeZombie(Coordinates c) {
		super(200, 3, 1000,c,47);
	}
	
	@Override
	public String tag() {
		return "FlagDuckyTubeZombie";
	}
}
