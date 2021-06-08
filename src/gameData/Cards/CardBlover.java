package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.Blover;
import gameData.Plants.Plants;

public class CardBlover extends Cards {
	
	public  CardBlover(Coordinates coordinates, int height, int width) {
		super(coordinates, height, width, new Blover(),0,150);
	}
	public  CardBlover( int height, int width) {
		super( height, width, new Blover(),0,150);
	}
	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new Blover();
	}
}
