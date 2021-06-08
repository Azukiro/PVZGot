package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.Lillypad;
import gameData.Plants.Plants;

public class CardLilyPad  extends Cards {

	public  CardLilyPad(Coordinates coordinates, int height, int width) {
		super(coordinates, height, width, new Lillypad(),0,25);
	}
	public  CardLilyPad( int height, int width) {
		super( height, width, new Lillypad(),0,25);
	}
	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new Lillypad();
	}
}