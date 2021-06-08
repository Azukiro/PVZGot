package gameData.Plants;

import gameData.Zombies.Zombies;

public class FumeShroom extends PuffShroom  {
	
	public FumeShroom(boolean isActive) {
		super(19,isActive);//degats,pv,delais d'attaque,couts,temps de recharge,range d'attaque
		
		
	}
	
	@Override
	public void addProjectileByPosition(int x, int sizeCase) {
		add(new MushRoomProjectile((x*sizeCase)+((int)(sizeCase*0.5))));
		add(new MushRoomProjectile((x*sizeCase)+((int)(sizeCase*0.5))));
	}
	
	@Override
	public boolean zombieInRange(Zombies z, int sizeCase, int x) {
		// TODO Auto-generated method stub
		return super.zombieInRange(z, sizeCase, x);
	}
	@Override
	public String tag() {
		// TODO Auto-generated method stub
		return "FumeShroom";
	}
}
