package gameData;

import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import fr.umlv.zen5.ApplicationContext;
import gameData.Controller.GameView;

public class Button {
	
	//bouton pour le menu pause
	
	private final Rectangle2D area;
	private final String text;
	
	public Button(Rectangle2D area, String text) {
		super();
		this.area=area;
		this.text = text;
	}
	
	public String clicked(int x, int y){	//si clic dans la zone, renvoie le texte contenu par le bouton
		if (area.contains((double) x, (double) y)) {
			return text;
		}
		return null;
	}
	
	public void draw(GameView view,ApplicationContext context, ArrayList<Image> listImages) {
		view.drawButton(context, listImages,this);
	}
	
	public Rectangle2D getArea() {
		return area;
	}
	
	public String getText() {
		return text;
	}
	
}
