package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.Jalapeno;
import gameData.Plants.Plants;

public class CardJalaPeno extends Cards {

	public  CardJalaPeno(Coordinates coordinates, int height, int width) {
		super(coordinates, height, width, new Jalapeno(),34,150);
	}
	public  CardJalaPeno( int height, int width) {
		super( height, width, new Jalapeno(),34,150);
	}
	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new Jalapeno();
	}
}