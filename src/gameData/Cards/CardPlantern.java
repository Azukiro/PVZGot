package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.Plantern;
import gameData.Plants.Plants;

public class CardPlantern extends Cards {
	
	public  CardPlantern(Coordinates coordinates, int height, int width) {
		super(coordinates, height, width, new Plantern(),0,150);
	}
	public  CardPlantern( int height, int width) {
		super( height, width, new Plantern(),0,150);
	}
	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new Plantern();
	}
}
