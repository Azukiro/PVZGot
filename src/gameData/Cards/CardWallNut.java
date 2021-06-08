package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.Plantern;
import gameData.Plants.Plants;
import gameData.Plants.WallNut;

public class CardWallNut extends Cards{
	
	public  CardWallNut(Coordinates coordinates, int height, int width) {
		super(coordinates, height, width, new WallNut(),20,50);
	}
	
	public  CardWallNut(int height, int width) {
		super(height, width, new WallNut(),20,50);
	}
	
	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new WallNut();
	}
}
