package gameData;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import gameData.Zombies.Zombies;
import gameData.ground.Ground;

public class Level implements Serializable{
	private final ArrayList<Wave> listWaves;
	private int wavePosition;	//index de la vague actuelle
	private int nbZombies;	//nombre de zombies restants à faire apparaitre, s'actualise
	private int maxZombies;	//nombre de zombies à faire apparaitre, fixe
	
	//Niveau de jeu comprenant des vagues de zombies

	public Level(ArrayList<Wave> listWaves) {
		wavePosition=0;
		this.listWaves = Objects.requireNonNull(listWaves);
		nbZombies=0;
		for (int i = 0; i < listWaves.size(); i++) {
			nbZombies+=listWaves.get(i).getNbZombies();
			maxZombies+=listWaves.get(i).getNbZombies();
		}
	}
	
	public Level() {
		wavePosition=0;
		listWaves = new ArrayList<>();
		nbZombies=0;
		maxZombies=0;
	}
	
	public void addWave(Wave w) {	//ajout d'une vague
		listWaves.add(w);
		nbZombies+=w.getNbZombies();
		maxZombies+=w.getNbZombies();
	}
	
	public boolean isFinished() {	//vérifie si la dernière vague est terminée
		return wavePosition==listWaves.size()-1 && listWaves.get(listWaves.size()-1).isFinished();
	}
	
	public boolean lastWave() {	//indique si la dernière vague est en cours de jeu
		return wavePosition==listWaves.size()-1;
	}

	public boolean play(Ground ground,int sizeCell) {	//avancée du niveau
		if (isFinished()) {	//fin de partie
			return false;
		}
		if (listWaves.get(wavePosition).isFinished()) {	//changement de vague
			wavePosition+=1;
		}
		nbZombies=listWaves.get(wavePosition).walk(ground,nbZombies);	//avancée de la vague actuelle
		return true;
	}
	
	public ArrayList<Zombies> getZombiesPlaying(){		//liste de tous les zombies du niveau
		ArrayList<Zombies> zombies = new ArrayList<>();
		for (Wave wave : listWaves) {
			for (Zombies zombies2 : wave.getZombies()) {
				zombies.add(zombies2);
			}
		}
		return zombies;
	}
	
	public void initializeSpawn(Ground ground,int width,int height) {	//chaque zombie du niveau est initialisé
		for (Wave wave : listWaves) {
			wave.initializeSpawn(ground, width, height);
		}
	}
	
	public void draw(Graphics2D graphics2d,Ground ground,int sizeCell,ArrayList<Image> imagesList) {
		graphics2d.setColor(Color.BLACK);
		graphics2d.fill(new Rectangle2D.Float((ground.getNbColumns()/2*sizeCell)-150, ground.getNbLines()*sizeCell, 300, 50));
		graphics2d.setColor(Color.GREEN);
		graphics2d.fill(new Rectangle2D.Float((ground.getNbColumns()/2*sizeCell)-150, ground.getNbLines()*sizeCell, 300*((float)-(nbZombies-maxZombies)/maxZombies), 50));
		graphics2d.drawImage(imagesList.get(15), (int) ((ground.getNbColumns()/2*sizeCell)-150+(300*((float)-(nbZombies-maxZombies)/maxZombies)))-35, ground.getNbLines()*sizeCell-7, 70, 60, null);
	}
}
