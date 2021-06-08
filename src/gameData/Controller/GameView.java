package gameData.Controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import fr.umlv.zen5.ApplicationContext;
import gameData.Button;
import gameData.Level;
import gameData.Cards.Cards;
import gameData.Zombies.*;
import gameData.ground.*;

public interface GameView {
	
	public default void drawZombies(ApplicationContext context, Ground ground,int height,ArrayList<Zombies> listZ,ArrayList<Image> listiImages) {
		context.renderFrame(graphics -> drawZombies(graphics, ground, height,listZ,listiImages));
		
	}
	
	void drawZombies(Graphics2D graphics, Ground ground,int height,ArrayList<Zombies> listZ,ArrayList<Image> listiImages );
	
	public default void draw(ApplicationContext context, Ground data,int heightGround, int widthGround,ArrayList<Image> listiImages,Level l1) {
		context.renderFrame(graphics -> draw(graphics, data, heightGround,  widthGround,listiImages,l1));
		
	}

	 void draw(Graphics2D graphics, Ground data,int heightGround, int widthGround,ArrayList<Image> listiImages,Level l);
	 

	 
	void drawLittleGround(Graphics2D graphics, ArrayList<Ground> grounds,int sizeCell,ArrayList<Image> listImages);
	
	public default void drawLittleGround(ApplicationContext context, ArrayList<Ground> grounds,int sizeCell,ArrayList<Image> listImages) {
		context.renderFrame(graphics -> drawLittleGround(graphics, grounds, sizeCell,listImages));
		
	}
	 
	 
		
	void drawBackground(Graphics2D graphics, Ground data,int heightGround, int widthGround,Image i,ArrayList<Image> listImages);
	
	public default void drawBackground(ApplicationContext context,Ground data, int heightGround, int widthGround,Image i,ArrayList<Image> listiImages) {
		context.renderFrame(graphics -> drawBackground(graphics, data,heightGround,widthGround,i,listiImages));
		
	}
	
	void drawPlants(Graphics2D graphics, Ground data,ArrayList<Image> listiImages);
	
	public default void drawPlants(ApplicationContext context,Ground data,ArrayList<Image> listiImages) {
		context.renderFrame(graphics -> drawPlants(graphics, data,listiImages));
		
	}
	
	void drawSelectCards(Graphics2D graphics, Ground data,ArrayList<Cards> plantsPlay,int sizeCell,Cards[] arrayCard,int y,ArrayList<Image> listImages);
	
	public default void drawSelectCards(ApplicationContext context,Ground data,ArrayList<Cards> plantsPlay, int sizeCell,Cards[] arrayCard,int y,ArrayList<Image> listImages) {
		context.renderFrame(graphics -> drawSelectCards(graphics, data, plantsPlay,sizeCell,arrayCard,y,listImages));
		
	}
	
	
	void winGame(Graphics2D graphics,float width,float height);
	
	public default void winGame(ApplicationContext context,float width,float height) {
		context.renderFrame(graphics -> winGame(graphics, width, height));
		
	}
	
	void looseGame(Graphics2D graphics,float width,float height);
	
	public default void looseGame(ApplicationContext context,float width,float height) {
		context.renderFrame(graphics -> looseGame(graphics, width, height));
		
	}
	
	void drawPause(Graphics2D graphics, ArrayList<Image> listImages,int xOrigin,int yOrigin,int xLength,int yLength);
	
	public default void drawPause(ApplicationContext context, ArrayList<Image> listImages,int xOrigin,int yOrigin,int xLength,int yLength) {
		context.renderFrame(graphics -> drawPause(graphics, listImages, xOrigin, yOrigin, xLength, yLength));
	}
	
	void drawButton(Graphics2D graphics, ArrayList<Image> listImages,Button button);
	
	public default void drawButton(ApplicationContext context, ArrayList<Image> listImages,Button button) {
		context.renderFrame(graphics -> drawButton(graphics, listImages,button));
	}
	
	void drawEnvironnement(Graphics2D graphics, Ground ground,int height,int x,int width,int widthFooter,int heightFooter,ArrayList<Image> listImages);
	
	public default void drawEnvironnement(ApplicationContext context, Ground ground,int height,int x,int width,int widthFooter,int heightFooter,ArrayList<Image> listImages) {
		context.renderFrame(graphics -> drawEnvironnement(graphics,ground, height, x, width, widthFooter, heightFooter,listImages));
		
	}
	
	void drawStartZombies(ArrayList<Image> listImages, Graphics2D graphics,Ground ground,int sizeCell,int yOrigin);
	
	public default void drawStartZombies(ArrayList<Image> listImages,ApplicationContext context,Ground ground,int sizeCell,int yOrigin) {
		context.renderFrame(graphics -> drawStartZombies(listImages,graphics,ground,sizeCell,yOrigin));	
	}
	
}
