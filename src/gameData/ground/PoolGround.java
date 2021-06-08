package gameData.ground;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import gameData.Coordinates;
import gameData.Level;
import gameData.Cards.CardBlover;
import gameData.Cards.CardCabbagePult;
import gameData.Cards.CardCactus;
import gameData.Cards.CardCherryBomb;
import gameData.Cards.CardChomper;
import gameData.Cards.CardCoffeeBean;
import gameData.Cards.CardDoomShroom;
import gameData.Cards.CardFlowerPot;
import gameData.Cards.CardFumeShroom;
import gameData.Cards.CardGarlic;
import gameData.Cards.CardGraveBuster;
import gameData.Cards.CardHypnoShroom;
import gameData.Cards.CardIceShroom;
import gameData.Cards.CardJalaPeno;
import gameData.Cards.CardKernelPult;
import gameData.Cards.CardLilyPad;
import gameData.Cards.CardMagnetShroom;
import gameData.Cards.CardPeashooter;
import gameData.Cards.CardPlantern;
import gameData.Cards.CardPotatoMine;
import gameData.Cards.CardPuffShroom;
import gameData.Cards.CardPumpkin;
import gameData.Cards.CardRepeater;
import gameData.Cards.CardScaredyShroom;
import gameData.Cards.CardSeaShroom;
import gameData.Cards.CardSnowPea;
import gameData.Cards.CardSpikeWeed;
import gameData.Cards.CardSplitPea;
import gameData.Cards.CardSquash;
import gameData.Cards.CardSunFlower;
import gameData.Cards.CardSunShroom;
import gameData.Cards.CardTallNut;
import gameData.Cards.CardTangleKelp;
import gameData.Cards.CardThreePeater;
import gameData.Cards.CardTorchWood;
import gameData.Cards.CardWallNut;
import gameData.Cards.Cards;
import gameData.Zombies.BasicDuckyTubeZombie;
import gameData.Zombies.BasicZombie;
import gameData.Zombies.BucketHeadDuckyTubeZombie;
import gameData.Zombies.BucketHeadZombie;
import gameData.Zombies.ConeHead;
import gameData.Zombies.ConeHeadDuckyTubeZombie;
import gameData.Zombies.DolphinRiderZombie;
import gameData.Zombies.FlagDuckyTubeZombie;
import gameData.Zombies.FlagZombie;
import gameData.Zombies.FootballZombie;
import gameData.Zombies.NewsPaperZombie;
import gameData.Zombies.PoleVaulterZombie;
import gameData.Zombies.ScreenDoorZombie;
import gameData.Zombies.SnorkelZombie;
import gameData.Zombies.ZamboniZombie;
import gameData.Zombies.Zombies;

public class PoolGround extends Ground {

	public PoolGround(int sizeCell, Level level) {
		super(6, 10, sizeCell, 0, level, 67, 72);
		for (int i = 0; i < 10; i++) {
			lines[2].setCell(new WaterCell(), i);
			lines[3].setCell(new WaterCell(), i);
		}
	}

	public PoolGround(int sizeCell, Level level, int background) {
		super(6, 10, sizeCell, 0, level, 67, background);
		for (int i = 0; i < 10; i++) {
			lines[2].setCell(new WaterCell(), i);
			lines[3].setCell(new WaterCell(), i);
		}
	}

	@Override
	public Zombies putZombieRandomly() { // ajoute un zombie al�atoire sur une ligne al�atoire du plateau
		Random random = new Random();
		int i = random.nextInt(lines.length);
		Coordinates c = new Coordinates((this.getNbColumns() - 1) * this.getSizeCell(), i * this.getSizeCell());
		Zombies zombies;
		if (c.getY() / this.getSizeCell() == 2 || c.getY() / this.getSizeCell() == 3) {
			zombies = generateRandomWaterZombie(c);
		} else {
			zombies = generateRandomZombie(c);
		}
		putZombie(zombies);
		return zombies;
	}

	@Override
	public Coordinates randomCoordinatesFromZombie(Zombies zombie) {
		int nbColumns = this.getNbColumns();
		int sizeCell = this.getSizeCell();
		Random randomY = new Random();
		if (zombie.spawnInWater()) {
			int y = randomY.nextInt(2) + 2;
			return new Coordinates((nbColumns - 1) * sizeCell, y * sizeCell);
		}
		Random randomY1 = new Random();
		Random randomY2 = new Random();
		int y1 = randomY1.nextInt(2);
		int y2 = randomY2.nextInt(((lines.length - 1) - 4) + 1) + 4;
		int y = randomY.nextInt(2);
		if (y == 0) {
			return new Coordinates((nbColumns - 1) * sizeCell, y1 * sizeCell);
		}
		return new Coordinates((nbColumns - 1) * sizeCell, y2 * sizeCell);
	}

	@Override
	public Zombies putFlagZombieRandomly() {
		Random random = new Random();
		int i = random.nextInt(lines.length);
		Coordinates c = new Coordinates((this.getNbColumns() - 1) * this.getSizeCell(), i * this.getSizeCell());
		Zombies zombies;
		if (c.getY() / this.getSizeCell() == 2 || c.getY() / this.getSizeCell() == 3) {
			zombies = new FlagDuckyTubeZombie(c);
		} else {
			zombies = new FlagZombie(c);
		}
		putZombie(zombies);
		return zombies;
	}

	public static Zombies generateRandomWaterZombie(Coordinates c) { // g�n�re un type de zombie al�atoire aux
																		// coordonn�es sp�cifi�es
		Objects.requireNonNull(c);
		Random randomZombie = new Random(); //
		Zombies[] typeZ = { new BasicDuckyTubeZombie(c), new BucketHeadDuckyTubeZombie(c),
				new ConeHeadDuckyTubeZombie(c), new DolphinRiderZombie(c), new SnorkelZombie(c) }; // liste de tous les
																									// types de zombies
		int iZombie = randomZombie.nextInt(typeZ.length); // ajoute un type de zombie al�atoire
		return typeZ[iZombie];
	}

