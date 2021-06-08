package gameData.Zombies;

import java.util.Objects;
import gameData.Coordinates;
import gameData.LivingBeing;
import gameData.Timer;

public class BackupDancerZombie extends Zombies{
	
	//Zombie danceur, avance par groupe en suivant le DancingZombie si celui-ci n'est pas mort, sinon rupture de la formation
	
	private boolean move;
	private boolean mainDancerIsDead;
	private final DancingZombie mainDancer;
	private final String pos;

	public BackupDancerZombie(Coordinates c,DancingZombie mainDancer,String pos) {
		super(200, 2, 2000, c, 34);
		this.pos=pos;
		move=true;
		mainDancerIsDead=false;
		this.mainDancer=mainDancer;
		
	}
	
	@Override
	public String tag() {
		return "BackupDancer";
	}
	
	@Override
	public void damage(LivingBeing l) {
		Objects.requireNonNull(l);
		
		l.setHealth(l.getHealth()-damage);
		if (!l.isAlive()) {
			System.out.println(l+" est mort!");
			move=true; //permet au groupe d'avancer si le danceur était bloqué par une plante
		}
	}
	
	@Override
	public String toString() {
		return super.toString()+" danceur "+pos;
	}
	
	public void setMainDancerIsDead(boolean mainDancerIsDead) {
		this.mainDancerIsDead = mainDancerIsDead;
	}
	
	public boolean canMove() {
		return move;
	}
	
	public void setMove(boolean move) {
		this.move = move;
	}
	
	@Override
	public boolean slow(int... time) {		//slow tout le groupe si celui-ci est encore actif
		if (mainDancerIsDead) {
			return slowDancer(time);
		}
		return mainDancer.slow(time);
	}
	
	public boolean slowDancer(int... time) {		//slow
		for (int i = 0; i < time.length; i+=2) {
			if(time[i]>=slowTimer[time[i+1]-1][0]) {
				slowTimer[time[i+1]-1][0]=time[i];
				slowTimer[time[i+1]-1][1]=time[i+1];
				if(time[i+1]<=slow) {
					slowMoveTimer=new Timer(time[i]);
					slow=time[i+1];
				}
			}
		}
		
		return true;
	}
	
	@Override
	public void moveZombie() {
		if (!mainDancerIsDead) {	//si le danceur principal n'est pas mort, laisser celui-ci contrôler son groupe
			return;
		}
		int move=speed;	//sinon faire avancer le zombie indépendamment
		if(back) {
			 move=-speed; 
		}
		if (slowMoveTimer.enoughDelay()) {
			if(slowMoveTimer.getDelay()>0 && slowMoveTimer.getDelay()!=slowTimer[1][0]) {
				this.slow=slowTimer[1][1];
				slowMoveTimer=new Timer(slowTimer[1][0]);
				
			}else {
				
				coordinates.substractSpeedToX(move);
				slowMoveTimer=new Timer(0);
			}
		}
		else {
			coordinates.substractSpeedToX(speed-(move/slow));
		}
	}

	
	@Override
	public boolean isAlive() {	//indique au danceur principal qu'il est mort
		if (getHealth()>0) {
			return true;
		}
		move=true;
		mainDancer.removeDancer(this);
		return false;
	}
	
	public void dancingOrderMoveZombie() {	//appelée par le danceur principal
		int move=speed;
		if(back) {
			 move=-speed; 
		}
		if (slowMoveTimer.enoughDelay()) {
			if(slowMoveTimer.getDelay()>0 && slowMoveTimer.getDelay()!=slowTimer[1][0]) {
				this.slow=slowTimer[1][1];
				slowMoveTimer=new Timer(slowTimer[1][0]);
				
			}else {
				
				coordinates.substractSpeedToX(move);
				slowMoveTimer=new Timer(0);
			}
		}
		else {
			coordinates.substractSpeedToX(speed-(move/slow));
		}
	}
	
	@Override
	public boolean inRange(int y, int sizeCell) {	//si prêt d'une plante, indique qu'il ne peut plus bouger
		if (coordinates.getX()/sizeCell == y) {
			move=false;
			return true;
		}
		move=true;
		return false;
	}
	
	public String getPos() {
		return pos;
	}
	
	@Override
	public boolean equals(Object obj) {	//equals par position du groupe
		if (!(obj instanceof BackupDancerZombie)) {
			return false;
		}
		BackupDancerZombie zombie = (BackupDancerZombie) obj;
		return	pos.equals(zombie.getPos());
		
	}
	
	@Override
	public int hashCode() {
		return pos.hashCode();
	}
}
