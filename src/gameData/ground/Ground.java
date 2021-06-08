package gameData.ground;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;


import javax.imageio.ImageIO;

import gameData.Bot;
import gameData.Coordinates;
import gameData.Level;
import gameData.Timer;
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
import gameData.Cards.CardShovel;
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
import gameData.Plants.LinesToGroundData;
import gameData.Plants.Plants;
import gameData.Plants.Projectile;
import gameData.Plants.Sun;
import gameData.Zombies.*;

public class Ground implements Serializable{

	protected final Lines[] lines;//Ligne du jeu
	private final int nbLines;
	private final int nbColumns;
	private final int sizeCell;
	private final int yOrigin;
	private  ArrayList<Cards> listCard = new ArrayList<Cards>();//Liste des cards jouable
	private ArrayList<Sun> listSun=new ArrayList<Sun>();//List des soleil en jeu
	private Timer sunTimer=new Timer(0);//Duré avant l'apparition d'un nouveau soleil
	private int totalSun=1000;
	protected final Level level;//Niveau contenant les zombie
	private int footer;
	private int background;
	private Zombies[] zombiesPlaying;
	private int heigthCard=0;//Paramètres pour dessiner les cartes

	public Ground(int nbLines, int nbColumns,int sizeCell,int yOrigin, Level level,int footer,int background) {
		if(nbLines<0||nbColumns<0||sizeCell<0) {
			throw new IllegalArgumentException();
		}
		this.nbLines = nbLines;
		this.nbColumns = nbColumns;
		this.sizeCell=sizeCell;
		this.yOrigin=yOrigin;
		lines= new Lines[nbLines];
		for (int i = 0; i < nbLines; i++) {
			lines[i] = new Lines(nbColumns,sizeCell,i);
		}
		this.level=level;
		this.background=background;
		this.footer=footer;
	}

	////Lines///////////////
	public void linesAttack() {//Méthode pour faire attaquer les line
		if (Bot.isActive()) {
			if (System.currentTimeMillis()%7==0) {//On fait apparaitre une plant aléatoirement si le debug est activé
				putPlantRandomly();
			}
		}
		for (int i = 0; i < lines.length; i++) {
			ArrayList<LinesToGroundData> plantsManyLines=lines[i].wichPlantsAttack(sizeCell);//Liste contenant les plantes attaquant pluseuirs ligne et les soleil produit
			if(plantsManyLines.size()>1) {
				for (int j=1;j<plantsManyLines.size();j++) {
					for (int k = 1; k <= plantsManyLines.get(j).getRange(); k++) {
						if(i-k>=0) {//On vérifie que les coorodoné sont viable et on fait réattquer la cellule sur une autre ligne
							lines[i-k].columnsAttack(plantsManyLines.get(j).getCells(), sizeCell,plantsManyLines.get(j).getX(),null);
						}else {
							plantsManyLines.get(j).getCells().getPlants().addProjectiles(new ArrayList<Projectile>(), 0, 0);
						}
						if(i+k<nbLines) {
							lines[i+k].columnsAttack(plantsManyLines.get(j).getCells(), sizeCell,plantsManyLines.get(j).getX(),null);

						}else {
							plantsManyLines.get(j).getCells().getPlants().addProjectiles(new ArrayList<Projectile>(), 0, 0);
						}
					}

					plantsManyLines.get(j).getCells().cleanCell();//On nettoie la cellule ayant était transferer
				}
				
			}
			ArrayList<Projectile> projectiles=lines[i].attackProjectile();
			for (Projectile projectile : projectiles) {//On vérifie que les prjectiles se trouve dans la bonne ligne sinon on les déplace
				if(!(projectile.rightLine(sizeCell)>lines.length && projectile.rightLine(sizeCell)<0)) {
					lines[projectile.rightLine(sizeCell)].addProjectile(projectile);
				}
				
			}
			lines[i].wichZombiesAttack(sizeCell,this);//On fait attaquer les zombie
			lines[i].cleanPlants();//On nettoie le planteau si jamais des plante sont mortes
			lines[i].projectileOutOfRange(nbColumns);//pour que les projectiles ne sorte pas du plateau
			spawnSun(plantsManyLines.get(0).getSunList(),i);//On fais spawn les soleil renvoyer par les plantes


		}

	}
	
	public void setZombiesPlaying(Zombies[] zombiesPlaying) {
		this.zombiesPlaying = zombiesPlaying;
	}
	
