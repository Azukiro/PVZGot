package gameData.Plants;

import java.util.Random;

import gameData.Bot;
import gameData.Zombies.Zombies;

public class Garlic extends Plants {

	public Garlic() {
		super(0, 1000, 0, 1, 62);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean zombieInRange(Zombies z, int sizeCase, int x) {
		int rdn = (new Random()).nextInt(2);//Choisir la direction de décalage
		boolean b= super.zombieInRange(z, sizeCase, x);
		if(b && z!=null && !z.horizontalMoveActive()) {//S'il elle attaque et que le zombie n'est pas entrain de bouger elle le dplace horizontallement
			if(rdn==0) {
				z.setHorizontalMove(1);
				
			}else {
				z.setHorizontalMove(-1);
			}
		}
		return b;
	}
	@Override
	public boolean colision(Zombies z) {
		
		
		return false;
	}
	
	@Override
	public String tag() {
		// TODO Auto-generated method stub
		return "Garlic";
	}
}
