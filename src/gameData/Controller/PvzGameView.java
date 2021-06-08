package gameData.Controller;

import gameData.Button;
import gameData.Coordinates;
import gameData.Cards.Cards;
import gameData.Zombies.*;
import gameData.ground.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class PvzGameView implements GameView {
	private final float heigthScreen;
	private final float widthScreen;
	
	
	public PvzGameView(float heigthScreen, float widthScreen) {
		this.heigthScreen = heigthScreen;
		this.widthScreen = widthScreen;
	}
	//affichage des zombies
	public void drawZombies(Graphics2D graphics, Ground ground, int height, ArrayList<Zombies> listZ,
			ArrayList<Image> listiImages) {
		for (Zombies zombies : listZ) {
			zombies.draw(graphics, height, listiImages);
		}
	}
	//affichage de l'arrière plan
	@Override
	public void drawBackground(Graphics2D graphics, Ground ground, int heightGround, int widthGround, Image i,ArrayList<Image> listImages) {
		ground.drawBackground(graphics, heightGround, widthGround,listImages);

	}
	//affichage des plantes
	@Override
	public void drawPlants(Graphics2D graphics, Ground ground, ArrayList<Image> listiImages) {
		ground.drawPlants(graphics, listiImages);

	}
	//afficahge du plateau
	@Override
	public void draw(Graphics2D graphics, Ground ground, int heightGround, int widthGround,
			ArrayList<Image> listiImages,gameData.Level l1) {

		ground.draw(graphics, heightGround, widthGround, listiImages);

	}
	//affichage des plateaux de jeu (sélection)
	@Override
	public void drawLittleGround(Graphics2D graphics, ArrayList<Ground> grounds, int sizeCell,ArrayList<Image> listImages) {
		graphics.setColor(Color.white);
		graphics.fill(new Rectangle2D.Float(0,0,widthScreen,heigthScreen));
		Image image=null;
		try {
			image=ImageIO.read(new File("./Pictures/Environnement/Name.png"));
		} catch (IOException e) {
		}
		graphics.drawImage(image,sizeCell*4,0,sizeCell*5,(int)(sizeCell*1.5),null);	
		int x = (int)(sizeCell*0.3);
		int y = 0;
		int i=0;
		for (Ground ground : grounds) {
			if(i%3==0) {
				x=(int)(sizeCell*0.3);
				y+=sizeCell*2;
			}
			graphics.drawImage(listImages.get(ground.getBackground()),x,y,sizeCell*3,(int)(sizeCell*1.5),null);	
			x+=sizeCell*3+(int)sizeCell*1.5;
			i++;
			
		}
		
	}
	
	
	//affichage des cartes à sélectionner
	@Override
	public void drawSelectCards(Graphics2D graphics, Ground data, ArrayList<Cards> plantsPlay, int sizeCell,Cards[] arrayCard,int originY,ArrayList<Image> listImages) {
		int x = (int)(sizeCell*0.5);
		int y = sizeCell;
		graphics.setColor(Color.white);
		graphics.fill(new Rectangle2D.Float(0,0,widthScreen,heigthScreen));
		Font font = new Font("Serif", Font.PLAIN, 40);
		graphics.setFont(font);
		graphics.drawString("Choissiez vos cartes vous en avez le droit � 5", (int)(sizeCell*0.5), (300+sizeCell*2)-originY);// Avec plus de plantes modifier
		Image image=null;
		try {
			image=ImageIO.read(new File("./Pictures/Environnement/Name.png"));
		} catch (IOException e) {
			
		}
		graphics.drawImage(image,sizeCell*4,0-originY,(sizeCell*5),sizeCell*2,null);																				// le nombre en param�tres
		int i=-1;
		for (Cards card : plantsPlay) {
			i++;
			if(i%3==0) {
				y+=(sizeCell)* 1.5;
				x= (int)(sizeCell*0.5);
			}
			card.setCoordinates(new Coordinates(x, y-originY));
			card.drawClassic(graphics,listImages);
			x += (sizeCell) + sizeCell * 1.5;

		}
		
		for (Cards cards : arrayCard) {
			if(cards!=null) {cards.drawClose(graphics,listImages);}
		}
		
		drawStartZombies(listImages, graphics, data, sizeCell, originY);

		
		
	}
	//affichage de la victoire
	public void winGame(Graphics2D graphics,float width, float height)  {
		Image image=null;
		try {
			image=ImageIO.read(new File("./Pictures/EndGame/win.png"));
		} catch (IOException e) {
			
		}
		graphics.drawImage(image,0,0,(int)width,(int)height,null);
		
		
	}
	//affichage de la défaite
	public void looseGame(Graphics2D graphics,float width, float height) {
		Image image=null;
		try {
			image=ImageIO.read(new File("./Pictures/EndGame/loose.png"));
		} catch (IOException e) {
			
		}
		graphics.drawImage(image,0,0,(int)width,(int)height,null);
		
	}
	//affichage de l'environnement (hors plateau)
	public void drawEnvironnement(Graphics2D graphics, Ground ground,int height,int x,int width,int widthFooter,int heightFooter,ArrayList<Image> listImages) {
		Image image=null;
		try {
			image=ImageIO.read(new File("./Pictures/map.jpg"));
		} catch (IOException e) {
			
		}
		graphics.drawImage(image,x,0,width,heightFooter,null);
		graphics.drawImage(listImages.get(ground.getFooter()),0,height,widthFooter-width,heightFooter,null);
		ground.draw(graphics, height, width, listImages);
	}
	//affichage de la prévisualisation des zombies à combattre
	@Override
	public void drawStartZombies(ArrayList<Image> listImages, Graphics2D graphics,Ground ground,int sizeCell,int yOrigin) {
		Zombies[] listZombies = ground.getZombiesPlaying();
		for (Zombies zombies : listZombies) {
			graphics.drawImage(listImages.get(zombies.getImage()),zombies.getCoordinates().getX(),zombies.getCoordinates().getY()-yOrigin,sizeCell,sizeCell,null);
		}
		
	}
	//affichage de l'assombrissement lors de la pause
	@Override
	public void drawPause(Graphics2D graphics, ArrayList<Image> listImages, int xOrigin, int yOrigin, int xLength,int yLength) {
		graphics.drawImage(listImages.get(84),xOrigin,yOrigin,xLength,yLength,null);
		
	}
	//affichage d'un bouton du menu pause
	@Override
	public void drawButton(Graphics2D graphics, ArrayList<Image> listImages,Button button) {
		Rectangle2D area = button.getArea();
		String text = button.getText();
		
		
		graphics.drawImage(listImages.get(85),(int) area.getMinX(),(int) area.getMinY(),(int) area.getWidth(),(int) area.getHeight(),null);
		Font font = new Font("Serif", Font.PLAIN, 30);
		graphics.setFont(font);
		int textWidth =graphics.getFontMetrics(font).stringWidth(text);
		int textHeight =graphics.getFontMetrics(font).getHeight();
		graphics.drawString(text, (int)area.getCenterX()-((int)textWidth/2), (int)area.getMaxY()-(textHeight/2));
	}

}
