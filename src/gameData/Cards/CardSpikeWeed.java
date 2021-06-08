package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.Plants;
import gameData.Plants.SpikeWeed;

public class CardSpikeWeed extends Cards {

	public  CardSpikeWeed(Coordinates coordinates, int height, int width) {
		super(coordinates, height, width, new SpikeWeed(),5,150);
	}
	public  CardSpikeWeed( int height, int width) {
		super( height, width, new SpikeWeed(),5,150);
	}
	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new SpikeWeed();
	}
}