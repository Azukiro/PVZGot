package gameData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import gameData.Zombies.Zombies;
import gameData.ground.*;
public class Wave implements Serializable{
	private int nbZombies;	//nombre de zombies restants à faire apparaitre, s'actualise
	private boolean flagSpawned;	//flag zombie spawné si la vague est supérieure à ... zombies
	private final int maxDelay;	//délai maximum entre chaque apparition de zombie 
	private Timer zTimer;
	private ArrayList<Zombies> zombiesSpawned = new ArrayList<>();	//liste des zombies apparus
	private final int delayEndWave;	//éventuel délai à la fin de la vague avant de passer à la suivante
	private Timer timerBetweenWaves;
	private ArrayList<Zombies> zombies = new ArrayList<>(); //liste entière des zombies de la vague
	private int zPos;	//zombie à faire apparaitre
	
	public Wave(int nbZombies, int maxDelay) {
		this.nbZombies=nbZombies;
		this.maxDelay=maxDelay*1000;
		Random r= new Random();
		int randomDelay = 1000 + r.nextInt(maxDelay);
		zTimer = new Timer(randomDelay);
		delayEndWave=0;
		timerBetweenWaves = new Timer(delayEndWave);
		flagSpawned=false;
		zPos=0;
	}
	
	public Wave(int nbZombies, int maxDelay, int delayEndWave) {
		this.nbZombies=nbZombies;
		this.maxDelay=maxDelay*1000;
		Random r= new Random();
		int randomDelay = 1000 + r.nextInt(maxDelay);
		zTimer = new Timer(randomDelay);
		this.delayEndWave=delayEndWave*1000;
		flagSpawned=false;
		zPos=0;
	}
	
	public int walk(Ground ground,int nbZombiesLevel) {
		Objects.requireNonNull(ground);
		if (nbZombies>5 && flagSpawned==false) {	//fait apparaitre un flag zombie si la vague est supérieure à 5 zombies
			flagSpawned=true;
			ground.putFlagZombieRandomly();
		}
		
		if (nbZombies>0) {
			if (zTimer.enoughDelay()) {	//si le zombie peut apparaitre
				zombies.get(zPos).setCoordinates(ground.randomCoordinatesFromZombie(zombies.get(zPos)));
				ground.putZombie(zombies.get(zPos));
				zombiesSpawned.add(zombies.get(zPos));
				zPos+=1;
				nbZombies-=1;
				nbZombiesLevel-=1;
				Random r= new Random();	//délai d'apparition du prochain zombie, entre 0 et le délai maximal
				int randomDelay = 1000 + r.nextInt(maxDelay);
				zTimer = new Timer(randomDelay);
			}
		}
		return nbZombiesLevel;
	}
	
	public boolean isFinished() {	//indique si la vague est terminée
		for (Zombies zombies : zombiesSpawned) {
			if (zombies.isAlive()) {
				return false;
			}
		}
		if (nbZombies==0) {
			if (timerBetweenWaves==null) {
				timerBetweenWaves =new Timer(delayEndWave);
				return false;
			}
			else if (timerBetweenWaves.enoughDelay()) {
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}
	
	public int getNbZombies() {
		return nbZombies;
	}
	
	public void initializeSpawn(Ground ground,int width,int height) {	//initialise les zombies à faire apparaitre en fonction du terrain
		for (int i = 0; i < nbZombies; i++) {
			zombies.add(ground.zombiesPlaying(width, height));
		}
	}
	
	public ArrayList<Zombies> getZombies() {
		return zombies;
	}
}
