package gameData.Zombies;

import gameData.Coordinates;

public class ConeHead extends UnMetallicDefensesZombies {
	public ConeHead(Coordinates c) {
		super(560, 1, 2000, c, 4, 360,3);
	}
	
	@Override
	public String tag() {
		return "ConeHeadZombie";
	}
}
