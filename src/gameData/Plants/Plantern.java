package gameData.Plants;

import gameData.ground.Ground;

public class Plantern extends Plants{
	
	public Plantern() {
		super(0, 300, 2000, 2, 78);
	}
	
	@Override
	public void effectOnFog(Ground ground,int x, int y) {
		for(int i=0; i<2;i++) {
			for(int j=0; j<3;j++) {//Enleve le brouillard sur une zone de 3*5
				ground.removeFog(y, x+j,1000);
				ground.removeFog(y, x-j,1000);
				ground.removeFog(y-i, x,1000);
				ground.removeFog(y+i, x,1000);
				ground.removeFog(y+i, x-j,1000);
				ground.removeFog(y-i, x+j,1000);
				ground.removeFog(y+i, x+j,1000);
				ground.removeFog(y-i, x-j,1000);
			}
		}
		ground.removeFog(y, x,1000);
	}
	
	@Override
	public String tag() {
		return "Plantern";
	}
	
}
