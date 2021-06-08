package gameData.Zombies;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import gameData.Coordinates;
import gameData.ground.Lines;

public class BobsledTeam extends Zombies{
	
	public BobsledTeam(Coordinates c) {
		super(280, 4, 2000, c, 54);
	}
	
	public boolean isAlive() {	
		if(health>0) {
			return true;
		}
		setReadyToJump(true); //prépare le spawn des membres de l'équipe de bobsleigh
		return false;
	}
	
	public void spawnBobsleds(int sizeCell, Lines line,int nbColumns) {	//destruction du bobsleigh et sortie des membres de l'équipe de bobsleigh
		int xOrigin=coordinates.getX();
		for (int i = 0; i < 4; i++) {
			line.addZombie(new BobsledZombie(new Coordinates(xOrigin, coordinates.getY())));
			if (xOrigin+sizeCell<nbColumns*sizeCell) {
				xOrigin+=sizeCell;
			}
		}
		suicide();
	}
	
	@Override
	public String tag() {
		return "BobsledTeam";
	}
	
	@Override
	public void draw(Graphics2D graphics,int height,ArrayList<Image> imagesList) {
		graphics.drawImage(imagesList.get(image),coordinates.getX(),coordinates.getY(),height*4,height,null);	//élargissement l'image
		super.drawLife(graphics,coordinates.getX(),coordinates.getY(),height);
	}
	
	@Override
	public boolean isBobsledTeam() {
		return true;
	}
}
