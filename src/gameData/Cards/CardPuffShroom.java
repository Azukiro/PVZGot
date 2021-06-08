package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.Plants;
import gameData.Plants.PuffShroom;

public class CardPuffShroom  extends CardsShroom {
	
	public  CardPuffShroom(Coordinates coordinates, int height, int width,boolean isActive) {
		super(coordinates, height, width, new PuffShroom(isActive),20,150,isActive);
	}
	public  CardPuffShroom( int height, int width, boolean isActive) {
		super( height, width, new PuffShroom(isActive),20,150,isActive);
	}
	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new PuffShroom(isActive());
	}

}
