package gameData.Zombies;
import gameData.*;
public class BasicZombie extends Zombies {
	
	//basic zombie
	
	public BasicZombie(Coordinates c) {
		super(100, 1, 2000, c,3);	//initialisation de l'attaque, du délai, de l'image et des coordonnées
	}
	
	@Override
	public String tag() {
		return "BasicZombie";
	}
}
