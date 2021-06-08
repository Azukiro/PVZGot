package gameData.Zombies;

import gameData.Coordinates;

public class FootballZombie extends MetallicDefensesZombies{

	public FootballZombie(Coordinates c) {
		super(1600, 3, 2000, c, 27, 1400, 3);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean removeDefenseMagnetic() {
		// TODO Auto-generated method stub
		return super.removeDefenseMagnetic();
	}
	
	@Override
	public String tag() {
		return "FootballZombie";
	}

}
