package gameData.ground;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import gameData.Bot;
import gameData.Plants.Plants;
import gameData.Zombies.Zombies;

public class PlantsCell<T extends Plants> extends Cells {//Ou T est le type de la base de la cellule
	protected T base=null ;
	
	public PlantsCell() {
		super();
	}
	
	@Override
	public boolean setPlants(Plants plants) {
		boolean reset=false;
		if(plants==null) {
			if(!havePlants()&& ! havePumpkin()) {
				base=null;
			}
		}
		if(base==null) {
			reset=true;
		}
		return super.setPlants(plants) || (base!=null && reset==true);//Le reset permet de voir si la base à était posé et qu'il faut donc reset le timer de la carte de la base
	}
	@Override
	public void cleanCell() {//On supprime s'il n'y a pas de plantes la base
		if(plants==null && !havePumpkin()) {
			if(base!=null) {
				if(!base.isAlive()) {
					base=null;
					return;
				}
			}
			return;
		}
		super.cleanCell();
	}
	
	@Override
	public Plants getPlants() {//POur obtenir  la plante ative sur la cellule
		if (havePlants() || havePumpkin()) {
			return super.getPlants();
		}
		else {
			return base;
		}
	}
	@Override
	public boolean canAttack(Zombies z, int sizeCase, int x) {
		if(!(havePlants() || havePumpkin()) && base!=null) {
			return false;
		}
		return super.canAttack(z, sizeCase, x);
	}
	@Override
		public int attack(Zombies z) {
			int delay=super.attack(z);
			if(delay>0 ) {
				base=null;
			}
			return delay;
		}
	@Override
	public int newSun() {
		if(base!=null && !(havePlants() || havePumpkin()) ) {
			return base.newSun();
		}
		return super.newSun();
	}
	
	
	@Override
	public boolean haveBase() {
		return base!=null;
	}

	public void putBase(T p) {//Pour mettre la base du type demandé
		if(destroyTimerEnoughDelay()) {

			base=p;
		}
	}
	
	@Override
	public boolean colision(Zombies z) {
		if(base!=null && !(havePlants() && havePumpkin()) ) {
			return base.colision(z);
		}
		return super.colision(z);
	}
	@Override
	public void drawCells(Graphics2D graphics,int x,int y,int height,ArrayList<Image> listiImages) {
		if (base!=null) {//On dessine la base et apres le reste de la cellule par dessu
			base.draw(graphics, x, y, height, listiImages);
		}
		super.drawCells(graphics, x, y, height, listiImages);
	}
	
}
