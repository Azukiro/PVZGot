package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.Plants;
import gameData.Plants.ScaredyShroom;

public class CardScaredyShroom extends CardsShroom {
	
	public  CardScaredyShroom(Coordinates coordinates, int height, int width,boolean isActive) {
		super(coordinates, height, width, new ScaredyShroom(isActive),20,150,isActive);
	}
	public  CardScaredyShroom( int height, int width, boolean isActive) {
		super( height, width, new ScaredyShroom(isActive),20,150,isActive);
	}
	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new ScaredyShroom(isActive());
	}


}
