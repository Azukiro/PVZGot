package gameData.ground;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import gameData.Bot;
import gameData.Coordinates;
import gameData.Level;
import gameData.Cards.CardCherryBomb;
import gameData.Cards.CardChomper;
import gameData.Cards.CardDoomShroom;
import gameData.Cards.CardFumeShroom;
import gameData.Cards.CardGraveBuster;
import gameData.Cards.CardHypnoShroom;
import gameData.Cards.CardIceShroom;
import gameData.Cards.CardJalaPeno;
import gameData.Cards.CardPeashooter;
import gameData.Cards.CardPotatoMine;
import gameData.Cards.CardPuffShroom;
import gameData.Cards.CardRepeater;
import gameData.Cards.CardScaredyShroom;
import gameData.Cards.CardSnowPea;
import gameData.Cards.CardSpikeWeed;
import gameData.Cards.CardSquash;
import gameData.Cards.CardSunFlower;
import gameData.Cards.CardSunShroom;
import gameData.Cards.CardTallNut;
import gameData.Cards.CardThreePeater;
import gameData.Cards.CardTorchWood;
import gameData.Cards.CardWallNut;
import gameData.Cards.Cards;
import gameData.Zombies.BasicZombie;
import gameData.Zombies.BucketHeadZombie;
import gameData.Zombies.ConeHead;
import gameData.Zombies.DancingZombie;
import gameData.Zombies.FlagZombie;
import gameData.Zombies.FootballZombie;
import gameData.Zombies.NewsPaperZombie;
import gameData.Zombies.PoleVaulterZombie;
import gameData.Zombies.ScreenDoorZombie;
import gameData.Zombies.Zombies;

public class NightGarden extends Ground{
	private  TombCell[] arrayTombCells;
	public NightGarden(int sizeCell,Level level) {
		super(5, 10, sizeCell, 0,level,68,71);
		randomTomb(sizeCell);
	}
	
	public void randomTomb(int sizeCell) {
		Random rdn=new Random();
		int nbTomb=4+rdn.nextInt(2);
		arrayTombCells=new TombCell[nbTomb];//On ajoute un nombre de tombe aléatoire dans le terrain
		for(int i=0;i<nbTomb;i++) {
			Coordinates coordinates=matrixCoordinatesRandom();
			if(coordinates.getX()<4) {
				coordinates.plusCoordonates(new Coordinates(4,0));
			}
			Coordinates zomCoordinates=coordinates.multiply(sizeCell);
			arrayTombCells[i]=new TombCell(zomCoordinates);
			lines[coordinates.getY()].setCell(arrayTombCells[i], coordinates.getX());
		}
	}
	
	@Override
	public void linesAttack() {
		// TODO Auto-generated method stub
		super.linesAttack();
		if(level.lastWave()) {//Si on arrive a la derniere wave les tomb font apparaitre ler zombie
			for (TombCell tomb : arrayTombCells) {
				putZombie(tomb.spawnZombie());
			}
		}
	}
	@Override
	public void spawnSun(ArrayList<Integer> nbSunSpaw,int y) {
		addSunByPlant(nbSunSpaw,y);//Seul les zombie de plante apparaise
	}
	
	@Override
	public Zombies putZombieRandomly() { //ajoute un zombie alï¿½atoire sur une ligne alï¿½atoire du plateau
		Random random = new Random();
		int i = random.nextInt(lines.length);
		Coordinates c= new Coordinates((this.getNbColumns()-1)*this.getSizeCell(), i*this.getSizeCell());
		Zombies zombies=NightGarden.generateRandomZombie(c);
		putZombie(zombies ); 
		return zombies;
	}
	
	public static Zombies generateRandomZombie(Coordinates c){ //gï¿½nï¿½re un type de zombie alï¿½atoire aux coordonnï¿½es spï¿½cifiï¿½es
		Objects.requireNonNull(c);
		Random randomZombie = new Random(); //
		Zombies[] typeZ = {new BasicZombie(c), new ConeHead(c),new PoleVaulterZombie(c),new BucketHeadZombie(c),new NewsPaperZombie(c)
				,new FootballZombie(c),new ScreenDoorZombie(c),new DancingZombie(c)}; //liste de tous les types de zombies
		int iZombie = randomZombie.nextInt(typeZ.length); //ajoute un type de zombie alï¿½atoire
		return typeZ[iZombie];
	}
	
	@Override
	public Zombies zombiesPlaying(int width,int height) {
		Coordinates c = new Coordinates(0, 0);
		Zombies[] typeZ = {new BasicZombie(c), new ConeHead(c),new PoleVaulterZombie(c),new BucketHeadZombie(c),new NewsPaperZombie(c)
		,new FootballZombie(c),new ScreenDoorZombie(c), new FlagZombie(c),new DancingZombie(c)}; //liste de tous les types de zombies
		Zombies zombies = typeZ[new Random().nextInt(typeZ.length)];
		zombies.setCoordinates(randomStartingZCooordinates(width, height));
		return zombies;
	}
	
	
	@Override
	public ArrayList<Cards> cardsPlaying(int nb, int maxHeigth) {
		ArrayList<Cards> plantsPlay = new ArrayList<Cards>();
		int heigth = sizeCard(nb, maxHeigth);
		int sizeCell=getSizeCell();
		plantsPlay.add(new CardCherryBomb(heigth,(int)(sizeCell*1.5)));
		plantsPlay.add(new CardPeashooter(heigth,(int)(sizeCell*1.5)));
		plantsPlay.add(new CardWallNut(heigth,(int)(sizeCell*1.5)));
		plantsPlay.add(new CardSunFlower(heigth,(int)(sizeCell*1.5)));
		plantsPlay.add(new CardChomper(heigth,(int)(sizeCell*1.5)));
		plantsPlay.add(new CardRepeater(heigth,(int)(sizeCell*1.5)));
		plantsPlay.add(new CardPotatoMine(heigth, (int)(sizeCell*1.5)));
		plantsPlay.add(new CardSnowPea(heigth, (int)(sizeCell*1.5)));
		plantsPlay.add(new CardPuffShroom(heigth, (int)(sizeCell*1.5),true));
		plantsPlay.add(new CardSunShroom(heigth, (int)(sizeCell*1.5),true));
		plantsPlay.add(new CardFumeShroom(heigth, (int)(sizeCell*1.5),true));
		plantsPlay.add(new CardGraveBuster(heigth, (int)(sizeCell*1.5)));
		plantsPlay.add(new CardHypnoShroom(heigth, (int)(sizeCell*1.5),true));
		plantsPlay.add(new CardScaredyShroom(heigth, (int)(sizeCell*1.5),true));
		plantsPlay.add(new CardIceShroom(heigth, (int)(sizeCell*1.5),true));
		plantsPlay.add(new CardDoomShroom(heigth, (int)(sizeCell*1.5),true));
		plantsPlay.add(new CardSquash(heigth, (int)(sizeCell*1.5)));
		plantsPlay.add(new CardThreePeater(heigth, (int)(sizeCell*1.5)));
		plantsPlay.add(new CardJalaPeno(heigth, (int)(sizeCell*1.5)));
		plantsPlay.add(new CardSpikeWeed(heigth, (int)(sizeCell*1.5)));
		plantsPlay.add(new CardTorchWood(heigth, (int)(sizeCell*1.5)));
		plantsPlay.add(new CardTallNut(heigth, (int)(sizeCell*1.5)));
		
		return plantsPlay;
	}

}