	public Zombies[] getZombiesPlaying() {
		return zombiesPlaying;
	}
	public void createSun(int i,int x,int y) {//On créer un nouveau soleil qu'on ajoute au jeu
		Coordinates sunCoordinates = new Coordinates(x*sizeCell,y*sizeCell);
		Sun sun=new Sun(sunCoordinates,true,i);
		listSun.add(sun);

	}

	public void createGroundSun() {

		Coordinates rdnCoordinates= matrixCoordinatesRandom();//On fais spawn des soleil aléatoirement sur le plateau
		Coordinates sunCoordinates = new Coordinates(rdnCoordinates.getX()*sizeCell, 0);
		listSun.add(new Sun(sunCoordinates, false));

	}
	public void moveAllSun() {//Fonction pour faire avancer les soleil
		for (Sun sun : listSun) {
			sun.moveSun((int)sizeCell*(nbLines-1));
		}
	}

	public void addSunByPlant(ArrayList<Integer> nbSunSpaw,int y) {
		if(nbSunSpaw.size()==0) {
			return ;
		}

		for(int i = 0; i<nbSunSpaw.size();i+=2) {//On crée les soleil donner par les plantes
			createSun(nbSunSpaw.get(i),nbSunSpaw.get(i+1),y);
		}
	}
	public void spawnSun(ArrayList<Integer> nbSunSpaw,int y) {
		moveAllSun();
		if(sunTimer.enoughDelay()) {//Si le délais est suffisant on fais apparaitre un nouveau soleil aléatoirement
			Random rdn = new Random();
			int rdnS =1000 * (1+rdn.nextInt(12));
			sunTimer = new Timer(rdnS);
			createGroundSun();
		}
		addSunByPlant(nbSunSpaw,y);

	}

	public boolean clickInSuns(int x,int y) {//On regarde si on à cliquer sur un soleil du plateau
		for (Sun sun : listSun) {
			if(sun.clickInSun(x, y, sizeCell)) {
				listSun.remove(sun);
				totalSun+=sun.getCost();
				return true;
			}
		}
		return false;
	}
	

	public Coordinates matrixCoordinatesRandom() {
		Random xRandom = new Random();
		Random yRandom = new Random();
		int x = xRandom.nextInt(nbColumns);
		int y = yRandom.nextInt(nbLines);
		return new Coordinates(x, y);
	}
	///Plants//////////////

	


	public void putPlantRandomly() {

		Random randomPlant = new Random();
		int iPlant = randomPlant.nextInt(listCard.size());
		Coordinates randomCoordinates = matrixCoordinatesRandom();
		if (listCard.get(iPlant).delayHasRefill()) {
			putPlant(listCard.get(iPlant).selectPlants(), randomCoordinates.getY(), randomCoordinates.getX());
		}
	}
	public void putPlant(Plants plants,int x, int y) {
		lines[x].putPlant(plants,y);
	}




	/////ZOMBIES//////
	public boolean zombiesWin() {//les zombie gagne s'il sont au dehors du jeu
		for (Lines lines2 : lines) {
			if (lines2.zombiesOut()) {
				return true;
			}
		}
		return false;
	}
	
	public void putZombie(Zombies zombies) {
		if (zombies==null) {
			return;
		}
		int y = zombies.getCoordinates().getY()/sizeCell;
		if(y>=0) {

			lines[y].addZombie(zombies);
		}
	}

	public Zombies putZombieRandomly() { //ajoute un zombie alï¿½atoire sur une ligne alï¿½atoire du plateau
		Zombies zombies=generateRandomZombie();
		putZombie(zombies );
		return zombies;
	}

	public Zombies putFlagZombieRandomly() {//Ajoute un flag zombie pour les grande vague du jeu
		Random random = new Random();
		int i = random.nextInt(lines.length);
		Coordinates c= new Coordinates((nbColumns-1)*sizeCell, i*sizeCell);
		Zombies zombies= new FlagZombie(c);
		putZombie(zombies );
		return zombies;
	}
	
