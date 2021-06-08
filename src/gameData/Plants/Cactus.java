package gameData.Plants;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import gameData.Zombies.Zombies;

public class Cactus extends Peashooter{
	private boolean upCactus=false;//Boolean pour quand le cactus et en l'air
	public Cactus() {
		super(82);
	}
	
	@Override
	public boolean zombieInRange(Zombies z, int sizeCase, int x) {
		
		boolean down= super.plantHaveRange(z, sizeCase, x);
		if(down && canAttack() ) {
			add(new Projectile(x*sizeCase));
			upCactus=false;
		}
		
		
		boolean up = plantHaveRangeWithoutheigth(z, sizeCase, x) && z.getHeight()==1 && canAttack();//On regarde si le cactus peut attaquer un zombie bolant
		if(up) {
			upCactus=true;
			add(new AreaProjectile(x*sizeCase));//On ajoute un projectiles volant
		}
		
		return up || down;
	}
	
	@Override
	public void draw(Graphics2D graphics, int x, int y, int height, ArrayList<Image> imagesList) {
		if(upCactus) {//Modifie l'image pour le faire grandir
			image++;
		}
		super.draw(graphics, x, y, height, imagesList);
		if(upCactus) {
			image--;
		}
	}
	
	@Override
	public String tag() {
		// TODO Auto-generated method stub
		return "Cactus";
	}
}
