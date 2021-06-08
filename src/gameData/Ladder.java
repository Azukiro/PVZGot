package gameData;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;

public class Ladder implements Serializable{
	private int image;

	public Ladder() {
		this.image = 65;
	}
	//Pour afficher plus facilement l'échelle du jeu
	public void draw(Graphics2D graphics,int x,int y,int height,ArrayList<Image> imagesList) {
		graphics.drawImage(imagesList.get(image),x+(int)(0.7*height),y,(int)(0.3*height),height,null);
		
	}
	
}
