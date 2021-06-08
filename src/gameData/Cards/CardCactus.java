package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.Cactus;
import gameData.Plants.Plants;

public class CardCactus extends Cards{
	

	public  CardCactus(Coordinates coordinates, int height, int width) {
		super(coordinates, height, width, new Cactus(),0,150);
	}
	public  CardCactus( int height, int width) {
		super( height, width, new Cactus(),0,150);
	}
	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new Cactus();
	}
}
