package gameData.Plants;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;

import gameData.Bot;
import gameData.LivingBeing;
import gameData.Zombies.Zombies;



public class Peashooter extends Plants{
	protected ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	public Peashooter() {
		super(40,300,3000,10,0);//degats,pv,delais d'attaque,couts,temps de recharge,range d'attaque
		
	}
	
	public Peashooter(int img) {
		super(20,300,5000,10,img);//degats,pv,delais d'attaque,couts,temps de recharge,range d'attaque
		
	}
	
	public Peashooter(int damage, int health,int delay ,int range,int image) {
		super(damage, health, delay, range, image);
	}
	
	public void add(Projectile p) {
		projectiles.add(p);
	}
		
	@Override
	public void addProjectiles(ArrayList<Projectile> projectiles, int i, int sizeCell){
		projectiles.addAll(this.projectiles);//Ajoute ses projectiles à ceux du plateau puis les supprime
		this.projectiles.clear();
	}
	
    public void addProjectileByPosition(int x,int sizeCase) {
    	 projectiles.add(new Projectile(sizeCase*x));
    }
    
	@Override
	public String tag() {
		return "Peashooter";
	}
	
	
	@Override
	public boolean zombieInRange(Zombies z, int sizeCase, int x) {
		
		 boolean b= zombieInRangeWithoutProjectile(z, sizeCase, x);
		 if(b) {//S'il peut attaquer il ajoute un projectiles a sa liste
			addProjectileByPosition(x, sizeCase);
		 }
		 return b;
	}
	
	public boolean zombieInRangeWithoutProjectile(Zombies z, int sizeCase, int x) {
		
		 boolean b= super.zombieInRange(z, sizeCase, x);
		 return b;
	}
	
	@Override
	public int attack(LivingBeing l) {
		
		return 0;
	}
	@Override
	public void draw(Graphics2D graphics, int x, int y, int height, ArrayList<Image> imagesList) {

		super.draw(graphics, x, y, height, imagesList);
	}
}
