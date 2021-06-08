package gameData.Plants;

import gameData.Bot;
import gameData.LivingBeing;
import gameData.Zombies.Zombies;

public class PuffShroom extends Peashooter  {
	protected boolean isActive;
	public PuffShroom(boolean isActive) {
		this(18, isActive);//degats,pv,delais d'attaque,couts,temps de recharge,range d'attaque
		
		
	}
	
	public PuffShroom(int img, boolean isActive) {
		super(img);//degats,pv,delais d'attaque,couts,temps de recharge,range d'attaque
		this.isActive=isActive;
	}

	@Override
	public boolean zombieInRange(Zombies z, int sizeCase, int x) {
		if(isActive) {
			return super.zombieInRange(z, sizeCase, x);
		}
		return false;
		
	}
	
	@Override
	public int attack(LivingBeing l) {
		if(isActive) {
			return super.attack(l);
		}
		return 0;
	}
	@Override
	public void addProjectileByPosition(int x,int sizeCase) {
		add(new MushRoomProjectile(x*sizeCase));
	}

	@Override
	public String tag() {
		// TODO Auto-generated method stub
		return "PuffShroom";
	}
	
	public void wakeUp() {
		isActive=true;
	}
}
