package gameData.Plants;

import gameData.Zombies.Zombies;

public class MagnetShroom extends Plants {
	private boolean isActive;
	public MagnetShroom(boolean isActive) {
		super(0, 100, 0, 3, 80);
		this.isActive=isActive;
	}
	
	@Override
	public int attackManyLine() {
		// TODO Auto-generated method stub
		return 2;
	}
	
	@Override
	public boolean attackManyZombies() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	@Override
	public boolean zombieInRange(Zombies z, int sizeCase, int x) {
		// TODO Auto-generated method stub
		if(isActive) {
			boolean b= super.behindPlantHaveRange(z, sizeCase, x) || super.plantHaveRange(z, sizeCase, x);
			if(b) {
				z.removeDefenseMagnetic();//Enlève les défense au zombie qu'il attaque
			}
		}
		return true;
		
	}
	
	@Override
	public String tag() {
		// TODO Auto-generated method stub
		return "MagnetShroom";
	}
	
	public void wakeUp() {
		isActive=true;
		timeToAttackTimer.reset();
	}
}
