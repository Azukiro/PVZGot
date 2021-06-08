package gameData.Zombies;

import gameData.Coordinates;

public class BucketHeadDuckyTubeZombie extends AbstractWaterMettalicDefensesZombie {
	public BucketHeadDuckyTubeZombie(Coordinates c) {
		super(1300, 1, 2000, c, 46, 1100,44);
	}
	
	@Override
	public String tag() {
		return "BucketHeadDuckyTubeZombie";
	}
}
