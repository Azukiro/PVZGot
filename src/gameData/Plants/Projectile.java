package gameData.Plants;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;


import gameData.Coordinates;
import gameData.LivingBeing;
import gameData.Zombies.Zombies;

public class Projectile implements Serializable {
	
	protected int image;
	private  Coordinates coordinates;
	private  int speed=17;
	private boolean haveBeenDuplicate=false;
	private int up=0;
	private  boolean reverse=false;
	
	public Projectile(int x,boolean reverse) {
		image=74;
		this.reverse=reverse;
		if(reverse==true) {
			speed*=-1;
		}
		
		this.coordinates=new Coordinates(x, -3);
	}
	
	public Projectile(int x,int up) {
		this(x);
		this.up=up;
	}
	
	public Projectile(int x) {
		this(x, false);
	}
			
	public void setImage(int image) {
		this.image = image;
	}
	
	public boolean breaker(int x,int sizeCell) {//Pour empécher les projectiles d'aller plus loin qu'une limite demandé
		return coordinates.getX()+sizeCell>=x*sizeCell && coordinates.getX()+sizeCell<=(x*sizeCell+(sizeCell*0.2))  ;
	}
	
	
	public void move() {
		coordinates.addSpeedToX(speed);
	}
	
	public void attack(LivingBeing l) {
		l.damage(20);
	}
	
	public boolean haveZombieInRange(LivingBeing l,int sizeCell) {
		Zombies  zombies=(Zombies) l;
		if (zombies!=null && !reverse) {
			if(coordinates.getY()==-3) {//Si les coordoné horizontal ne sont pas encore défini on ne les prends pas en compte
				
				return coordinates.getX()>=zombies.getCoordinates().getX() && coordinates.getX()<=zombies.getCoordinates().getX()+sizeCell && goodHeight(zombies) ;
			}else{
				return coordinates.getX()>=zombies.getCoordinates().getX() && coordinates.getX()<=zombies.getCoordinates().getX()+sizeCell && goodHeight(zombies) && coordinates.getY()==zombies.getCoordinates().getY();
			}
		}
		
		if (zombies!=null && reverse) {//Si le projectiles va ) l'envers on change sont mode de détection
			if(coordinates.getY()==-3) {
				return coordinates.getX()<=zombies.getCoordinates().getX() && coordinates.getX()+sizeCell>=zombies.getCoordinates().getX() && goodHeight(zombies) ;
			}else{
				return coordinates.getX()<=zombies.getCoordinates().getX() && coordinates.getX()+sizeCell>=zombies.getCoordinates().getX() && goodHeight(zombies) && coordinates.getY()==zombies.getCoordinates().getY();
			}
		}
		return false;
	}
	protected boolean goodHeight(Zombies z) {//On vérifie que le projectile attaque les zombie se trouvant a sa hauteur
		return z.getHeight()==0;
	}
	public int getY() {
		return coordinates.getY();
	}
	
	public boolean canBeOnFire() {
		return true;
	}
	public Projectile duplicate(int x, int sizeCase, int y) {//Pour dupliquer les projectiles avec la torchwood
		if(x*sizeCase<=coordinates.getX() && (x+1)*sizeCase>=coordinates.getX() && !haveBeenDuplicate) {//Si on est dans la range de la torchwood il renvoit un porjectiles luis correspondant 
			Projectile projectile= new Projectile(this.coordinates.getX()+10);
			projectile.coordinates.setY(coordinates.getY());
			projectile.haveBeenDuplicate=true;
			haveBeenDuplicate=true;
			return projectile;
		}
		return null;
	}
	public void draw(Graphics2D graphics, int height,int y,ArrayList<Image> listImages) {
		if(coordinates.getY()==-3) {
			coordinates.setY((y+up)*height);
		}
		graphics.drawImage(listImages.get(image),coordinates.getX(),coordinates.getY(),height,height,null);
		
	}
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Projectile)){
			return false;
		}
		
		Projectile projectile = (Projectile) obj;
		return speed==projectile.speed && coordinates.equals(projectile.coordinates);
				
	}
	
	public boolean wrongLine(int y) {//Pour vérifier si le projectiles et sur la bonne ligne ou pas et apres le décaler dans le ground
		if((coordinates.getY()<0)) {
			return false;
		}
		return y!=coordinates.getY();
	}
	
	public int rightLine(int sizeCell) {
		return coordinates.getY()/sizeCell;
	}
	
	@Override
	public String toString() {
		return coordinates.toString()+up+ "Have been duplicate :"+haveBeenDuplicate;
	}
	
}
