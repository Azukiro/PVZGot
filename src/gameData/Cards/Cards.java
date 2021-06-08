package gameData.Cards;
import gameData.Plants.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import javax.imageio.ImageIO;

import gameData.Bot;
import gameData.Coordinates;
import gameData.Timer;

public class Cards implements Serializable{
	
	private Coordinates coordinates;
	private int height;
	private int width;
	private Plants plants;
	private final int cost;
	private final Timer refill;
	private int closeImage;
	private int cardImage;
	private boolean selected=false;
	private boolean delay=false;//Permet de si la carte est utilisable avec le mode debug elle le reste quand on repasse  en normal
	
	public Cards(Coordinates coordinates, int height, int width, Plants plants, int refill, int cost) {
		this.coordinates = Objects.requireNonNull(coordinates);
		this.height = height;
		this.width = width;
		this.plants = Objects.requireNonNull(plants);
		this.cost = cost;
		if(refill<0) {
			throw new IllegalArgumentException("Le timer ne peut pas avoir un délais négatif");
		}
		this.refill = new Timer(refill * 1000);
	}
	
	public Cards( int height, int width, Plants plants, int refill, int cost) {
		this.coordinates = null;
		this.height = height;
		this.width = width;
		this.plants = plants;
		this.cost = cost;
		this.refill = new Timer(refill*1000);
	}
	
	public void setCoordinates(Coordinates coordinates) {
		refill.reset();
		this.coordinates = coordinates;
	}
	
	public boolean canUseCards() {
		boolean b=  refill.enoughDelayWithoutReset()  || delay;
		delay=false;
		return b;
	}
	
	public boolean delayHasRefill() {
		return refill.enoughDelay();
	}
	public boolean clickInCards(int i, int j) {
		
		return coordinates.getX()<=i && coordinates.getX()+width>=i && coordinates.getY()<=j && coordinates.getY()+height>=j  ;
	}
	
	public Plants selectPlants() {//Quand le joueur sélectionne la carte et pose la plante on lui renvoie une nouvelle plantes à chaque fois
		return plants;
	}
	
	public int enoughCost(int costGround) {
		if(costGround>=cost) {
			return cost;
		}
		return -1;
	}
	public boolean clickOnSetCard(int x) {
		return x>=coordinates.getX();
	}
	public void draw(Graphics2D graphics,ArrayList<Image> listImages) {
		if (refill.enoughDelayWithoutReset() || delay) {
			drawClassic(graphics,listImages);
			delay=true;
			
		} else {
			
			double calc=(double)((double)refill.chronomill()*Bot.getSpeed()/(double)refill.getDelay());//On obtient le % de progression du timer
			//Et en fonction de se pourcentage on dessine un rectangle blanc sur la carte
			drawClose( graphics,listImages);
			drawCoins(graphics);
			graphics.setColor(new Color(1f, 1f, 1f, 0.65f));
			graphics.fill(new Rectangle2D.Float(coordinates.getX(),coordinates.getY()+(int)(height*calc),width,height-(int)(height*calc)));
			
			
		}
	}
	
	public boolean getSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public void drawCoins(Graphics2D graphics) {//Fonction pour dessiner les coins des cartes
		if(!selected) {//Si la carte est sélectionné les coins deviennent orange sinon il garde leur couleur d'origine
			graphics.setColor(new Color(80, 85, 81));
		}else {
			graphics.setColor(Color.ORANGE);
		}
			BasicStroke lineBasicStroke=new BasicStroke(5.5f);
			graphics.setStroke(lineBasicStroke);
			//Haut Gauche
			graphics.draw(new Line2D.Float(coordinates.getX(),coordinates.getY(),coordinates.getX()+ (int)(width*0.2),coordinates.getY()));
			graphics.draw(new Line2D.Float(coordinates.getX(),coordinates.getY(),coordinates.getX(),coordinates.getY()+ (int)(height*0.2)));
			
			//Haut droit
			graphics.draw(new Line2D.Float(coordinates.getX()+width,coordinates.getY(),coordinates.getX()+ (int)(width*0.8),coordinates.getY()));
			graphics.draw(new Line2D.Float(coordinates.getX()+width,coordinates.getY(),coordinates.getX()+width,coordinates.getY()+ (int)(height*0.2)));
		
			//Bas Droit
			graphics.draw(new Line2D.Float(coordinates.getX(),coordinates.getY()+height,coordinates.getX()+ (int)(width*0.2),coordinates.getY()+height));
			graphics.draw(new Line2D.Float(coordinates.getX(),coordinates.getY()+height,coordinates.getX(),coordinates.getY()+ (int)(height*0.8)));
			
			//Bas Gauche
			graphics.draw(new Line2D.Float(coordinates.getX()+width,coordinates.getY()+height,coordinates.getX()+ (int)(width*0.8),coordinates.getY()+height));
			graphics.draw(new Line2D.Float(coordinates.getX()+width,coordinates.getY()+height,coordinates.getX()+width,coordinates.getY()+ (int)(height*0.8)));
		graphics.setColor(new Color(80, 85, 81));
	}
	
	public void drawClassic(Graphics2D graphics,ArrayList<Image> listImages) {
		graphics.drawImage(listImages.get(cardImage),coordinates.getX(),coordinates.getY(),width,height,null);
		drawCoins(graphics);
		
	}

	public void drawClose(Graphics2D graphics,ArrayList<Image> listImages) {
		graphics.drawImage(listImages.get(closeImage),coordinates.getX(),coordinates.getY(),width,height,null);
	}
	
	@Override
	public String toString() {
		return coordinates+" plants :"+plants;
	}
	
	public void setCardImage(int cardImage) {
		this.cardImage = cardImage;
	}
	public void setCloseImage(int closeImage) {
		this.closeImage = closeImage;
	}
}

