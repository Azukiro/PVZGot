package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.GraveBuster;
import gameData.Plants.Plants;

public class CardGraveBuster extends Cards {

	public  CardGraveBuster(Coordinates coordinates, int height, int width) {
		super(coordinates, height, width, new GraveBuster(),5,75);
	}
	public  CardGraveBuster( int height, int width) {
		super( height, width, new GraveBuster(),5,75);
	}
	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new GraveBuster();
	}
}
