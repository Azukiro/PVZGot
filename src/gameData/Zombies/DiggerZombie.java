package gameData.Zombies;

import gameData.Coordinates;

public class DiggerZombie extends MetallicDefensesZombies{
	private boolean back=false;
	public DiggerZombie(Coordinates coordinates) {
		super(500, 3, 2000, coordinates, 94, 200,93);
		setHeight(-1);//Il est en sous sol
	}
	
	@Override
	public boolean removeDefense() {
		setHeight(0);//Quand les défense sont enlever il remonte a la surface
		back=true;
		return super.removeDefense();
	}
	@Override
	public boolean cantBeStop() {
		if(back) {//On ne peut pas le stopper quand il est sous terre
			return false;
		}
		return true;
	}
	
	@Override
	public boolean canAttack() {
		if(back) {//Il attaque que quand il fait demitour
			return super.canAttack();
		}
		return false;
	}
	
	@Override
	public boolean canGoBack() {
		if(!back && haveDefense()) {//Pour lui faire faire demi tour au bout du terrain
			image--;
			speed=-speed;
			setHeight(0);
			back=true;
		}
		return true;
	}

}
