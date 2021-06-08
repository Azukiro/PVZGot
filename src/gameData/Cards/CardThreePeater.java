package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.Plants;
import gameData.Plants.ThreePeater;

public class CardThreePeater extends Cards {

	public CardThreePeater(Coordinates coordinates, int height, int width) {
		super(coordinates, height, width, new ThreePeater(),5,300);
	}
	
	public CardThreePeater( int height, int width) {
		super(height, width, new ThreePeater(),5,300);
	}
	
	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new ThreePeater();
	}
}
