
package gameData.Zombies;

import gameData.Coordinates;

public class ZamboniZombie extends Zombies{
	
	private int iceCells;
	private boolean teamSpawned;
	
	public ZamboniZombie(Coordinates c) {
		super(1160, 2, 2000, c, 52);
		damage=10000;
		iceCells=0;
		teamSpawned=false;
	}
	
	@Override
	public boolean removeWheel() {
		suicide();
		return true;
	}
	
	@Override
	public boolean placeIceTrail() {
		return true;
	}
	
	public void incrementIceCells() {	//nombre de glace posée
		iceCells+=1;
	}
	
	public boolean canSpawnBobsledTeam() {		//apparition du bobsleigh si le nombre de glace est supérieur  à ...
		if (iceCells>3 && teamSpawned==false) {
			teamSpawned=true;
			return true;
		}
		return false;
	}
}
