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
import gameData.Plants.FlowerPot;
import gameData.Plants.Garlic;
import gameData.Zombies.BalloonZombie;
import gameData.Zombies.BasicDuckyTubeZombie;
import gameData.Zombies.BasicZombie;
import gameData.Zombies.BucketHeadDuckyTubeZombie;
import gameData.Zombies.BucketHeadZombie;
import gameData.Zombies.ConeHead;
import gameData.Zombies.ConeHeadDuckyTubeZombie;
import gameData.Zombies.DancingZombie;
import gameData.Zombies.DiggerZombie;
import gameData.Zombies.DolphinRiderZombie;
import gameData.Zombies.FlagZombie;
import gameData.Zombies.FootballZombie;
import gameData.Zombies.Gargantuar;
import gameData.Zombies.LadderZombie;
import gameData.Zombies.NewsPaperZombie;
import gameData.Zombies.PogoZombie;
import gameData.Zombies.PoleVaulterZombie;
import gameData.Zombies.ScreenDoorZombie;
import gameData.Zombies.ZamboniZombie;
import gameData.Zombies.Zombies;

public class RoofGround extends Ground {

	public RoofGround(int sizeCell, Level level) {
		super(5, 9, sizeCell, 0, level, 67, 73);
		prepareRoofCell();
	}

	private void prepareRoofCell() {
		for (int i = 0; i < 5; i++) {

			for (int j = 0; j < 4; j++) {
				lines[i].setCell(new RoofCell(new FlowerPot()), j);//On met sur les 4 première colonne les pot de fleurs
			}
			for (int j = 4; j < 9; j++) {
				lines[i].setCell(new RoofCell(), j);
			}
		}
	}

	@Override
	public void linesAttack() {
		// TODO Auto-generated method stub
		super.linesAttack();
		for (Lines lines2 : lines) {
			lines2.projectileOutOfRange(4);//On verifie que les projectyiles ne depasse pas la partie casser du toit
		}
	}

	public Zombies zombiesPlaying(int width, int height) {
		Coordinates c = new Coordinates(0, 0);
		Zombies[] typeZ = {new BasicZombie(c),new ConeHead(c), new BucketHeadZombie(c),//Jour
				new FootballZombie(c),
				new PogoZombie(c),new BalloonZombie(c),
				new LadderZombie(c),randomCoordinatesPlant(),new Gargantuar(c)}; // liste de tous les types de zombies
		Zombies zombies = typeZ[new Random().nextInt(typeZ.length)];
		zombies.setCoordinates(randomStartingZCooordinates(width, height));
		return zombies;
	}

	public Zombies generateRandomZombie() { // gï¿½nï¿½re un type de zombie alï¿½atoire aux coordonnï¿½es spï¿½cifiï¿½es

		Random randomZombie = new Random(); //
		Coordinates c = randomSpawnCoordinatesZombie();
		Zombies[] typeZ = { new Gargantuar(c), randomCoordinatesPlant() }; // liste de tous les types de zombies

		int iZombie = randomZombie.nextInt(typeZ.length); // ajoute un type de zombie alï¿½atoire
		return typeZ[iZombie];
	}

	@Override
	public ArrayList<Cards> cardsPlaying(int nb, int maxHeigth) {
		ArrayList<Cards> plantsPlay = new ArrayList<Cards>();
		int heigth = (int)(sizeCard(nb, maxHeigth)*0.8);
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
		plantsPlay.add(new CardSquash(heigth, (int) (sizeCell * 1.5)));
		plantsPlay.add(new CardThreePeater(heigth, (int) (sizeCell * 1.5)));
		plantsPlay.add(new CardJalaPeno(heigth, (int) (sizeCell * 1.5)));
		plantsPlay.add(new CardSpikeWeed(heigth, (int) (sizeCell * 1.5)));
		plantsPlay.add(new CardTorchWood(heigth, (int) (sizeCell * 1.5)));
		plantsPlay.add(new CardTallNut(heigth, (int) (sizeCell * 1.5)));

		// fog
		plantsPlay.add(new CardCactus(heigth, (int) (sizeCell * 1.5)));
		plantsPlay.add(new CardSplitPea(heigth, (int) (sizeCell * 1.5)));
		plantsPlay.add(new CardPumpkin(heigth, (int) (sizeCell * 1.5)));
		plantsPlay.add(new CardMagnetShroom(heigth, (int) (sizeCell * 1.5), false));

		// roof
		plantsPlay.add(new CardCabbagePult(heigth, (int) (sizeCell * 1.5)));
		plantsPlay.add(new CardFlowerPot(heigth, (int) (sizeCell * 1.5)));
		plantsPlay.add(new CardKernelPult(heigth, (int) (sizeCell * 1.5)));
		plantsPlay.add(new CardCoffeeBean(heigth, (int) (sizeCell * 1.5)));
		plantsPlay.add(new CardGarlic(heigth, (int) (sizeCell * 1.5)));
		return plantsPlay;
	}
}
