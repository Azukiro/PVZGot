package gameData.Plants;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import gameData.LivingBeing;
import gameData.Timer;
import gameData.Zombies.Zombies;

public class ThreePeater extends Peashooter {
	private boolean iAttack=false;
	private Timer canAttackTimer=new Timer(3000);//Meme principe que la cheerybomb timer intern pour lui permettre de vérifier s'il peut attaquer les différente ligne
	private int nbAttack=0;
	
	public ThreePeater() {
		super(40);
	}
	@Override
	public int attackManyLine() {
		// TODO Auto-generated method stub
		return 1;
	}
	
	@Override
	public void addProjectiles(ArrayList<Projectile> projectiles, int i, int sizeCell) {
		nbAttack++;
		super.addProjectiles(projectiles, i, sizeCell);
	}
	@Override
	public int attack(LivingBeing l) {
		if(iAttack) {

			return super.attack(l);
		}
		return 0;
	}
	@Override
	public boolean zombieInRange(Zombies z, int sizeCase, int x) {
		
		iAttack=super.plantHaveRange(z, sizeCase, x);
		
		if(iAttack && canAttackTimer.enoughDelayWithoutReset()) {
			canAttackTimer.reset();
			if(nbAttack%3==0) {//EN fonction du modulo donc de la ligne ou il détecte le zombie on change les projectile qu'il produit
				add(new Projectile(x*sizeCase, 1) );
				add(new Projectile(sizeCase*x));
				add(new Projectile(x*sizeCase, -1) );
			}else if(nbAttack%3==1) {
				add(new Projectile(x*sizeCase, +1) );
				add(new Projectile(sizeCase*x));
				add(new Projectile(x*sizeCase, +2) );
			}else if(nbAttack%3==2) {
				add(new Projectile(x*sizeCase, -1) );
				add(new Projectile(sizeCase*x));
				add(new Projectile(x*sizeCase, -2) );
			}
			
		}
		if(nbAttack==3) {
			nbAttack=0;
		}
		return true;
	}
	@Override
	public void draw(Graphics2D graphics, int x, int y, int height, ArrayList<Image> imagesList) {
			
		super.draw(graphics, x, y, height, imagesList);
	}
	
	@Override
	public String tag() {
		// TODO Auto-generated method stub
		return "ThreePeater";
	}
}
