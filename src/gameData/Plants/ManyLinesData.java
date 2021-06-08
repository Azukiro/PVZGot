package gameData.Plants;

import gameData.ground.Cells;

public class ManyLinesData implements LinesToGroundData {//Permet de transferer une cellule a une autre ligne
	private final Cells cells;
	private final int x;
	private final int range;
	
	public ManyLinesData(Cells cells, int x, int range) {
		this.cells = cells;
		this.x = x;
		this.range = range;
	}

	@Override
	public void add(int s) {
		// TODO Auto-generated method stub
		
	}
	public Cells getCells() {
		return cells;
	}
	public int getRange() {
		return range;
	}
	public int getX() {
		return x;
	}
	
}
