package gameData.Zombies;

import gameData.Coordinates;

public class BucketHeadZombie extends MetallicDefensesZombies{
	
	public BucketHeadZombie(Coordinates c) {
		super(1300, 1, 2000, c, 23, 1100,3);
	}
	
	@Override
	public boolean removeDefenseMagnetic() {
		return super.removeDefenseMagnetic();
	}
	
	@Override
	public String tag() {
		return "BucketHeadZombie";
	}
}
