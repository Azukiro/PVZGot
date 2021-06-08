package gameData.Zombies;

import gameData.Coordinates;

public class ConeHeadDuckyTubeZombie extends AbstractWaterUnMettalicDefensesZombie{
	public ConeHeadDuckyTubeZombie(Coordinates c) {
		super(560, 1, 2000, c, 45, 360,44);
	}
	
	@Override
	public String tag() {
		return "ConeHeadDuckyTubeZombie";
	}
}
