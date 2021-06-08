package gameData.Plants;

import gameData.ground.Ground;

public class Blover extends Plants {
	

	public Blover() {
		super(0, 300, 2000, 2, 77);
	}
	
	@Override
	public void effectOnFog(Ground ground,int x, int y) {//Quand il est acitf il supprime tout le brouillard pendant 10 sec
		for (int i = 0; i < ground.getNbLines(); i++) {
			for (int j = 0; j < ground.getNbColumns(); j++) {
				ground.removeFog(i, j,10000);
				
			}
			
		}
		suicide();//Et is se suicide
	}
	
	@Override
	public String tag() {
		return "Blover";
	}
}
