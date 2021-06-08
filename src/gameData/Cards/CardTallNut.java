package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.Plants;
import gameData.Plants.TallNut;

public class CardTallNut extends Cards{
	
	public  CardTallNut(Coordinates coordinates, int height, int width) {
		super(coordinates, height, width, new TallNut(),20,125);
	}
	
	public  CardTallNut(int height, int width) {
		super(height, width, new TallNut(),20,125);
	}
	
	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new TallNut();
	}
}
