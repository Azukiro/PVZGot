package gameData.Plants;

public class CabbagePult extends Plants{

	public CabbagePult() {
		super(40, 300, 2000, 10, 59);
		// TODO Auto-generated constructor stub
	}
	
	public CabbagePult(int img) {
		super(40, 300, 2000, 10, img);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean attackRoof() {
		// TODO Auto-generated method stub
		return !super.attackRoof();
	}
	
	@Override
	public String tag() {
		// TODO Auto-generated method stub
		return "CabbagePult";
	}
}
