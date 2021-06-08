package gameData.Plants;

import gameData.Bot;
import gameData.LivingBeing;

public class PotatoMine extends Plants {
	
	private final int imageArmed;
	
	public PotatoMine(int damage, int health, int delay, int range, int image) {
		super(1800, 300, 14000, 1, 11);
		imageArmed=12;
	}
	
	public PotatoMine() {
		super(1800, 300, 14000, 1, 11);
		imageArmed=12;
	}
	
	@Override
	public int attack(LivingBeing l) {//Elle meurt au moment du tuer son adversaire
		super.attack(l);
		suicide();
		return 0;
	}
	
	@Override
	public boolean canAttack() {//S'il peut attaquer on l'arme
		if(timeToAttackTimer.enoughDelayWithoutReset()) {
			image=imageArmed;
			return true;
		}
		return false;
	}
	
	@Override
	public String tag() {
		return "PotatoMine";
	}
	
}
