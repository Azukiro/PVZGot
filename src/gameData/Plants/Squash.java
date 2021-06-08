package gameData.Plants;

import gameData.Bot;
import gameData.LivingBeing;
import gameData.Zombies.Zombies;

public class Squash extends Plants {
	private boolean candie=false;//permet de s'assurer que meme s'il le zombie qu'il a detecté meurt avant que le squash attaque il se suicidera
	//car il aura attaqué pour lui
	public Squash() {
		super(10000, 300, 0, 1, 37);
	}
	
	@Override
	public boolean zombieInRange(Zombies z, int sizeCase, int x) {
		
		boolean b= super.zombieInRange(z, sizeCase, x);
		if(b) {
			candie=true;
		}else if(candie) {
			suicide();
		}
		return b;
	}
	@Override
	public boolean attackManyZombies() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public int attack(LivingBeing l) {
		
		return super.attack(l);
	}
	
	@Override
	public String tag() {
		// TODO Auto-generated method stub
		return "Squash";
	}
}
