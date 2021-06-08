package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.Plants;
import gameData.Plants.SnowPea;

public class CardSnowPea extends Cards{
	
	public CardSnowPea(Coordinates coordinates, int height, int width) {
		super(coordinates, height, width, new SnowPea(),5,150);
	}
	
	public CardSnowPea( int height, int width) {
		super(height, width, new SnowPea(),5,150);
	}
	
	@Override
	public Plants selectPlants() {
		return new SnowPea();
	}
}
