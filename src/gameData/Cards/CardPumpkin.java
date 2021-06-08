package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.Plants;
import gameData.Plants.Pumpkin;

public class CardPumpkin extends Cards{
	
	public  CardPumpkin(Coordinates coordinates, int height, int width) {
		super(coordinates, height, width, new Pumpkin(),0,150);
	}
	public  CardPumpkin( int height, int width) {
		super( height, width, new Pumpkin(),0,150);
	}
	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new Pumpkin();
	}
}
