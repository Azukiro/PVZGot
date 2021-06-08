package gameData.Zombies;

import gameData.Coordinates;

public class BasicDuckyTubeZombie extends AbstractWaterZombie {
	public BasicDuckyTubeZombie(Coordinates c) {	//basic zombie avec une bouée
		super(100, 1, 2000, c,3);
		image=44;
	}
	
	@Override
	public String tag() {
		return "BasicDuckyTubeZombie";
	}
}
