package gameData.Plants;

import gameData.Bot;
import gameData.LivingBeing;
import gameData.Zombies.Zombies;

public class SnowPea extends Peashooter{
	public SnowPea() {
		super(20, 300, 5000, 10, 22);
	}
	
	@Override
	public int attack(LivingBeing l) {
		return 0;
	}
	
	@Override
	public void addProjectileByPosition(int x, int sizeCase) {
		// TODO Auto-generated method stub
		add(new IceProjectile(x*sizeCase));//Il produit des projectiles de glace à la place des prohetciles standars
	}
	@Override
	public boolean zombieInRange(Zombies z, int sizeCase, int x) {
		
		return super.zombieInRange(z, sizeCase, x);
	}
	@Override
	public String tag() {
		return "SnowPea";
	}
}
