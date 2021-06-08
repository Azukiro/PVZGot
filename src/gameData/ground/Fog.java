package gameData.ground;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;

import gameData.Timer;

public class Fog implements Serializable{
	private boolean active=false;
	private  Timer time= new Timer(0);//Timer ou il n'est plus actif
	private final int image=57;
	private  long timeFogDown=10000;
	
	public void activeFog() {
		active=true;
	}
	
	public void removeFog(long time) {
		if(active || this.time.getDelay()!=0) {//s'il est actif ou que le timer à un delais différents de 0 donc que le brouillard est désactivé

			timeFogDown=time;//On reset le timer avec le nouveau délais de désactivation
			this.time=new Timer(timeFogDown);
			active=false;
		}
		
	}
	public boolean isActive() {
		
		return active;
	}
	public void draw(Graphics2D graphics,int x,int y,int height,ArrayList<Image> listiImages) {
		if(time.enoughDelay() && time.getDelay()!=0) {
			
			active=true;
		}
		if(active) {
			graphics.drawImage(listiImages.get(image),x-height/3,y-height/3,height+height/2,height+height/2,null);
		}
		
	}
}
