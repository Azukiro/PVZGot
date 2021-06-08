package gameData.Zombies;

import gameData.Coordinates;

public class BalloonZombie extends Zombies {

	public BalloonZombie(Coordinates c) {
		super(300, 3, 2000, c, 81, 1);	//ajout d'une hauteur de 1
	}
	
	@Override
	public boolean canAttack() {	//n'attaque pas
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean cantBeStop() {	
		// TODO Auto-generated method stub
		return true;
	}
}
