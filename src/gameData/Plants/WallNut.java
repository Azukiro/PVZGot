package gameData.Plants;


public class WallNut extends AbtractDefensivePlant{
	public WallNut() {
		super(4000,1);
	}
	
	@Override
	public boolean canAttack() {
		return false;
	}
	
	@Override
	public String tag() {
		return "WallNut";
	}
	
}
