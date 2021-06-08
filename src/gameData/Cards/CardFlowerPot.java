package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.FlowerPot;
import gameData.Plants.Plants;

public class CardFlowerPot extends Cards {
	
	public  CardFlowerPot(Coordinates coordinates, int height, int width) {
		super(coordinates, height, width, new FlowerPot(),5,75);
	}
	public  CardFlowerPot( int height, int width) {
		super( height, width, new FlowerPot(),5,75);
	}
	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new FlowerPot();
	}
}
