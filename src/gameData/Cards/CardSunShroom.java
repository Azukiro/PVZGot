package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.Plants;
import gameData.Plants.SunShroom;

public class CardSunShroom  extends CardsShroom {
	
	public  CardSunShroom(Coordinates coordinates, int height, int width,boolean isActive) {
		super(coordinates, height, width, new SunShroom(isActive),20,150,isActive);
	}
	public  CardSunShroom( int height, int width, boolean isActive) {
		super( height, width, new SunShroom(isActive),20,150,isActive);
	}
	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new SunShroom(isActive());
	}

}
