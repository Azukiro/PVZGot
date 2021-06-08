package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.Plants;
import gameData.Plants.PotatoMine;

public class CardPotatoMine extends Cards{

	public  CardPotatoMine(Coordinates coordinates, int height, int width) {
		super(coordinates, height, width, new PotatoMine(),20,25);
	}
	public  CardPotatoMine( int height, int width) {
		super( height, width, new PotatoMine(),20,25);
	}
	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new PotatoMine();
	}
}
