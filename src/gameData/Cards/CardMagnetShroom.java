package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.MagnetShroom;
import gameData.Plants.Plants;

public class CardMagnetShroom extends CardsShroom {
	public  CardMagnetShroom(Coordinates coordinates, int height, int width,boolean isActive) {
		super(coordinates, height, width, new MagnetShroom(isActive),0,150,isActive);
	}
	public  CardMagnetShroom( int height, int width, boolean isActive) {
		super( height, width, new MagnetShroom(isActive),0,150,isActive);
	}
	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new MagnetShroom(isActive());
	}

}
