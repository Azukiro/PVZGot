package gameData.Zombies;

import gameData.Coordinates;

public class NewsPaperZombie extends UnMetallicDefensesZombies{
	public NewsPaperZombie(Coordinates c) {
		super(340, 1, 2000, c, 24, 140, 25);
	}
	
	@Override
	public String tag() {
		return "NewsPaperZombie";
	}
	
	@Override
	public boolean removeDefense() {
		if (health<=healthDefenseLess && defense==true) {	//charge lorsqu'il perd son journal
			defense=false;
			speed=5;
			image=imageDefenceLess;
			return true;
		}
		return false;
	}
}
