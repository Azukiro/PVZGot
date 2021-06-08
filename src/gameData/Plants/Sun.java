package gameData.Plants;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;

import gameData.Bot;
import gameData.Coordinates;

public class Sun implements Serializable{
	private final int cost;
	private  Coordinates coordinates;
	private int image=14;
	private final boolean originPlant;//Permet de savoir s'il doit bouger ou non
	private final int speed=1;
	private int offset=3;//Pour faire zizguer les soleil
	
	public Sun(Coordinates coordinates,boolean originPlant,int cost) {
		this.coordinates=coordinates;
		this.originPlant=originPlant;
		this.cost=cost;
		
	}
	public Sun(Coordinates coordinates,boolean originPlant) {
		this(coordinates, originPlant, 25);
		
	}
	
	public void moveSun(int maxRange) {
		if(originPlant || maxRange<coordinates.getY()+speed) {//Eviter qu'il depasse la limite de la carte
			return;
		}
		if(Bot.isActive()) {
			for(int i =0;i<Bot.getSpeed()-1;i++) {
				coordinates=new Coordinates(coordinates.getX()+offset, coordinates.getY()+speed);
				offset=-(offset);
			}
		}
		coordinates=new Coordinates(coordinates.getX()+offset, coordinates.getY()+speed);
		offset=-(offset);
	}
	public boolean clickInSun(int i, int j,int height) {
		
		return coordinates.getX()<=i && coordinates.getX()+height>=i && coordinates.getY()<=j && coordinates.getY()+height>=j  ;
	}
	
	
	public void draw(Graphics2D graphics,int height,ArrayList<Image> listiImages) {
		int offset=height;
		height=(int) (height*((double)(cost)/25.0));
		offset-=height;
		offset/=2;//On met sa taille en fonction de la valeur de son cout
		graphics.drawImage(listiImages.get(image),coordinates.getX()+offset,coordinates.getY()+offset,height,height,null);
	}
	
	public int getCost() {
		return cost;
	}
	
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Sun)){
			return false;
		}
		
		Sun su = (Sun) obj;
		return cost==su.cost && coordinates.equals(su.coordinates);
				
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return coordinates.toString()+"  "+originPlant+"  "+cost;
	}
}
