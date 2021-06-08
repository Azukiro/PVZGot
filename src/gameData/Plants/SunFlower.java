package gameData.Plants;

import gameData.Timer;

public class SunFlower extends Plants{
	private final Timer timer = new Timer(7000);
	protected  int costSun;
	private  boolean firstSun=true;
	public SunFlower() {
		super(0, 300, 34000, 0, 6);
		this.costSun=25;
	}

	public SunFlower(int img,int refill,int costSun) {
		super(0, 300, refill, 0, img);
		this.costSun=costSun;
	}
	@Override
	public String tag() {
		// TODO Auto-generated method stub
		return "SunFlower";
	}
	
	@Override
	public int newSun() {//S'il peut attacker il produit un soleil
		if(timer.enoughDelayWithoutReset() && firstSun==true) {//Premier soleil plus tot que l'attaque normal
			firstSun=false;
			return costSun;
		}
		if(canAttack()) {

			return costSun;
		}
		return super.newSun();
	}
}
