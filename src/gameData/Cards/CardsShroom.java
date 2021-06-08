package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.Plants;

public class CardsShroom extends Cards {
	//On ajoute la propiété isActive pour pouvoir faire dormir les champignons dans les maps de jours
	private final boolean isActive;
	public CardsShroom(Coordinates coordinates, int height, int width, Plants plants, int refill, int cost,boolean isActive) {
		super(coordinates, height, width, plants, refill, cost);
		this.isActive=isActive;
	}
	
	public  CardsShroom( int height, int width,Plants plants,int refill, int cost, boolean isActive) {
		super( height, width, plants,refill,cost);
		this.isActive=isActive;
	}
	
	protected boolean isActive() {
		return isActive;
	}

}
