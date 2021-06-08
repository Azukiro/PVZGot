package gameData.Zombies;

import java.util.ArrayList;
import java.util.Objects;

import gameData.Coordinates;
import gameData.LivingBeing;
import gameData.Timer;
import gameData.ground.Ground;
import gameData.ground.Lines;

public class DancingZombie extends Zombies{
	
	private boolean move;
	private Timer spawningDancersTimer;
	
	private final ArrayList<BackupDancerZombie> dancers;
	
	public DancingZombie(Coordinates c) {
		super(340, 2, 2000, c, 33);
		dancers = new ArrayList<>();
		spawningDancersTimer = new Timer(10000);
		move=true;
	}
	
	@Override
	public String tag() {
		return "DancingZombie";
	}
	
	@Override
	public boolean isAlive() {	//indique à ses danceurs sa mort pour qu'ils puissent être indépendants
		if (health>0) {
			return true;
		}
		for (BackupDancerZombie dancer : dancers) {
			dancer.setMainDancerIsDead(true);
		}
		return false;
	}
	
	@Override
	public boolean inRange(int y, int sizeCell) {	//indique l'arrêt du groupe si une plante à proximité
		if (coordinates.getX()/sizeCell == y) {
			move=false;
			return true;
		}
		move=true;
		return false;
	}
	
	public boolean dancersAreMotionless() {	//indique si le groupe peut bouger
		for (BackupDancerZombie dancer : dancers) {
			if (!dancer.canMove()) {
				return true;
			}
		}
		return !move;
	}
	
	public void moveEveryDancer() {	//fait avancer le groupe
		for (BackupDancerZombie dancer : dancers) {
			dancer.dancingOrderMoveZombie();
		}
	}
	
	@Override
	public void spawnDancers(Lines line,int sizeCell,int i,Ground ground) {		//apparition et réapparition des danceurs
		if (spawningDancersTimer!=null && spawningDancersTimer.enoughDelay()) {
			
			ArrayList<BackupDancerZombie> newDancers = new ArrayList<>();
			newDancers.add(new BackupDancerZombie(new Coordinates(coordinates.getX()-sizeCell, coordinates.getY()),this,"left"));
			newDancers.add(new BackupDancerZombie(new Coordinates(coordinates.getX()+sizeCell, coordinates.getY()),this,"right"));
			newDancers.add(new BackupDancerZombie(new Coordinates(coordinates.getX(), coordinates.getY()+sizeCell),this,"bot"));
			newDancers.add(new BackupDancerZombie(new Coordinates(coordinates.getX(), coordinates.getY()-sizeCell),this,"top"));
			
			for (BackupDancerZombie backupDancerZombie : newDancers) {
				if (!dancers.contains(backupDancerZombie)) {
					ground.putZombie(backupDancerZombie);
					dancers.add(backupDancerZombie);
				}
			}
		}
	}
	
	@Override
	public void damage(LivingBeing l) {		
		Objects.requireNonNull(l);
		
		l.setHealth(l.getHealth()-damage);
		if (!l.isAlive()) {
			System.out.println(l+" est mort!");
			move=true;
		}
	}
	
	@Override
	public void moveZombie() {	//bouge en fonction du groupe
		int move=speed;
		if(back) {
			 move=-speed; 
		}
		if (slowMoveTimer.enoughDelay()) {
			if(slowMoveTimer.getDelay()>0 && slowMoveTimer.getDelay()!=slowTimer[1][0]) {
				this.slow=slowTimer[1][1];
				slowMoveTimer=new Timer(slowTimer[1][0]);
				
			}else {
				if (!dancersAreMotionless()) {
					moveEveryDancer();
					coordinates.substractSpeedToX(move);
				}
				slowMoveTimer=new Timer(0);
			}
		}
		else {
			moveEveryDancer();
			coordinates.substractSpeedToX(speed-(move/slow));
		}
	}
	
	public void removeDancer(BackupDancerZombie dancer) {	//fait disparaitre un de ses danceurs
		for (BackupDancerZombie zombie : dancers) {
			if (zombie.getPos()==dancer.getPos()) {
				dancers.remove(zombie);
				return;
			}
		}
	}
	
	public void resetDancerSpawnTimer() {
		spawningDancersTimer.reset();
	}
	
	public boolean slowDancer(int... time) {
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
	public boolean slow(int... time) {
		slowDancer(time);

		
		for (BackupDancerZombie dancer : dancers) {
			dancer.slowDancer(time);
		}
		
		return true;
	}
}
