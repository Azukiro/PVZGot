package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.IceShroom;
import gameData.Plants.Plants;

public class CardIceShroom  extends CardsShroom {

	public  CardIceShroom(Coordinates coordinates, int height, int width,boolean isActive) {
		super(coordinates, height, width, new IceShroom(isActive),20,150,isActive);
	}
	public  CardIceShroom( int height, int width, boolean isActive) {
		super( height, width, new IceShroom(isActive),20,150,isActive);
	}
	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new IceShroom(isActive());
	}
}