package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.Plants;
import gameData.Plants.SunFlower;

public class CardSunFlower extends Cards {

	public CardSunFlower(Coordinates coordinates, int height, int width) {
		super(coordinates, height, width, new SunFlower(),5,50);
	}
	
	public CardSunFlower( int height, int width) {
		super(height, width, new SunFlower(),5,50);
	}
	
	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new SunFlower();
	}
}
