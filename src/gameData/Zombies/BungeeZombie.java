package gameData.Zombies;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import gameData.Coordinates;
import gameData.LivingBeing;

public class BungeeZombie extends Zombies {
	private Zombies zombies;//Zombie pouvant etre contenu dans le bugee s'il n'attque pas de plante
	private boolean attacked=false;
	public BungeeZombie(Coordinates c) {
		super(460, 0, 2500, c, 75);
		zombies=null;
	}
	//Voir si on ajoute un null dans wichzombieAttack et du coup la mettre oui avec timer et tout et tout
	
	public BungeeZombie(Coordinates coordinates, Zombies zombies) {
		this(coordinates);
		this.zombies=zombies;
	}
	
	@Override
	public int attack(LivingBeing l) {
		if(zombies!=null) {
			return 0;
		}
		l.suicide();
		suicide();//S'il attaque une plante il la oneshot et meurt
		return 0;
	}
	
	@Override
	public Zombies wakeUpZombie() {
		if(zombies!=null && canAttack()) {//Si le bungee a atteint son délais d'attque il meurt en posant un zombie

			suicide();
			return zombies;
		}
		return null;
		
	}
	
	@Override
	public void draw(Graphics2D graphics, int height, ArrayList<Image> imagesList) {
		// TODO Auto-generated method stub
		super.draw(graphics, height, imagesList);
		if(zombies!=null) {
			zombies.draw(graphics,(int) (height*0.5), imagesList);
		}
	}
	@Override
	public String tag() {
		// TODO Auto-generated method stub
		return "BungeeZombie";
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+" et je contient ["+zombies+"]";
	}
}
