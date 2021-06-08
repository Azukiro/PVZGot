package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.Chomper;
import gameData.Plants.Plants;

public class CardChomper extends Cards {

	public  CardChomper(Coordinates coordinates, int height, int width) {
		super(coordinates, height, width, new Chomper(),5,150);
	}
	public  CardChomper( int height, int width) {
		super( height, width, new Chomper(),5,150);
	}
	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new Chomper();
	}
}
