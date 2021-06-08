package gameData.Plants;

public class MushRoomProjectile extends Projectile {

	public MushRoomProjectile(int x) {
		super(x);
		setImage(91);
	}
	
	@Override
	public boolean canBeOnFire() {
		// TODO Auto-generated method stub
		return false;
	}

}
