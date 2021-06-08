package gameData;

import java.io.Serializable;
import java.util.Objects;

public class Coordinates implements Serializable{
	private int x;
	private int y;
	
	//Classe des coordonnÃ©es de la fenÃªtre
	
	public Coordinates(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public void plusCoordonates(Coordinates c) {
		x+=c.x;
		y+=c.y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public Coordinates multiply(int nb) {//Multiplé les coordoné par le meme chfiffre
		Coordinates coordinates=new Coordinates(0,0);
		coordinates.x=nb*x;
		coordinates.y=nb*y;
		return coordinates;
	}
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Coordinates)) {
			return false;
		}
		Coordinates c= (Coordinates) obj;
		return c.x==this.x && c.y==this.y;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x,y);
	}
	
	@Override
	public String toString() {
		return "("+x+","+y+")";
	}
	
	public int substractSpeedToX(int speed) {
		return x=x-speed;
	}
	
	public int addSpeedToX(int speed) {
		return x=x+speed;
	}
	
}