	public static Zombies generateRandomZombie(Coordinates c) { // g�n�re un type de zombie al�atoire aux
																// coordonn�es sp�cifi�es
		Objects.requireNonNull(c);
		Random randomZombie = new Random(); //
		Zombies[] typeZ = { new BasicZombie(c), new ConeHead(c), new PoleVaulterZombie(c), new BucketHeadZombie(c),
				new NewsPaperZombie(c), new FootballZombie(c), new ScreenDoorZombie(c), new ZamboniZombie(c) }; // liste
																												// de
																												// tous
																												// les
																												// types
																												// de
																												// zombies
		int iZombie = randomZombie.nextInt(typeZ.length); // ajoute un type de zombie al�atoire
		return typeZ[iZombie];
	}

	@Override
	public Zombies zombiesPlaying(int width, int height) {
		Coordinates c = new Coordinates(0, 0);
		Zombies[] typeZ = { new BasicZombie(c), new ConeHead(c), new PoleVaulterZombie(c), new BucketHeadZombie(c),
				new NewsPaperZombie(c), new FootballZombie(c), new ScreenDoorZombie(c), new FlagZombie(c),
				new BasicDuckyTubeZombie(c), new BucketHeadDuckyTubeZombie(c), new ConeHeadDuckyTubeZombie(c),
				new DolphinRiderZombie(c), new SnorkelZombie(c), new ZamboniZombie(c) }; // liste de tous les types de
																							// zombies
		Zombies zombies = typeZ[new Random().nextInt(typeZ.length)];
		zombies.setCoordinates(randomStartingZCooordinates(width, height));
		return zombies;
	}

	@Override
	public ArrayList<Cards> cardsPlaying(int nb, int maxHeigth) {
		ArrayList<Cards> plantsPlay = new ArrayList<Cards>();
		int heigth = (int) (sizeCard(nb, maxHeigth) * 0.8);
		int sizeCell = getSizeCell();
		// Day
		plantsPlay.add(new CardPeashooter(heigth, (int) (sizeCell * 1.5)));
		plantsPlay.add(new CardSunFlower(heigth, (int) (sizeCell * 1.5)));
		plantsPlay.add(new CardCherryBomb(heigth, (int) (sizeCell * 1.5)));
		plantsPlay.add(new CardWallNut(heigth, (int) (sizeCell * 1.5)));
		plantsPlay.add(new CardPotatoMine(heigth, (int) (sizeCell * 1.5)));
		plantsPlay.add(new CardSnowPea(heigth, (int) (sizeCell * 1.5)));
		plantsPlay.add(new CardChomper(heigth, (int) (sizeCell * 1.5)));
		plantsPlay.add(new CardRepeater(heigth, (int) (sizeCell * 1.5)));

		// Nigth
		plantsPlay.add(new CardPuffShroom(heigth, (int) (sizeCell * 1.5), false));
		plantsPlay.add(new CardSunShroom(heigth, (int) (sizeCell * 1.5), false));
		plantsPlay.add(new CardFumeShroom(heigth, (int) (sizeCell * 1.5), false));
		plantsPlay.add(new CardGraveBuster(heigth, (int) (sizeCell * 1.5)));
		plantsPlay.add(new CardHypnoShroom(heigth, (int) (sizeCell * 1.5), false));
		plantsPlay.add(new CardScaredyShroom(heigth, (int) (sizeCell * 1.5), false));
		plantsPlay.add(new CardIceShroom(heigth, (int) (sizeCell * 1.5), false));
		plantsPlay.add(new CardDoomShroom(heigth, (int) (sizeCell * 1.5), false));

		// Pool
		plantsPlay.add(new CardLilyPad(heigth, (int) (sizeCell * 1.5)));
		plantsPlay.add(new CardSquash(heigth, (int) (sizeCell * 1.5)));
		plantsPlay.add(new CardThreePeater(heigth, (int) (sizeCell * 1.5)));
		plantsPlay.add(new CardTangleKelp(heigth, (int) (sizeCell * 1.5)));
		plantsPlay.add(new CardJalaPeno(heigth, (int) (sizeCell * 1.5)));
		plantsPlay.add(new CardSpikeWeed(heigth, (int) (sizeCell * 1.5)));
		plantsPlay.add(new CardTorchWood(heigth, (int) (sizeCell * 1.5)));
		plantsPlay.add(new CardTallNut(heigth, (int) (sizeCell * 1.5)));

		// fog
		plantsPlay.add(new CardSeaShroom(heigth, (int) (sizeCell * 1.5)));
		plantsPlay.add(new CardCactus(heigth, (int) (sizeCell * 1.5)));
		plantsPlay.add(new CardSplitPea(heigth, (int) (sizeCell * 1.5)));
		plantsPlay.add(new CardPumpkin(heigth, (int) (sizeCell * 1.5)));
		plantsPlay.add(new CardMagnetShroom(heigth, (int) (sizeCell * 1.5), false));

		// roof
		plantsPlay.add(new CardCabbagePult(heigth, (int) (sizeCell * 1.5)));
		plantsPlay.add(new CardKernelPult(heigth, (int) (sizeCell * 1.5)));
		plantsPlay.add(new CardCoffeeBean(heigth, (int) (sizeCell * 1.5)));
		plantsPlay.add(new CardGarlic(heigth, (int) (sizeCell * 1.5)));

		return plantsPlay;
	}
}
