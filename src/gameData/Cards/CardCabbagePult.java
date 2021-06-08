package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.CabbagePult;
import gameData.Plants.Plants;

public class CardCabbagePult extends Cards {
	public  CardCabbagePult(Coordinates coordinates, int height, int width) {
		super(coordinates, height, width, new CabbagePult(),0,150);
	}
	public  CardCabbagePult( int height, int width) {
		super( height, width, new CabbagePult(),0,150);
	}
	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new CabbagePult();
	}
}
