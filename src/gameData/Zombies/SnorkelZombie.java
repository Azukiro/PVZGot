package gameData.Zombies;

import java.util.Objects;

import gameData.Coordinates;
import gameData.LivingBeing;

public class SnorkelZombie extends AbstractWaterZombie{
	public SnorkelZombie(Coordinates c) {
		super(200, 2, 2000, c, 50);
		setHeight(-1);
	}
	
	@Override
	public int attack(LivingBeing l) {	//sort de l'eau lorsqu'il attaque
		image=51;
		setHeight(1);
		return super.attack(l);
	}
	
	@Override
	public void damage(LivingBeing l) {	//si il tue la plante, retourne dans l'eau
		Objects.requireNonNull(l);
		
		l.setHealth(l.getHealth()-damage);
		if (!l.isAlive()) {
			image=50;
			setHeight(-1);
			System.out.println(l+" est mort!");
		}
	}
}
