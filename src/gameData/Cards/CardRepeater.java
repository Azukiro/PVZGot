package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.Plants;
import gameData.Plants.Repeater;

public class CardRepeater extends Cards {
	
	public  CardRepeater(Coordinates coordinates, int height, int width) {
		super(coordinates, height, width, new Repeater(),5,200);
	}
	public  CardRepeater( int height, int width) {
		super( height, width, new Repeater(),5,200);
	}
	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new Repeater();
	}
}
