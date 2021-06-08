package gameData.Plants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TorchWood extends Plants{
	private int y =-3;
	public TorchWood() {
		super(0, 300, 0, 0, 43);
	}
	
	@Override
	public String tag() {
		// TODO Auto-generated method stub
		return "TorchWood";
	}
	@Override
	public int attackManyLine() {
		// TODO Auto-generated method stub
		return 1;
	}
	@Override
	public boolean canAttack() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public void setY(int y) {
		if(this.y<0) {
			this.y = y;
		}
	}
	
	@Override
	public void addProjectiles(ArrayList<Projectile> projectiles, int i, int sizeCell) {
		duplicate(projectiles, i, sizeCell);
	}
	
	private void duplicate(ArrayList<Projectile> projectiles, int i, int sizeCase) {
		//Il duplique les projectile qui passe par lui
		Set<Projectile> temp=new HashSet<Projectile>();
		for (Projectile projectile : projectiles) {
			if(projectile.canBeOnFire()) {//On vérifie que le projectile peut etre dupliqué donc 2 fois plus de dégat
				Projectile pro=projectile.duplicate(i,sizeCase,y);
				if(pro!=null) {
					temp.add(pro);
				}
			}
		}
		projectiles.addAll(temp);
		
	}
	
}
