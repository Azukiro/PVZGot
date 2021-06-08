package gameData.Plants;

import java.util.Random;

import gameData.LivingBeing;

public class KernelPult extends CabbagePult {
	
	public KernelPult() {
		super(60);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int attack(LivingBeing l) {
		int rdn=(new Random()).nextInt(3);//Peut slow un ennemie 1 fois sur 3 donc aléatoirement
		if(rdn==0) {
			damage=40;
			super.attack(l);
			l.slow(2000,1);//Slow l'ennemeie totalement pendant 2 sec
			damage=20;
			return 0;
		}
		return super.attack(l);
	}
	@Override
	public String tag() {
		// TODO Auto-generated method stub
		return "KernelPult";
	}
}
