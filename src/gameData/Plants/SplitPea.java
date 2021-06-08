package gameData.Plants;

import gameData.Zombies.Zombies;

public class SplitPea extends Peashooter {
	
	public SplitPea() {
		super(76);
	}
	
	
	@Override
	public boolean zombieInRange(Zombies z, int sizeCase, int x) {
		// TODO Auto-generated method stub
		boolean b= super.plantHaveRange(z, sizeCase, x);
		b= (b || super.behindPlantHaveRange(z, sizeCase, x)) && canAttack();//On regarde s'il peut attaquer derrière avant de regarder s'il a le délais pour attaquer
		if(b) {

			add(new Projectile(x*sizeCase));
			add(new Projectile(x*sizeCase, true));//On envoit en projectiles allant dans l'autre sens
		}
		return b;
	}
	
	@Override
	public String tag() {
		// TODO Auto-generated method stub
		return "SplitPea";
	}
}
