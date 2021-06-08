package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.KernelPult;
import gameData.Plants.Plants;

public class CardKernelPult extends Cards{
	public  CardKernelPult(Coordinates coordinates, int height, int width) {
		super(coordinates, height, width, new KernelPult(),0,100);
	}
	public  CardKernelPult( int height, int width) {
		super( height, width, new KernelPult(),0,100);
	}
	@Override
	public Plants selectPlants() {
		// TODO Auto-generated method stub
		return new KernelPult();
	}
}
