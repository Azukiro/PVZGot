package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.Plants;
import gameData.Plants.Squash;

public class CardSquash  extends Cards {

	public  CardSquash(Coordinates coordinates, int height, int width) {
		super(coordinates, height, width, new Squash(),20,50);
	}
	public  CardSquash( int height, int width) {
		super( height, width, new Squash(),20,50);
	}
	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new Squash();
	}
}