package gameData.Plants;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import gameData.Bot;
import gameData.LivingBeing;
import gameData.Zombies.Zombies;
import gameData.ground.Cells;
import gameData.ground.Ground;

public class Plants extends LivingBeing implements Serializable {

	private final int range;// Déterminer sur cb de case elle peuvent attaquer
	protected int image;

	public Plants(int damage, int health, int delay, int range, int image) {
		this(damage, health, delay, range, image, 0);
	}

	public Plants(int damage, int health, int delay, int range, int image, int height) {
		super(damage, health, delay, height);
		this.range = range;
		this.image = image;
	}

	public int getImageInt() {
		return image;
	}

	public void addProjectiles(ArrayList<Projectile> projectiles, int i, int sizeCell) {// Methode pour les plantes qui
																						// tire des projectiles

	}

	public String getCardImages() {
		return tag();
	}

	@Override
	public String toString() {
		return super.toString() + " Je tire ï¿½ une distance de " + range;
	}

	@Override
	public String tag() {// Nom de la plante pour le toString
		return "Plants";
	}

	@Override
	public boolean canAttack() {
		return super.canAttack();
	}

	public boolean colision(Zombies z) {// Pour savoir si les zombie sont bloqué ou pas par la plante

		return true;
	}

	@Override
	public int attack(LivingBeing l) {
		super.attack(l);
		l.removeDefense();
		return 0;
	}

	public int attackManyLine() {// Pour si une plnate peut attaquer plusieurs ligne
		return 0;
	}

	public boolean attackManyZombies() {// Si une plante n'arrete pas son attque à un seul zombie
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Plants)) {
			return false;
		}

		Plants p = (Plants) obj;
		return super.equals(p) && range == p.range && image == p.image;
	}

	@Override
	public int hashCode() {
		return super.hashCode() + Objects.hash(image, range);
	}

	public boolean inHeight(Zombies z) {// Vérifier si le zombie est à la meme hauteurs que la plante pour que celle ci
										// l'attaque
		return (z.getHeight() == getHeight());
	}

	public boolean zombieInRange(Zombies z, int sizeCase, int x) {// On regarde si le zombie peut etre attaquer par la
																	// range de la plante et par son délais d'attaque
		boolean b = canAttack();
		return plantHaveRange(z, sizeCase, x) && b;
	}

	public boolean plantHaveRange(Zombies z, int sizeCase, int x) {
		if (z == null) {
			return false;
		}
		return plantHaveRangeWithoutheigth(z, sizeCase, x) && inHeight(z);
	}

	public boolean plantHaveRangeWithoutheigth(Zombies z, int sizeCase, int x) {// On regarde si le zombie est dans la
																				// range de la plante
		if (z == null) {
			return false;
		}
		return z.getCoordinates().getX() <= ((x + range) * sizeCase)
				&& z.getCoordinates().getX() >= (x * sizeCase) - sizeCase / 2;
	}

	public boolean behindPlantHaveRange(Zombies z, int sizeCase, int x) {// Meme chose mais en regardant derrière
		if (z == null) {
			return false;
		}
		return z.getCoordinates().getX() <= x * sizeCase && z.getCoordinates().getX() >= ((x - range) * sizeCase)
				&& inHeight(z);
	}

	public boolean behindZombieInRange(Zombies z, int sizeCase, int x) {
		boolean b = canAttack();

		return behindPlantHaveRange(z, sizeCase, x) && b;
	}

	public void draw(Graphics2D graphics, int x, int y, int height, ArrayList<Image> imagesList) {
		graphics.drawImage(imagesList.get(image), x, y, height, height, null);
		super.drawLife(graphics, x, y, height);

	}

	public int newSun() {// SI la plante renvoit un soleil
		return 0;
	}

	public boolean attackRoof() {
		return false;
	}

	public void wakeUp() {// Pour réveiller les plantes
		return;
	}

	public boolean putLadder() {// Mettre l'échelle sur les plantes défensive
		return false;
	}

	public boolean activePlant(Cells cells) {
		if (cells.havePlants() || !cells.haveBase() || cells.haveTomb()) {// Si la cellyle n'as pas de plantes ou
																			// qu'elle à une base ou qu'elle n'as pas de
																			// tmbe on peut poser la plante
			return false;
		}
		return true;
	}

	public void effectOnFog(Ground ground, int x, int y) {// Pour que les plantes puisse modifier l état du brouillard
															// mais par deafut elle ne font rien
		return;
	}
}
