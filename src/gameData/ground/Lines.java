package gameData.ground;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import gameData.Bot;
import gameData.Coordinates;
import gameData.LawnMower;
import gameData.Plants.LinesToGroundData;
import gameData.Plants.ManyLinesData;
import gameData.Plants.Peashooter;
import gameData.Plants.Plants;
import gameData.Plants.Projectile;
import gameData.Plants.SunList;
import gameData.Plants.TorchWood;
import gameData.Zombies.BobsledTeam;
import gameData.Zombies.ZamboniZombie;
import gameData.Zombies.Zombies;

public class Lines implements Serializable {
	private final Cells[] columns;
	private ArrayList<Zombies> listZ = new ArrayList<>();
	private ArrayList<Zombies> listGoodZ = new ArrayList<>();
	private ArrayList<Projectile> projectiles = new ArrayList<>();
	private final int sizeCell;
	private LawnMower lawnMower;
	private final int nbLine;

	public Lines(int c, int sizeCell, int y) {
		lawnMower = new LawnMower(y);
		nbLine = y;
		this.columns = new Cells[c];
		this.sizeCell = sizeCell;
		for (int i = 0; i < columns.length; i++) {
			columns[i] = new Cells();
		}
	}

	public void setCell(Cells c, int i) {
		columns[i] = c;
	}

	public void setFog(boolean fog) {

		for (int i = 0; i < columns.length; i++) {
			if (i > (columns.length / 2) - 1) {
				columns[i].setFog(true);
			}
		}
	}

	public void drawElements(Graphics2D graphics, int height, ArrayList<Image> listiImages) {
		drawPlants(graphics, height, listiImages);
		drawZombies(graphics, listiImages);
		drawFog(graphics, height, listiImages);
		drawProjectile(graphics, height, listiImages);
	}

	public void drawProjectile(Graphics2D graphics, int height, ArrayList<Image> listiImages) {
		for (Projectile pro : projectiles) {
			pro.draw(graphics, sizeCell, nbLine, listiImages);
			pro.move();
		}
	}

	public void drawPlants(Graphics2D graphics, int y, ArrayList<Image> listiImages) {
		for (int i = 0; i < columns.length; i++) {
			columns[i].drawCells(graphics, i * sizeCell, y, sizeCell, listiImages);

		}
	}

	public void drawZombies(Graphics2D graphics, ArrayList<Image> listiImages) {
		if (lawnMower != null) {
			lawnMower.draw(graphics, sizeCell, listiImages);
		}
		for (Zombies zo : listZ) {
			zo.draw(graphics, sizeCell, listiImages);
		}
		for (Zombies zomb : listGoodZ) {
			zomb.draw(graphics, sizeCell, listiImages);

		}
	}

	public void drawFog(Graphics2D graphics, int y, ArrayList<Image> listImages) {
		for (int i = 0; i < columns.length; i++) {
			if (columns[i].isFog()) {
				columns[i].drawCells(graphics, i * sizeCell, y, sizeCell, listImages);
			}
		}
	}

	public void lawnMowerManagment(int sizeCase) {

		if (lawnMower.isFinished((columns.length - 1) * sizeCase)) {//On regarde si la lawnMover et doit encore avancer
			lawnMower = null;
			return;
		}

		int z = 0;

		if (listZ.size() > 0 && !lawnMower.isActive() && lawnMower.zombieInRange(sizeCase, listZ.get(z))) {//On regarde la lawnmer à un zombie dans sa range
			lawnMower.setActive();
		}

		if (lawnMower.isActive()) {//On la fait bouger
			lawnMower.move();
		}

		if (listZ.size() > 0 && lawnMower.isActive() && lawnMower.zombieInRange(sizeCase, listZ.get(z))) {//puis ttaquer le zombie
			lawnMower.attack(listZ.get(z));
			removeZombie(z);

		}
	}

	public ArrayList<LinesToGroundData> wichPlantsAttack(int sizeCase) {//On fais attaquer les plante
		ArrayList<LinesToGroundData> plantsManyLines = new ArrayList<LinesToGroundData>();
		if (lawnMower != null) {
			lawnMowerManagment(sizeCase);//les lawnMower regarde si des zombie sont à leur porter
		}
		plantsManyLines.add(new SunList());
		for (int i = 0; i < columns.length; i++) {
			if (columns[i].havePlants()) {
				boolean b = columnsAttack(columns[i], sizeCase, i, plantsManyLines);
				if (b && columns[i].attackManyLine() > 0) {//Si la cellule à attaquer ou peut attaquer et qu'elle attaque plusieur ligne on la renvoi via l'interface  LinesToGroundData
					plantsManyLines.add(new ManyLinesData(columns[i], i, columns[i].attackManyLine()));
				}

			}
			if (columns[i].isIceCell()) {
				IceCell cell = (IceCell) columns[i];
				if (cell.hasExpired()) {//On nettoie les cellule de glace
					columns[i] = new Cells();
				}
			}

		}

		return plantsManyLines;
	}

	public void addProjectile(Projectile p) {
		projectiles.add(p);
	}

