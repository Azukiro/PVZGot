package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.CoffeeBean;
import gameData.Plants.Plants;

public class CardCoffeeBean extends Cards {
	public  CardCoffeeBean(Coordinates coordinates, int height, int width) {
		super(coordinates, height, width, new CoffeeBean(),34,75);
	}
	public  CardCoffeeBean( int height, int width) {
		super( height, width, new CoffeeBean(),0,75);
	}
	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new CoffeeBean();
	}
}
