package gameData.Zombies;

import java.awt.Graphics2D;
import java.awt.Image;

import java.util.ArrayList;
import java.util.Objects;

import gameData.Coordinates;
import gameData.LivingBeing;
import gameData.Timer;
import gameData.Plants.Plants;
import gameData.ground.Cells;
import gameData.ground.Ground;
import gameData.ground.Lines;

public class Zombies extends LivingBeing implements Comparable<Zombies> {

	protected int speed;
	protected Coordinates coordinates; // Point en haut ï¿½ gauche de la case
	protected int image;
	protected Timer slowMoveTimer;// Le temps ou le zombie ne peut plus bouger
	protected int slow = 10;
	protected final int slowTimer[][] = new int[2][2];// Les timer et les coef de ralentissement des zombie
	protected boolean back = false;// Pour indiquer si le zombie va en arrière
	private boolean readyToJump;
	private int horizontalMove = 0;

	public Zombies(int health, int speed, int delay, Coordinates coordinates, int image, int heigth) {
		super(200, health, delay, heigth);
		this.speed = speed;
		this.coordinates = coordinates;
		this.image = image;
		slowMoveTimer = new Timer(0);
		readyToJump = false;
	}

	public Zombies(int health, int speed, int delay, Coordinates coordinates, int image) {
		this(health, speed, delay, coordinates, image, 0);
	}

	public boolean removeDefenseMagnetic() {
		return false;
	}

	public boolean cantBeStop() {// Si le zombie ne peut etre stoper par les plante dans son déplacemebt
		return false;
	}

	@Override
	public String tag() {
		return "Zombie";
	}

	@Override
	public String toString() {
		return super.toString() + " Sa vitesse est de " + speed + " et a pour coordonnï¿½es " + coordinates;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Zombies)) {
			return false;
		}

		Zombies z = (Zombies) obj;
		return super.equals(z) && speed == z.speed && coordinates.equals(z.coordinates);
	}

	@Override
	public int hashCode() {
		return super.hashCode() + Objects.hash(speed, coordinates);
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public boolean inRange(int y, int sizeCell) {// On regarde si la position de la pante correspond a celle du zombie
		return coordinates.getX() / sizeCell == y;
	}

	public void moveZombie() {
		int move = speed;
		if (back) {// S'il va en arriere sa vitesse est in verser
			move = -speed;
		}
		if (slowMoveTimer.enoughDelay()) {// Si le slow n'est plus effectif
			if (slowMoveTimer.getDelay() > 0 && slowMoveTimer.getDelay() != slowTimer[1][0]) {// On regarde si un
																								// deuxième slow va
																								// entrer en actiob
				this.slow = slowTimer[1][1];
				slowMoveTimer = new Timer(slowTimer[1][0]);

			} else {

				coordinates.plusCoordonates(new Coordinates(-move, 0));
				slowMoveTimer = new Timer(0);
			}
		} else {
			coordinates.plusCoordonates(new Coordinates(-(speed - (move / slow)), 0));// On déplace le zombie de ca
																						// vitesse moins la vitesse de
																						// son slow
		}
	}

	public void horizontalMove(int maxY, int sizeCase, int yCell) {// Pour deplacer les zombie horizontallemet
		// On utlise le meme procédé que pour la fonction de move sauf su'on vérifie que
		// le zombie ne se décale pas de plus d'une case et q'uapès il est déplacé
		// horizontallement
		if (horizontalMoveActive() && ((coordinates.getY() + (speed * horizontalMove) < (maxY - 1) * sizeCase)
				&& coordinates.getY() + (speed * horizontalMove) > 0)) {
			if (slowMoveTimer.enoughDelay()) {
				if (slowMoveTimer.getDelay() > 0 && slowMoveTimer.getDelay() != slowTimer[1][0]) {
					this.slow = slowTimer[1][1];
					slowMoveTimer = new Timer(slowTimer[1][0]);

				} else {

					coordinates.plusCoordonates(new Coordinates(0, speed * horizontalMove));
					slowMoveTimer = new Timer(0);
				}
			} else {
				coordinates.plusCoordonates(new Coordinates(0, speed * horizontalMove));
			}
		} else {
			horizontalMove = 0;
		}
	}

	public void draw(Graphics2D graphics, int height, ArrayList<Image> imagesList) {
		graphics.drawImage(imagesList.get(image), coordinates.getX(), coordinates.getY(), height, height, null);
		super.drawLife(graphics, coordinates.getX(), coordinates.getY(), height);

	}

	@Override
	public boolean slow(int... time) {//Fonction pour activer le slow sur le zombie
		for (int i = 0; i < time.length; i += 2) {//On parcours les différent slow envoyer en paramètres
			if (time[i] >= slowTimer[time[i + 1] - 1][0]) {//On regarde q'il est plus puissant que le slow déja effectif
				slowTimer[time[i + 1] - 1][0] = time[i];
				slowTimer[time[i + 1] - 1][1] = time[i + 1];
				if (time[i + 1] <= slow) {
					slowMoveTimer = new Timer(time[i]);
					slow = time[i + 1];//Et ob l'active si c'est le cas
				}
			}
		}

		return true;
	}

	public void back() {
		back = true;
	}

	public void spawnDancers(Lines line, int sizeCell, int i, Ground ground) {
		return;
	}

	public boolean ennemyZombieInRange(Zombies z, int sizeCell) {//Pour voire si le zombie peut attaquer des zombie qui se retourne contre lui
		return z.getCoordinates().getX() <= (coordinates.getX() + (sizeCell))
				&& z.getCoordinates().getX() >= (coordinates.getX());
	}

	public boolean goodZombieInRange(Zombies z, int sizeCell) {//Pour voir si le zombie peut attaquer des zombie qui se retourne contre lui
		return z.getCoordinates().getX() >= (coordinates.getX() - (sizeCell))
				&& z.getCoordinates().getX() <= (coordinates.getX());
	}

	@Override
	public int compareTo(Zombies z) { // se compare par la coordonnÃ©e x
		return this.getCoordinates().getX() - z.getCoordinates().getX();
	}

	public boolean placeIceTrail() {
		return false;
	}

	public boolean isReadyToJump() {
		return readyToJump;
	}

	public void setReadyToJump(boolean readyToJump) {
		this.readyToJump = readyToJump;
	}

	public boolean isBobsledTeam() {
		return false;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public int getImage() {
		return image;
	}

	public boolean horizontalMoveActive() {
		if (horizontalMove == 0) {
			return false;
		}
		return true;
	}

	public void setHorizontalMove(int horizontalMove) {
		if (horizontalMove == 1 || horizontalMove == -1 || horizontalMove == 0) {
			this.horizontalMove = horizontalMove;
		}

	}

	public Zombies wakeUpZombie() {
		return null;
	}

	public boolean canGoBack() {//Pour voir si le zombie peut faire demitour au bout du terrain
		return false;
	}

	public int attackCell(Cells cells) {
		Plants p = cells.getPlants();
		if (p != null) {//le zombie attaque la plante présente dans la cellule
			return attack(p);
		}
		return 0;
	}

	public boolean spawnInWater() {//Pour savoir si le zombie spawn dans leau
		return false;
	}

	public boolean removeWheel() {
		return false;
	}

}
