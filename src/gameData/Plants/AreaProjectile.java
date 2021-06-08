package gameData.Plants;

import gameData.Zombies.Zombies;

public class AreaProjectile extends Projectile {
	/*Projectiles pour les zombie volant*/
	public AreaProjectile(int x) {
		super(x);
	}
	
	
	@Override
	protected boolean goodHeight(Zombies z) {
		// TODO Auto-generated method stub
		return z.getHeight()==1;
	}
	
}