	public Coordinates randomSpawnCoordinatesZombie() {//Faire apparaitre des zombie aléatoirement
		Random random = new Random();
		int i = random.nextInt(lines.length);
		return new Coordinates((nbColumns-1)*sizeCell, i*sizeCell);
	}
	public  Zombies generateRandomZombie( ){ //gï¿½nï¿½re un type de zombie alï¿½atoire aux coordonnï¿½es spï¿½cifiï¿½es
		Random randomZombie = new Random(); //
		Coordinates c= randomSpawnCoordinatesZombie();
		Zombies[] typeZ = { new FlagZombie(c),new BasicZombie(c),new ConeHead(c), new BucketHeadZombie(c),//Jour
				new FootballZombie(c),new ScreenDoorZombie(c),new NewsPaperZombie(c),new DancingZombie(c),//Nuit
				new BasicDuckyTubeZombie(c),new ConeHeadDuckyTubeZombie(c),new BucketHeadDuckyTubeZombie(c),
				new ZamboniZombie(c),new DolphinRiderZombie(c),//Pool
				new PogoZombie(c),new BalloonZombie(c),new DiggerZombie(c),//fog
				new LadderZombie(c),randomCoordinatesPlant(),new Gargantuar(c)}; //Roof

		int iZombie = randomZombie.nextInt(typeZ.length); //ajoute un type de zombie alï¿½atoire
		return typeZ[iZombie];
	}

	public void moveZombies() {
		for (int i = 0; i < lines.length; i++) {
			lines[i].moveZombies();
		}
	}

	public Zombies zombiesPlaying(int width,int height) {//Pour avoir un nouveau zombie en jeu
		Coordinates c = new Coordinates(0, 0);
		//liste de tous les types de zombies
		Zombies[] typeZ = { new FlagZombie(c),new BasicZombie(c),new ConeHead(c), new BucketHeadZombie(c),//Jour
				new FootballZombie(c),new ScreenDoorZombie(c),new NewsPaperZombie(c),new DancingZombie(c),//Nuit
				new BasicDuckyTubeZombie(c),new ConeHeadDuckyTubeZombie(c),new BucketHeadDuckyTubeZombie(c),
				new ZamboniZombie(c),new DolphinRiderZombie(c),//Pool
				new PogoZombie(c),new BalloonZombie(c),new DiggerZombie(c),//fog
				new LadderZombie(c),randomCoordinatesPlant(),new Gargantuar(c)}; //Roof
		Zombies zombies = typeZ[new Random().nextInt(typeZ.length)];
		zombies.setCoordinates(randomStartingZCooordinates(width, height));
		return zombies;
	}
	
	public Coordinates randomCoordinatesFromZombie(Zombies zombie) {
		Random random = new Random();
		int i = random.nextInt(lines.length);
		return new Coordinates((nbColumns-1)*sizeCell, i*sizeCell);
	}

	public Coordinates randomStartingZCooordinates(int width,int height) {//Pour dessiner les zombie aléatoirement dans le menu
		Random randomX = new Random();
		Random randomY = new Random();
		int xmin=(width/2)+sizeCell;
		int ymin=sizeCell*2;
		int x =randomX.nextInt((width-sizeCell)-xmin+1)+xmin;
		int y =randomY.nextInt((height-sizeCell)-ymin+1)+ymin;
		return new Coordinates(x, y);
	}

	
	public boolean groundHavePlant() {//Vérifie si le plateau a une plante 
		for (Lines lines2 : lines) {
			for (int i = 0; i < nbColumns; i++) {
				if(lines2.havePlant(i)) {
					return true;
				}
			}
		}
		return false;
	}

	public BungeeZombie randomCoordinatesPlant() {//Méthode pour avoir un bungee zombie
		Coordinates c=matrixCoordinatesRandom();//Il tire des coordonées aléatoire
		
			if(c.getX()<2) {
				c.addSpeedToX(2);
			}
			if(lines[c.getY()].havePlant(c.getX())) {//Si sur cette coordonéne il y a une plante il va l'attaquer sinon il pose un zombie a sa place
				c=c.multiply(sizeCell);
				return new BungeeZombie(c);
			}else {
				c=c.multiply(sizeCell);
				Zombies[] zombiesArray= {new ConeHead(c), new BasicZombie(c), new BucketHeadZombie(c)};
				int iZombie = (new Random()).nextInt(zombiesArray.length); //ajoute un type de zombie alï¿½atoire
				Zombies zombies= zombiesArray[iZombie];
				return new BungeeZombie(c,zombies);
			}
			
	

	}



