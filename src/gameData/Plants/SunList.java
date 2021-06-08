package gameData.Plants;

import java.util.ArrayList;

public class SunList implements LinesToGroundData{//Pour renvoyer depuis les lignes les soleil produit par les plantes
	private ArrayList<Integer> sunList=new ArrayList<Integer>();
	
	public void add(int s) {
		sunList.add(s);
	}
	
	
	public ArrayList<Integer> getSunList() {
		return sunList;
	}
}
