package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.*;

public class CardPeashooter extends Cards {

	public CardPeashooter(Coordinates coordinates, int height, int width) {
		super(coordinates, height, width, new Peashooter(),5,100);
	}
	
	public CardPeashooter( int height, int width) {
		super(height, width, new Peashooter(),5,100);
	}
	
	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new Peashooter();
	}
}
