package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.Garlic;
import gameData.Plants.Plants;

public class CardGarlic extends Cards {
	public  CardGarlic(Coordinates coordinates, int height, int width) {
		super(coordinates, height, width, new Garlic(),0,150);
	}
	public  CardGarlic( int height, int width) {
		super( height, width, new Garlic(),0,150);
	}
	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new Garlic();
	}
}