	/////Cards//////
	public Cards clique(int x ,int y,Cards card) {//On regarde si on clique sur une carte ou sur le terraib
		if(listCard.size()>0 && listCard.get(0).clickOnSetCard(x)) {
			for (Cards card2 : listCard) {
				if(card2.clickInCards(x, y)) {
					if(card!=null) {
						card.setSelected(false);
					}
					card2.setSelected(true);
					return card2;
				}
			}
		}else if(card!=null) {//SI on clique syr le terraib

			if(y>=yOrigin && y<=yOrigin+sizeCell*nbLines) {

				int line=-1;
				line=((y-yOrigin)/sizeCell);
				Cells cell = lines[line].wichCell(x);


				if( (!(card.enoughCost(totalSun)>=0) || (cell !=null && (cell.havePlants())) || !card.canUseCards())) {//On vérifie que toutes les conditions soit validé et on pose la plante

					if(cell.getPlants()!=null) {
						card.delayHasRefill();
						cell.setPlants(card.selectPlants());
						card.setSelected(false);
						return null;

					}
					return card;
				}else {

					if(cell.setPlants(card.selectPlants())) {
						card.delayHasRefill();
						totalSun-=card.enoughCost(totalSun);
						card.setSelected(false);
					}else {
						return card;
					}
				}


			}


		}
		return null;
	}

	public void addCard(Cards c) {
		Objects.requireNonNull(c);
		int index = listCard.size();
		int x=sizeCell*nbColumns+(sizeCell/2);
		int y=heigthCard*index;
		c.setCoordinates(new Coordinates(x, y));
		listCard.add(c);
	}
	
	public void addShovel() {//ON ajoute la pelle pour supprimer les plantes du plateau
		Cards c=new CardShovel((int)(heigthCard*0.8),(int)( sizeCell));
		int x=sizeCell*nbColumns+(sizeCell/2)+(sizeCell/4);
		int y=heigthCard*listCard.size();
		c.setCoordinates(new Coordinates(x, y));
		listCard.add(c);
		c.setCardImage(88);
		c.setCloseImage(89);
	}
	////DRAWWWW///////////////////
	public void drawBackground(Graphics2D graphics,int heightGround, int widthGround,ArrayList<Image> listImages) {
		graphics.drawImage(listImages.get(background),0,0,widthGround,heightGround,null);

	}

	public void drawLines(Graphics2D graphics,ArrayList<Image> listiImages) {
		for (int i = 0; i < lines.length; i++) {
			lines[i].drawElements(graphics,i*sizeCell, listiImages);
		}

	}

	public void drawPlants(Graphics2D graphics,ArrayList<Image> listiImages) {
		for (int i = 0; i < lines.length; i++) {
			lines[i].drawPlants(graphics, i*sizeCell,listiImages);
		}

	}
	public void draw( Graphics2D graphics,int heightGround, int widthGround,ArrayList<Image> listiImages) {
		drawBackground(graphics, sizeCell*nbLines,  sizeCell*nbColumns,listiImages);
		drawCards(graphics,listiImages);
		drawLines(graphics,listiImages);
		drawCards(graphics,listiImages);
		drawSuns(graphics,listiImages);
		level.draw(graphics, this, sizeCell,listiImages);
	}
	public void drawCards(Graphics2D graphics,ArrayList<Image> listImages) {
		for (Cards card : listCard) {
			card.draw(graphics,listImages);
		}
	}

	public void drawSuns(Graphics2D graphics,ArrayList<Image> listiImages) {
		for (Sun sun : listSun) {
			sun.draw(graphics,sizeCell,listiImages);
		}

		Image image=null;
		try {
			image=ImageIO.read(new File("./Pictures/SunCard.png"));
		} catch (IOException e) {

		}
		graphics.setColor(Color.WHITE);
		graphics.drawImage(image,20,(int)( (sizeCell*nbLines)+(sizeCell/2)),sizeCell+10,sizeCell/2,null);
		Font font = new Font("Serif", Font.PLAIN, 40);
		graphics.setFont(font);
		graphics.drawString(Integer.toString(totalSun),20,(int)( (sizeCell*nbLines)+(sizeCell)));
	}



	public int getFooter() {
		return footer;
	}
	
	public void removeFog(int y,int x, long time) {
		return ;
	}
	//////Override/////////////
	@Override
	public String toString() {
		String reString="";
		for (int i = 0; i < lines.length; i++) {
			reString+=lines[i]+"\n";
		}
		return reString;
	}



