package gameData;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.Objects;

public class LivingBeing implements Serializable{
	protected int damage;
	protected int health;
	protected  Timer timeToAttackTimer;//Timer pour savoir quand attaquer
	private final int maxHealth;
	private  int height;	//hauteur,si diffÃ©rentes pas d'attaque
	
	public LivingBeing(int damage, int health, int delay,int height) {
		if(damage<0 || health<0 || delay <0) {
			throw new IllegalStateException("Une de vos valeur est négatif elle ne devrait pas l'être");
		}
		this.damage = damage;
		this.health = health;
		this.maxHealth=health;
		this.height=height;
		timeToAttackTimer=new Timer(delay);
	}
	
	public void damage(LivingBeing l) {		
		l.health-= damage;
		if (!l.isAlive()) {
			System.out.println(l+" est mort!");
		}
	}
	
	public int attack(LivingBeing l) {//QUand on attaque le timer d'attaque est remis à 0
		damage(l);
		resetTimer();
		return 0;
	}
	
	public boolean removeDefense() {//POur enelever les defense qu'un zombie possède
		return false;
	}
	
	public void resetTimer() {
		timeToAttackTimer.reset();
	}
	
	public boolean canAttack() {//On peut atatquer quand notre timer le permet
		return timeToAttackTimer.enoughDelay();
	}
	
	public boolean isAlive() {
		return health>0;
	}
	
	@Override
	public String toString() {
		return this.tag()+" inflige "+damage+" dï¿½gats et a "+health+" points de vie.";
	}
	
	public String tag() {
		return "LivingBeing";
	}
	
	public void drawLife(Graphics2D graphics2d,int x,int y,int sizeCell) {//Dessiner la bar de pv de la plante et du zombie
		graphics2d.setColor(Color.RED);
		graphics2d.fill(new Rectangle2D.Float(x,y,(sizeCell+1),((int)(sizeCell*0.1))+1));
		graphics2d.setColor(Color.BLACK);
		graphics2d.fill(new Rectangle2D.Float(x+2,y+2,(sizeCell*((float) (health)/maxHealth))-2,((int)(sizeCell*0.1))-2));
		
	}
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void suicide() {
		health=0;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int h) {
		height=h;
	}
	public boolean slow(int... time) {
		return false;
	}
	
	public void damage(int ouch) {
		health-=ouch;
	}
	
	public boolean removeWheel() {
		return false;
	}
}
