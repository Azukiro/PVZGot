package gameData.ground;
import gameData.Timer;
import gameData.Plants.*;
import gameData.Zombies.*;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;


public class Cells implements Serializable{
	private Timer destroyCaseTimer=new Timer(0);//Timer pour quand on ne peut pas poser de plante sur la cellule du à son altératiob
	protected Plants plants;//La plante contenu dans la cellule
	private Fog fog=new Fog();//Un brouillairs au cas ou il serait effectif sur celle si
	private Pumpkin pumpkin=null;//La pumpkin car elle vient par dessus une plante dans la cellule
	
	public Cells() {
		plants=null;
	}
	
	public boolean setPlants(Plants plants) {//Pour mettre la plante

		if(!destroyCaseTimer.enoughDelay()) {//Si la cellule est détruite on ne peut pas poser la plante
			return false;
		}
					
		if(destroyCaseTimer.getDelay()>0) {
			destroyCaseTimer=new Timer(0);
		}
		
		if(plants==null) {//Si la plante est null et correspond donc à la pelle on la met à la place
			if(havePumpkin()) {
				pumpkin=null;
			}
			this.plants=plants;
			return true;
		}
		
		if(plants.activePlant(this)) {//La plante dis celle peut etre posé
							
			this.plants = plants;
			return true;
		}
		return false;
		
		
	}
	
	public boolean destroyTimerEnoughDelay() {
		return destroyCaseTimer.enoughDelay();
	}
	public boolean havePlants() {
		return plants!=null;
	}
	
	public Plants getReallyPlants() {
		return plants;
	}
	public Plants getPlants() {
		if(pumpkin==null) {
			return plants;
		}
		return pumpkin;
	}
	
	public boolean canAttack(Zombies z,int sizeCase,int x) {//On regarde si la plante peut attaquer la cellule est un interlédiaire
		if (!havePlants()) {
			return false;
		}
		return plants.zombieInRange(z, sizeCase,x);
	}
	
	public int attack(Zombies z) 
	{
		int time=plants.attack(z);
		if(time>0) {
			destroyCaseTimer=new Timer(time);
			pumpkin=null;
		}
		return time;
	}
	
	public void cleanCell() {//On supprime les éléement de la cellule
		if(havePumpkin() && !pumpkin.isAlive()) {
			pumpkin=null;
			return;
		}
		if (plants!=null && !(plants.isAlive())) {
			plants=null;
		}
	}
	
	public int attackManyLine() {
		if(plants!=null) {

			return plants.attackManyLine();
		}
		return 0;
	}
	
	public boolean attackManyZombies() {
		if(plants!=null) {

			return plants.attackManyZombies();
		}
		return false;
	}
	
	public void drawCells(Graphics2D graphics,int x,int y,int height,ArrayList<Image> listiImages) {
		
		if(plants!=null) {//On dessine la plante
			plants.draw( graphics, x,y,height,listiImages);	
		}
		if(!destroyCaseTimer.enoughDelayWithoutReset()) {//La case détruite
			graphics.drawImage(listiImages.get(30),x,y,height,height,null);
		}
		
		fog.draw(graphics, x, y, height, listiImages);//Le brouillard s'il esxiste
		if(pumpkin!=null) {//Et la pumpkin
			pumpkin.draw(graphics, x, y, height, listiImages);
		}
	
	}
	
	public void removeFogFromPlant(Ground ground, int x, int y) {//Pour activer les effets de brouillard des plantes
		if(plants!=null) {

			plants.effectOnFog(ground, x, y);
		}
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if (plants!=null) {
			return plants.toString();
		}
		return "null + cell";
	}
	
	public boolean colision(Zombies z) {
		if(plants==null) {return false;}
		return plants.colision(z);
	}
	
	public int newSun() {
		return plants.newSun();
	}
	
	public boolean isIceCell() {//Propiété utiliser quand le zomboni passe
		return false;
	}
	
	public void setFog(boolean fog) {

		
		if(fog) {
			this.fog.activeFog();
		}
	}
	
	public boolean havePumpkin() {
		return pumpkin!=null;
	}
	public void putPumpkin(Pumpkin p) {
		if(destroyTimerEnoughDelay() && haveBase()) {

			pumpkin=p;
		}
	}
	
	public boolean haveBase() {//Différentes fonction pour connaitre l'état de la cellule
		return true;
	}
	 public boolean haveTomb() {
		 return false;
	 }
	public boolean isFog() {
		return fog.isActive();
	}
	
	public void removeFog(long time) {
		fog.removeFog(time);
	}
}