	public int getNbColumns() {
		return nbColumns;
	}
	public int getNbLines() {
		return nbLines;
	}
	public int getBackground() {
		return background;
	}
	public int getSizeCell() {
		return sizeCell;
	}
	
	public int sizeCard(int nb,int maxHeigth) {
		heigthCard= maxHeigth/(nb+1);
		return heigthCard;
	}
	public ArrayList<Cards> cardsPlaying(int nb,int maxHeigth){//Liste de toute es cartes jouables avant sélection
		ArrayList<Cards> plantsPlay = new ArrayList<Cards>();
		int heigth=(int)(heigthCard*0.8);
		
		//Day
		plantsPlay.add(new CardPeashooter(heigth,(int)(sizeCell*1.5)));
		plantsPlay.add(new CardSunFlower(heigth,(int)(sizeCell*1.5)));
		plantsPlay.add(new CardCherryBomb(heigth,(int)(sizeCell*1.5)));
		plantsPlay.add(new CardWallNut(heigth,(int)(sizeCell*1.5)));
		plantsPlay.add(new CardPotatoMine(heigth, (int)(sizeCell*1.5)));
		plantsPlay.add(new CardSnowPea(heigth, (int)(sizeCell*1.5)));
		plantsPlay.add(new CardChomper(heigth,(int)(sizeCell*1.5)));
		plantsPlay.add(new CardRepeater(heigth,(int)(sizeCell*1.5)));
		
		//Nigth
		plantsPlay.add(new CardPuffShroom(heigth, (int)(sizeCell*1.5),true));
		plantsPlay.add(new CardSunShroom(heigth, (int)(sizeCell*1.5),true));
		plantsPlay.add(new CardFumeShroom(heigth, (int)(sizeCell*1.5),true));
		plantsPlay.add(new CardGraveBuster(heigth, (int)(sizeCell*1.5)));
		plantsPlay.add(new CardHypnoShroom(heigth, (int)(sizeCell*1.5),true));
		plantsPlay.add(new CardScaredyShroom(heigth, (int)(sizeCell*1.5),true));
		plantsPlay.add(new CardIceShroom(heigth, (int)(sizeCell*1.5),true));
		plantsPlay.add(new CardDoomShroom(heigth, (int)(sizeCell*1.5),true));
		
		//Pool
		plantsPlay.add(new CardLilyPad(heigth, (int)(sizeCell*1.5)));
		plantsPlay.add(new CardSquash(heigth, (int)(sizeCell*1.5)));
		plantsPlay.add(new CardThreePeater(heigth, (int)(sizeCell*1.5)));
		plantsPlay.add(new CardTangleKelp(heigth, (int)(sizeCell*1.5)));
		plantsPlay.add(new CardJalaPeno(heigth, (int)(sizeCell*1.5)));
		plantsPlay.add(new CardSpikeWeed(heigth, (int)(sizeCell*1.5)));
		plantsPlay.add(new CardTorchWood(heigth, (int)(sizeCell*1.5)));
		plantsPlay.add(new CardTallNut(heigth, (int)(sizeCell*1.5)));
		
		//fog
		plantsPlay.add(new CardSeaShroom(heigth, (int)(sizeCell*1.5)));
		plantsPlay.add(new CardPlantern(heigth, (int)(sizeCell*1.5)));
		plantsPlay.add(new CardCactus(heigth, (int)(sizeCell*1.5)));
		plantsPlay.add(new CardBlover(heigth, (int)(sizeCell*1.5)));
		plantsPlay.add(new CardSplitPea(heigth, (int)(sizeCell*1.5)));
		plantsPlay.add(new CardPumpkin(heigth, (int)(sizeCell*1.5)));
		plantsPlay.add(new CardMagnetShroom(heigth, (int)(sizeCell*1.5),true));
		
		//roof
		plantsPlay.add(new CardCabbagePult(heigth, (int)(sizeCell*1.5)));
		plantsPlay.add(new CardFlowerPot(heigth, (int)(sizeCell*1.5)));
		plantsPlay.add(new CardKernelPult(heigth, (int)(sizeCell*1.5)));
		plantsPlay.add(new CardCoffeeBean(heigth, (int)(sizeCell*1.5)));
		plantsPlay.add(new CardGarlic(heigth, (int)(sizeCell*1.5)));
		return plantsPlay;
	}
	
	public Level getLevel() {
		return level;
	}
	
	public Lines getLine(int y) {
		return lines[y];
	}

}
