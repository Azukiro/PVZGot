package gameData.Plants;

import gameData.Bot;
import gameData.LivingBeing;
import gameData.Zombies.Zombies;

public class HypnoShroom extends Plants {
	private boolean isActive;
	
	public HypnoShroom(boolean isActive) {
		super(0, 300, 0, 1, 36);
		this.isActive=isActive;
	}
	
	@Override
	public int attack(LivingBeing l) {
		if(isActive) {
			suicide();
			return -1;//Renvoie -1 pour indiquer que le zombie qu'il a touché se retourne
		}
		return 0;
	}
	
	@Override
	public boolean zombieInRange(Zombies z, int sizeCase, int x) {
		if(isActive) {
			return super.zombieInRange(z, sizeCase, x);
		}
		return false;
	}
	@Override
	public String tag() {
		// TODO Auto-generated method stub
		return "HypnoShroom";
	}
	
	public void wakeUp() {
		isActive=true;
	}
}
