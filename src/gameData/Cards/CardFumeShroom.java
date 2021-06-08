package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.FumeShroom;
import gameData.Plants.Plants;

public class CardFumeShroom extends CardsShroom {
	
	public  CardFumeShroom(Coordinates coordinates, int height, int width,boolean isActive) {
		super(coordinates, height, width, new FumeShroom(isActive),20,150,isActive);
	}
	public  CardFumeShroom( int height, int width, boolean isActive) {
		super( height, width, new FumeShroom(isActive),20,150,isActive);
	}
	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new FumeShroom(isActive());
	}


}
