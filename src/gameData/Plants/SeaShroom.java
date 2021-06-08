package gameData.Plants;

import gameData.ground.Cells;

public class SeaShroom extends Peashooter{
	public SeaShroom() {
		super(42);//degats,pv,delais d'attaque,couts,temps de recharge,range d'attaque
		
	}
	
	
	public void addProjectileByPosition(int x,int sizeCase) {
		add(new WaterProjectile(x*sizeCase));
	}
	
	@Override
	public boolean activePlant(Cells cells) {
		if(cells.haveBase()) {//ne peut etre proser sur une cellule ayant une base car il ne va que dans l'eau
			return false;
		}
		return true;
	}
	
	@Override
	public String tag() {
		// TODO Auto-generated method stub
		return "SeaShroom";
	}
}

