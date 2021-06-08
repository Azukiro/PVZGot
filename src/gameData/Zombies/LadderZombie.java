package gameData.Zombies;

import gameData.Coordinates;
import gameData.LivingBeing;
import gameData.Plants.Plants;

public class LadderZombie extends MetallicDefensesZombies{
	
	public LadderZombie(Coordinates c) {
		super(2000, 2, 2000, c, 63, 500, 64);
	}
	
	@Override
	public boolean removeDefenseMagnetic() {
		// TODO Auto-generated method stub
		return super.removeDefenseMagnetic();
	}
	
	@Override
	public int attack(LivingBeing l) {
		Plants p=(Plants) (l);
		if(haveDefense() && p.putLadder()) {//Si le zombie à pu poser son échelle on lui enleve ses défense
			removeDefenseMagnetic();
			return 0;
		}
		return super.attack(l);
		
	}
	
	@Override
	public String tag() {
		return "LadderZombie";
	}
	
}