	public void projectileOutOfRange(int x) {//On regarde si les projectiles sont toujours viable sinon on les supprime
		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).breaker(x, sizeCell)) {
				projectiles.remove(i);
				i--;
			}
		}
	}

	public ArrayList<Projectile> attackProjectile() {//On fais attaquer les projectiles et on verifie aussi qu'il sois dans la bonne ligne
		ArrayList<Projectile> wrongProjectiles = new ArrayList<Projectile>();
		for (int p = 0; p < projectiles.size(); p++) {
			Projectile pro = projectiles.get(p);
			if (pro.wrongLine(nbLine * sizeCell)) {
				wrongProjectiles.add(pro);
				projectiles.remove(p);
				p--;
				continue;
			}
			for (int z = 0; z < listZ.size(); z++) {
				Zombies zombie = listZ.get(z);
				if (pro.haveZombieInRange(zombie, sizeCell)) {
					pro.attack(zombie);
					if (removeZombie(z)) {
						z--;
					}
					projectiles.remove(p);
					p--;
					break;

				}
			}
		}
		return wrongProjectiles;
	}

	public void modifyFog(Ground ground) {
		int i = 0;
		for (Cells cells : columns) {
			cells.removeFogFromPlant(ground, i, nbLine);
			i++;
		}
	}

	public boolean columnsAttack(Cells cell, int sizeCase, int i, ArrayList<LinesToGroundData> plantsManyLines) {//Methode pour que le cellules atatque
		int z = 0;
		boolean result = false;
		cell.getPlants().addProjectiles(projectiles, i, sizeCase);//On récupère le sprojectiles produit par les plantes
		Zombies zombie;
		while (z < listZ.size() + 1) {//On parcours la list des zombie +1 pour que les plante nécessitant des action sans zombie puisse les effectuer

			int sun = cell.newSun();//On recupère les soleil
			if (sun > 0) {
				if (plantsManyLines != null) {
					plantsManyLines.get(0).add(sun);
					plantsManyLines.get(0).add(i);
				}
			}
			if (listZ.size() > 0 && z < listZ.size()) {//On vérifie que la list contient bien des zombie
				zombie = listZ.get(z);
			} else {
				zombie = null;
			}
			boolean b = cell.canAttack(zombie, sizeCase, i);//la cellule ttaque
			if (b) {
				result = true;
				if (listZ.size() > z) {

					int attack = cell.attack(listZ.get(z));
					if (attack < 0) {
						listZ.get(z).back();
						listGoodZ.add(listZ.get(z));
						listZ.remove(z);
					}

					if (removeZombie(z)) {
						z--;
					}

					if (!cell.attackManyZombies()) {//si elle ne peut pas attaquer d'autre zombie elle est arrater sinon la cellule recommence

						break;
					}
				}
				z++;

			} else {
				removeZombie(z);
				z++;
			}

		}
		return result;//On dit qu'on à attaquer

	}

	public void removeFog(int x, long time) {//Methode pour retirer le brouillard pendant un temps donner
		if (x >= 0 && x < columns.length) {
			columns[x].removeFog(time);
		}
	}

	public boolean zombiesOut() {//On regarde si les zombies sont endehors du terraib
		if (listZ.size() > 0) {
			if (listZ.get(0).getCoordinates().getX() <= -100) {
				return true;
			}
			if (listZ.get(listZ.size() - 1).getCoordinates().getX() >= sizeCell * (columns.length - 1)) {
				listZ.remove((listZ.size() - 1));
				return false;
			}
		}
		return false;
	}

	public void cleanPlants() {
		for (Cells cells : columns) {
			cells.cleanCell();
		}
	}

	public void moveZombies() {
		for (Zombies zo : listZ) {
			zo.moveZombie();
		}
	}

	public ArrayList<Zombies> wichZombiesAttack(int sizeCase, Ground ground) {// Methode d'atatque des zombie
		boolean mouv = true;
		ArrayList<Zombies> wrongLine = new ArrayList<Zombies>();
		for (int pos = 0; pos < listZ.size(); pos++) {// On parcourt la liste
			mouv = true;
			Zombies zombies = listZ.get(pos);

			if (zombies.placeIceTrail()) {// Pour ajouter la glace quand les zombie spécifique passe
				int columnsi = (zombies.getCoordinates().getX() / sizeCase) + 1;
				if (columnsi < columns.length && !columns[columnsi].isIceCell()) {
					boolean fog = false;
					if (columns[columnsi].isFog()) {
						fog = true;
					}
					columns[columnsi] = new IceCell();
					columns[columnsi].setFog(fog);
					ZamboniZombie zamboniZombie = (ZamboniZombie) zombies;
					zamboniZombie.incrementIceCells();
					if (zamboniZombie.canSpawnBobsledTeam()) {
						listZ.add(new BobsledTeam(new Coordinates((columns.length - 1) * sizeCell, nbLine * sizeCell)));
					}
				}
			}
			if (!columns[(zombies.getCoordinates().getX() / sizeCell)].isIceCell()) {
				if (zombies.isBobsledTeam()) {
					BobsledTeam bobsledTeam = (BobsledTeam) zombies;
					bobsledTeam.spawnBobsleds(sizeCase, this, columns.length);
					listZ.remove(pos);
					pos--;
				}
			}

			int y = (zombies.getCoordinates().getX()) / sizeCase;
			if (y > -1 && columns[y].getPlants() != null && zombies.inRange(y, sizeCase)&& columns[y].colision(zombies)) {//On regarde si le zombie peut attquer
				mouv = false;//si c'est le cas il ne peut pas bouger et il attaque
				if (zombies.canAttack()) {
					zombies.attackCell(columns[y]);
					removeZombie(pos);
					pos--;
					columns[y].cleanCell();

				}
			} else {
				Zombies zombie = zombies.wakeUpZombie();//Pour si u zombie en révèle un autre
				if (zombie != null) {
					listZ.add(zombie);
					removeZombie(pos);
					pos--;

				}
			}
			for (int zo = 0; zo < listGoodZ.size(); zo++) {//On fais attaquer le zombie les bon zombie
				if (zombies.goodZombieInRange(listGoodZ.get(zo), sizeCase)) {
					mouv = false;
					if (zombies.canAttack()) {
						zombies.attack(listGoodZ.get(zo));
						if (removeGoodZombie(zo)) {
							zo--;
						}

					}
				}
			}

			if (mouv || zombies.cantBeStop()) {//On regarde si le zombie peut bouger et on le bouge si c'est le cas
				if (Bot.isActive()) {
					for (int i = 0; i < Bot.getSpeed(); i++) {
						zombies.moveZombie();
						zombies.horizontalMove(ground.getNbLines(), sizeCase, nbLine);
						zombies.spawnDancers(this, sizeCell, pos, ground);
					}
				} else {
					zombies.moveZombie();
					zombies.horizontalMove(ground.getNbLines(), sizeCase, nbLine);
					zombies.spawnDancers(this, sizeCell, pos, ground);
				}
			}
			if (zombies.getCoordinates().getY() < sizeCase * (nbLine - 1)
					|| zombies.getCoordinates().getY() > sizeCase * (nbLine + 1)) {//On fais bouger les zombie horizontallemebt

				listZ.remove(zombies);
				ground.putZombie(zombies);
				zombies.setHorizontalMove(0);//Et ajoute a une autre liste s'il on changer de ligne
				wrongLine.add(zombies);
				pos--;
			}

		}
		boolean goodMouv = true;
		for (Zombies zomb : listGoodZ) {//Meme principe que précédement mais avec les bon zombie
			goodMouv = true;
			for (int z = 0; z < listZ.size(); z++) {
				if (zomb.ennemyZombieInRange(listZ.get(z), sizeCase)) {
					goodMouv = false;
					if (zomb.canAttack()) {
						zomb.attack(listZ.get(z));
					}
					if (removeZombie(z)) {
						z--;
					}
				}
			}
			if (goodMouv) {
				if (Bot.isActive()) {
					for (int k = 0; k < Bot.getSpeed(); k++) {
						zomb.moveZombie();
					}
				} else {
					zomb.moveZombie();
				}

			}
		}

		Collections.sort(listZ);// On tri les zombie par coordonées
		return wrongLine;
	}

	public int lastPlants() {// Obtenir la derniere plnate dans une lifne
		for (int i = columns.length - 1; i >= 0; i--) {
			if (columns[i].havePlants()) {
				return i;
			}
		}
		return -1;
	}

	public void putPlant(Plants plants, int y) {
		columns[y].setPlants(plants);
		System.out.println("Ajout de " + plants);
	}

	public boolean havePlant(int x) {
		return columns[x].getPlants() != null;
	}

	public void addZombie(Zombies z) {
		listZ.add(z);
		System.out.println("Ajout de " + z);
	}

	public boolean removeZombie(int z) {// Enlever un mauvais zombie
		if (listZ.size() > z && z >= 0 && !listZ.get(z).isAlive()) {
			if (listZ.get(z).isReadyToJump()) {// Quand le bobseldTeam meurt on fais le spawn le reste des bobslayer
				BobsledTeam bobsledTeam = (BobsledTeam) listZ.get(z);
				bobsledTeam.spawnBobsleds(sizeCell, this, columns.length);
			}

			listZ.remove(z);
			return true;
		}
		return false;
	}

	public boolean removeGoodZombie(int z) {// enlever un zombie bon quand il est mort
		if (listGoodZ.size() > z && z >= 0 && !listGoodZ.get(z).isAlive()) {
			listGoodZ.remove(z);
			return true;
		}
		return false;
	}

	public Cells wichCell(int x) {// A partir d'une coordonée obtenir la celluke
		if (x >= 0 && x <= sizeCell * columns.length) {
			int index = 0;
			index = ((x) / sizeCell);
			return columns[index];
		}
		return null;
	}

	@Override
	public String toString() {
		String reString = "";
		for (int i = 0; i < columns.length; i++) {
			reString += columns[i].havePlants() + "|";
		}
		reString += "---" + listZ;
		return reString;
	}

	public void removeZombie(Zombies z) {// Enlever un zimbie
		listZ.remove(z);
	}
}
