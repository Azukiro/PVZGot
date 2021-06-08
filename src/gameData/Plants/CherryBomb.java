package gameData.Plants;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import gameData.Bot;
import gameData.LivingBeing;
import gameData.Timer;
import gameData.Zombies.Zombies;

public class CherryBomb extends Plants{
	private Timer attackTimer=new Timer(1200);//Timer d'explosion
	private boolean iAttack;
	private boolean isAlive;
	public CherryBomb() {
		super(1800, 1799,0, 2,2);
	}
	
	public CherryBomb(int range,int img) {
		super(1800, 1799,0, range,img);
	}
	
	@Override
	public boolean inHeight(Zombies z) {
		return (z.getHeight()==getHeight() || z.getHeight()==1 || z.getHeight()==-1);
	}
	
	@Override
	public boolean zombieInRange(Zombies z, int sizeCase, int x) {
		// TODO Auto-generated method stub
		iAttack = super.zombieInRange(z, sizeCase, x) || super.behindZombieInRange(z, sizeCase, x);//On garde en mémoire si elle peut tuer 
		
		
			
		isAlive= attackTimer.enoughDelayWithoutReset();
		return isAlive;
		
	}
	@Override
	public boolean colision(Zombies z) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return !isAlive;//Elle est en vie quand le timer n'a pas atteint son délais
	}
	
	@Override
	public int attack(LivingBeing l) {
		// TODO Auto-generated method stub
		if(iAttack){//Si elle peut attaquer elle le fait
			super.damage(l);
		}
		return 0;
		
		
	}
	@Override
	public int attackManyLine() {
		return 1;
	}
	
	
	
	@Override
	public boolean attackManyZombies() {
		return true;
	}
	
	@Override
	public String tag() {
		return "CherryBomb";
	}
	
	@Override
	public void draw(Graphics2D graphics, int x, int y, int height, ArrayList<Image> imagesList) {
		// TODO Auto-generated method stub
		height=(int) (height*(0.5+(double)((double)attackTimer.chronomill()/(double)attackTimer.getDelay())));//Pour faire croissir la cherrybomb avant son explosion
		super.draw(graphics, x, y, height, imagesList);
	}
	public void drawWithouGrow(Graphics2D graphics, int x, int y, int height, ArrayList<Image> imagesList) {
		// TODO Auto-generated method stub
		super.draw(graphics, x, y, height, imagesList);
	}
	
	@Override
	public boolean attackRoof() {
		// TODO Auto-generated method stub
		return !super.attackRoof();
	}
	
	
}
