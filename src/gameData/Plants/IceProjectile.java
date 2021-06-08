package gameData.Plants;

import gameData.LivingBeing;

public class IceProjectile extends Projectile {
	//Projectile pour slow les zombie
	public IceProjectile(int x) {
		super(x);
		setImage(92);
	}
	
	@Override
	public void attack(LivingBeing l) {
		super.attack(l);
		l.slow(2000,2);
	}
}
