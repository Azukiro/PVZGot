package gameData.Plants;

public class WaterProjectile extends Projectile {
	public WaterProjectile(int x) {
		super(x);
		setImage(90);
	}
	
	@Override
	public boolean canBeOnFire() {
		// TODO Auto-generated method stub
		return false;
	}
}
