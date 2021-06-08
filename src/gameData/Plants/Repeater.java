package gameData.Plants;

import gameData.Zombies.Zombies;

public class Repeater extends Peashooter {
	public Repeater() {
		super(10);
	}
	
	@Override
	public boolean zombieInRange(Zombies z, int sizeCase, int x) {
		if( super.zombieInRange(z, sizeCase, x)) {//Attaque avec deux projectile
			add(new Projectile(x*sizeCase+(int)(sizeCase*0.1)));
			return true;
		}
		return false;
		
	}
	@Override
	public String tag() {
		// TODO Auto-generated method stub
		return "Repeater";
	}
}
