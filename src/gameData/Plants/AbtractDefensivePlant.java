package gameData.Plants;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import gameData.Ladder;
import gameData.Zombies.Zombies;

public abstract class AbtractDefensivePlant extends Plants implements DefensivePlant {
	private  Ladder ladder=null;//Seul les plantes défensive peuvent avoir une échelle sur elles
	
	public AbtractDefensivePlant(int health,int heigth,int image) {
		super(0, health,0, 0,heigth,image);
	}
	public AbtractDefensivePlant(int health,int image) {
		super(0, health,0, 0,image);
	}
	
	@Override
	public boolean putLadder() {//On ajoute l'échelle sur les plante défensive
		if(ladder==null) {
			ladder=new Ladder();
			return true;
		}
		return false;
	}
	
	@Override
	public boolean colision(Zombies z) {//Si l'échelle est mise les zombies peuvent passer au travers de la plante
		if(ladder!=null) {
			return false;
		}
		return true;
	}
	

	@Override
	public void draw(Graphics2D graphics, int x, int y, int height, ArrayList<Image> imagesList) {
		super.draw(graphics, x, y, height, imagesList);
		if(ladder!=null) {ladder.draw(graphics, x, y, height, imagesList);}
	}
}
