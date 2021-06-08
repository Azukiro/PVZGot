package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.Plants;
import gameData.Plants.SeaShroom;

public class CardSeaShroom extends Cards {

	public  CardSeaShroom(Coordinates coordinates, int height, int width) {
		super(coordinates, height, width, new SeaShroom(),5,0);
	}
	public  CardSeaShroom( int height, int width) {
		super( height, width, new SeaShroom(),5,0);
	}
	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new SeaShroom();
	}
}
