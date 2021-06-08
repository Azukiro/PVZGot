package gameData.Cards;

import gameData.Coordinates;
import gameData.Plants.Plants;

public class CardShovel extends Cards {

		public CardShovel(Coordinates coordinates, int height, int width) {
			super(coordinates, height, width, new Plants(0,0,0,0,0),0,0);
		}
		
		public CardShovel( int height, int width) {
			super(height, width,  new Plants(0,0,0,0,0),0,0);
		}
		
		@Override
		public Plants selectPlants() {//On renvoit une plante null pour après supprimer les plantes existante sur le plateau.
			// TODO Auto-generated method stub
			return null;
		}
}


