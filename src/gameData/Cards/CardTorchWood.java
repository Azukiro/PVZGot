package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.Plants;
import gameData.Plants.TorchWood;

public class CardTorchWood extends Cards {

	public  CardTorchWood(Coordinates coordinates, int height, int width) {
		super(coordinates, height, width, new TorchWood(),20,150);
	}
	public  CardTorchWood( int height, int width) {
		super( height, width, new TorchWood(),0,150);
	}
	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new TorchWood();
	}
}