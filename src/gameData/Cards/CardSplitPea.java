package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.Plants;
import gameData.Plants.SplitPea;

public class CardSplitPea extends Cards {

	public CardSplitPea(Coordinates coordinates, int height, int width) {
			super(coordinates, height, width, new SplitPea(),5,100);
		}

	public CardSplitPea( int height, int width) {
			super(height, width, new SplitPea(),5,100);
		}

	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new SplitPea();
	}

}
