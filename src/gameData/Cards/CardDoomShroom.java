package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.DoomShroom;
import gameData.Plants.Plants;

public class CardDoomShroom extends CardsShroom {
	public  CardDoomShroom(Coordinates coordinates, int height, int width,boolean isActive) {
		super(coordinates, height, width, new DoomShroom(isActive),20,150,isActive);
	}
	public  CardDoomShroom( int height, int width, boolean isActive) {
		super(height, width, new DoomShroom(isActive),0,150,isActive);
	}
	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new DoomShroom(isActive());
	}
}