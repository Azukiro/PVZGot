package gameData.Plants;

import java.util.ArrayList;

import gameData.ground.Cells;

public interface LinesToGroundData {//Class pour que les plantes puisse attaquer sur plusieur ligne
	void add(int s);
	
	public default Cells getCells() {
		return null;
	}
	public default int getRange() {
		return 0;
	}
	public default int getX() {
		return 0;
	}
	public default ArrayList<Integer>  getSunList() {
		return null;
	}
}
