package gameData.Plants;

import gameData.LivingBeing;
import gameData.Zombies.ZamboniZombie;
import gameData.Zombies.Zombies;

public class SpikeWeed extends Plants {
	
	public SpikeWeed() {
		super(10, 300, 1000, 1, 41);
	}
	
	@Override
	public int attack(LivingBeing l) {
		if(!l.removeWheel()){//Si un zombie ayant des roue passe dessus il tue le zombie et se suicde sinon il l'attque juste

			return super.attack(l);
		}
		suicide();
		return 0;
	}
	@Override
	public boolean colision(Zombies z) {
		// TODO Auto-generated method stub
		return false;//Les zombie lui passe dessus 
	}
	
	@Override
	public String tag() {
		// TODO Auto-generated method stub
		return "SpikeWeed";
	}
	
}
