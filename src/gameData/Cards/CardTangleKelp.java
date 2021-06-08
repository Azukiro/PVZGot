package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.Plants;
import gameData.Plants.TangleKelp;

public class CardTangleKelp  extends Cards {

	public  CardTangleKelp(Coordinates coordinates, int height, int width) {
		super(coordinates, height, width, new TangleKelp(),20,25);
	}
	public  CardTangleKelp( int height, int width) {
		super( height, width, new TangleKelp(),20,25);
	}
	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new TangleKelp();
	}
}