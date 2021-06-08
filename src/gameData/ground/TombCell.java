package gameData.ground;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import gameData.Bot;
import gameData.Coordinates;
import gameData.Plants.GraveBuster;
import gameData.Plants.Plants;
import gameData.Zombies.BasicZombie;
import gameData.Zombies.BucketHeadZombie;
import gameData.Zombies.ConeHead;
import gameData.Zombies.FlagZombie;
import gameData.Zombies.LadderZombie;
import gameData.Zombies.Zombies;

public class TombCell extends Cells {
	private boolean haveTomb=true;
	private int image;
	private Zombies zombies;
	
	public TombCell(Coordinates coordinates) {
		super();
		image=13;//Image de la tombe
		Zombies[] typeZ = {new ConeHead(coordinates), new BucketHeadZombie(coordinates),new BasicZombie(coordinates)}; //liste de tous les types de zombies
		int iZombie = (new Random()).nextInt(typeZ.length); //ajoute un type de zombie al�atoire
		zombies= typeZ[iZombie];;
	}
	
	@Override
	public boolean canAttack(Zombies z, int sizeCase, int x) {
		// TODO Auto-generated method stub
		
		return super.canAttack(z, sizeCase, x);
	}
	
	@Override
	public int attackManyLine() {
		if(plants==null) {return 0;}
		return super.attackManyLine();
	}
	@Override
	public boolean attackManyZombies() {
		if(plants==null) {return false;}
		return super.attackManyZombies();
	}
	@Override
	public int attack(Zombies z) {
		
		return super.attack(z);
		
	}
	
	public void removeTomb() {
		haveTomb=false;
	}
	@Override
	public boolean setPlants(Plants plants) {
		return super.setPlants(plants);
	}
	
	@Override
	public void drawCells(Graphics2D graphics, int x, int y, int height, ArrayList<Image> listiImages) {
		if(!haveTomb) {
			super.drawCells(graphics, x, y, height, listiImages);
		}else {
			graphics.drawImage(listiImages.get(image),x,y,height,height,null);
		}
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Je suis une tombe "+super.toString();
	}
	
	@Override
	public boolean haveTomb() {
		return haveTomb;
	}
	public Zombies spawnZombie() {
		if(haveTomb) {
			haveTomb=false;
			return zombies;
		}
		return null;
	}
	
	
}
