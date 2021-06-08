package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.CherryBomb;
import gameData.Plants.Plants;

public class CardCherryBomb extends Cards {

	public  CardCherryBomb(Coordinates coordinates, int height, int width) {
		super(coordinates, height, width, new CherryBomb(),34,150);
	}
	public  CardCherryBomb( int height, int width) {
		super( height, width, new CherryBomb(),34,150);
	}
	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new CherryBomb();
	}
}
