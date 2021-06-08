package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.HypnoShroom;
import gameData.Plants.Plants;

public class CardHypnoShroom extends CardsShroom {

	public  CardHypnoShroom(Coordinates coordinates, int height, int width,boolean isActive) {
		super(coordinates, height, width, new HypnoShroom(isActive),20,150,isActive);
	}
	public  CardHypnoShroom( int height, int width, boolean isActive) {
		super( height, width, new HypnoShroom(isActive),20,150,isActive);
	}
	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new HypnoShroom(isActive());
	}
}