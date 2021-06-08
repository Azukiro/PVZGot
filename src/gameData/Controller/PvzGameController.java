package gameData.Controller;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import gameData.Bot;
import gameData.Button;
import gameData.Config;
import gameData.Level;
import gameData.Timer;
import gameData.Wave;
import gameData.WaveConfig;
import gameData.Cards.*;
import gameData.Zombies.Zombies;
import gameData.ground.*;
import fr.umlv.zen5.*;
import fr.umlv.zen5.Event.Action;
public class PvzGameController {

	static void GameLoop(ApplicationContext context) throws IOException, ClassNotFoundException   {
		
		ScreenInfo screenInfo = context.getScreenInfo();
		float width = screenInfo.getWidth();
		float height = screenInfo.getHeight();
		int sizeCell=0;	//taille des cellules du plateau de jeu

		FileInputStream fis = new FileInputStream("config.xml");	//lecture du fichier de configuration XML
	    XMLDecoder decoder = new XMLDecoder(fis);
	    Config parametersConfig = (Config) decoder.readObject();
	    decoder.close();
	    fis.close();

		Level l1 = new Level();	//initialisation du niveau avec les différentes vagues
		int waveCount=0;
		for (WaveConfig waveConfig : parametersConfig.getWaves()) {
			waveCount+=1;
			if (waveCount==parametersConfig.getWaves().length) {
				l1.addWave(new Wave(waveConfig.getNbZombies(), waveConfig.getSpawnRate()));
			}
			else {
				l1.addWave(new Wave(waveConfig.getNbZombies(), waveConfig.getSpawnRate(), 3));
			}

		}

		PvzGameView view= new PvzGameView(height,width);	//initialisation du gameview
		//Chargement des image
				ArrayList<Image> listiImages = new ArrayList<Image>();	//liste d'images
				try {
					listiImages.add(ImageIO.read(new File("./Pictures/Peashooter.png")));
					listiImages.add(ImageIO.read(new File("./Pictures/WallNut.png")));
					listiImages.add(ImageIO.read(new File("./Pictures/CherryBomb.png")));
					listiImages.add(ImageIO.read(new File("./Pictures/BasicZombie.png"))); //3
					listiImages.add(ImageIO.read(new File("./Pictures/ConeHeadZombie.png")));
					listiImages.add(ImageIO.read(new File("./Pictures/FlagZombie.png")));
					listiImages.add(ImageIO.read(new File("./Pictures/SunFlower.png")));
					listiImages.add(ImageIO.read(new File("./Pictures/LawnMower.png")));
					listiImages.add(ImageIO.read(new File("./Pictures/Chomper.png")));
					listiImages.add(ImageIO.read(new File("./Pictures/ChomperEat.png")));
					listiImages.add(ImageIO.read(new File("./Pictures/Repeater.png")));//10
					listiImages.add(ImageIO.read(new File("./Pictures/PotatoMine.png")));
					listiImages.add(ImageIO.read(new File("./Pictures/potatoMineArmed.png")));
					listiImages.add(ImageIO.read(new File("./Pictures/Tomb.png")));
					listiImages.add(ImageIO.read(new File("./Pictures/Sun.png")));
					listiImages.add(ImageIO.read(new File("./Pictures/banner.png")));
					listiImages.add(ImageIO.read(new File("./Pictures/Polevaulterzombie.png")));
					listiImages.add(ImageIO.read(new File("./Pictures/PolevaulterzombieAfterJump.png")));//17
					listiImages.add(ImageIO.read(new File("./Pictures/PuffShroom.png")));
					listiImages.add(ImageIO.read(new File("./Pictures/FumeShroom.png")));
					listiImages.add(ImageIO.read(new File("./Pictures/ScaredyShroom.png")));
					listiImages.add(ImageIO.read(new File("./Pictures/ScaredyShroomHidden.png")));//21
					listiImages.add(ImageIO.read(new File("./Pictures/SnowPea.png")));
					listiImages.add(ImageIO.read(new File("./Pictures/BucketHeadZombie.png"))); //23
					listiImages.add(ImageIO.read(new File("./Pictures/NewsPaperZombie.png")));
					listiImages.add(ImageIO.read(new File("./Pictures/AngryNewsPaperZombie.png")));
					listiImages.add(ImageIO.read(new File("./Pictures/ScreenDoorZombie.png")));
					listiImages.add(ImageIO.read(new File("./Pictures/FootballZombie.png"))); //27
					listiImages.add(ImageIO.read(new File("./Pictures/SunShroom.png"))); //28
					listiImages.add(ImageIO.read(new File("./Pictures/DoomShroom.png"))); //29
					listiImages.add(ImageIO.read(new File("./Pictures/cratere.png"))); //30
					listiImages.add(ImageIO.read(new File("./Pictures/GraveBuster.png"))); //31
					listiImages.add(ImageIO.read(new File("./Pictures/IceShroom.png"))); //32
					listiImages.add(ImageIO.read(new File("./Pictures/DancingZombie.png"))); //33
					listiImages.add(ImageIO.read(new File("./Pictures/BackupDancer.png"))); //34
					listiImages.add(ImageIO.read(new File("./Pictures/LilyPad.png"))); //35
					listiImages.add(ImageIO.read(new File("./Pictures/HypnoShroom.png"))); //36
					listiImages.add(ImageIO.read(new File("./Pictures/Squash.png"))); //37
					listiImages.add(ImageIO.read(new File("./Pictures/TallNut.png"))); //38
					listiImages.add(ImageIO.read(new File("./Pictures/Jalapeno.png"))); //39
					listiImages.add(ImageIO.read(new File("./Pictures/ThreePeater.png"))); //40
					listiImages.add(ImageIO.read(new File("./Pictures/SpikeWeed.png"))); //41
					listiImages.add(ImageIO.read(new File("./Pictures/SeaShroom.png"))); //42
					listiImages.add(ImageIO.read(new File("./Pictures/TorchWood.png"))); //43
					listiImages.add(ImageIO.read(new File("./Pictures/BasicDuckyTubeZombie.png"))); //44
					listiImages.add(ImageIO.read(new File("./Pictures/ConeHeadDuckyTubeZombie.png"))); //45
					listiImages.add(ImageIO.read(new File("./Pictures/BucketHeadDuckyTubeZombie.png"))); //46
					listiImages.add(ImageIO.read(new File("./Pictures/FlagDuckyTubeZombie.png"))); //47
					listiImages.add(ImageIO.read(new File("./Pictures/DolphinRiderZombie.png"))); //48
					listiImages.add(ImageIO.read(new File("./Pictures/RiderZombie.png"))); //49
					listiImages.add(ImageIO.read(new File("./Pictures/SnorkelZombieSubmerged.png"))); //50
					listiImages.add(ImageIO.read(new File("./Pictures/SnorkelZombieEmerged.png"))); //51
					listiImages.add(ImageIO.read(new File("./Pictures/ZamboniZombie.png"))); //52
					listiImages.add(ImageIO.read(new File("./Pictures/IceCell.png"))); //53
					listiImages.add(ImageIO.read(new File("./Pictures/BobsledTeam.png"))); //54
					listiImages.add(ImageIO.read(new File("./Pictures/BobsledZombie.png"))); //55
					listiImages.add(ImageIO.read(new File("./Pictures/TangleKelp.png"))); //56
					listiImages.add(ImageIO.read(new File("./Pictures/Fog.png"))); //57
					listiImages.add(ImageIO.read(new File("./Pictures/FlowerPot.png"))); //58
					listiImages.add(ImageIO.read(new File("./Pictures/CabbagePult.png"))); //59
					listiImages.add(ImageIO.read(new File("./Pictures/KernelPult.png"))); //60
					listiImages.add(ImageIO.read(new File("./Pictures/CoffeeBean.png"))); //61
					listiImages.add(ImageIO.read(new File("./Pictures/Garlic.png"))); //62
					listiImages.add(ImageIO.read(new File("./Pictures/LadderZombie.png"))); //63
					listiImages.add(ImageIO.read(new File("./Pictures/LadderZombieWithoutLadder.png"))); //64
					listiImages.add(ImageIO.read(new File("./Pictures/Ladder.png"))); //65
					listiImages.add(ImageIO.read(new File("./Pictures/Day.png"))); //66
					listiImages.add(ImageIO.read(new File("./Pictures/footer.jpg"))); //67
					listiImages.add(ImageIO.read(new File("./Pictures/footerNight.jpg"))); //68
					listiImages.add(ImageIO.read(new File("./Pictures/ground.png"))); //69
					listiImages.add(ImageIO.read(new File("./Pictures/FogPool.png"))); //70
					listiImages.add(ImageIO.read(new File("./Pictures/night.png"))); //71
					listiImages.add(ImageIO.read(new File("./Pictures/Pool.png"))); //72
					listiImages.add(ImageIO.read(new File("./Pictures/Roof.png"))); //73
					listiImages.add(ImageIO.read(new File("./Pictures/arrow.png"))); //74
					listiImages.add(ImageIO.read(new File("./Pictures/BungeeZombie.png"))); //75
					listiImages.add(ImageIO.read(new File("./Pictures/SplitPea.png"))); //76
					listiImages.add(ImageIO.read(new File("./Pictures/Blover.png"))); //77
					listiImages.add(ImageIO.read(new File("./Pictures/Plantern.png"))); //78
					listiImages.add(ImageIO.read(new File("./Pictures/Pumpkin.png"))); //79
					listiImages.add(ImageIO.read(new File("./Pictures/MagnetShroom.png"))); //80
					listiImages.add(ImageIO.read(new File("./Pictures/BalloonZombie.png"))); //81
					listiImages.add(ImageIO.read(new File("./Pictures/Cactus.png"))); //82
					listiImages.add(ImageIO.read(new File("./Pictures/CactusUp.png"))); //83
					listiImages.add(ImageIO.read(new File("./Pictures/pause.png"))); //84
					listiImages.add(ImageIO.read(new File("./Pictures/button.png"))); //85
					listiImages.add(ImageIO.read(new File("./Pictures/PogoZombie.png"))); //86
					listiImages.add(ImageIO.read(new File("./Pictures/Gargantuar.png"))); //87
					listiImages.add(ImageIO.read(new File("./Pictures/Cards/PlantsCard.png")));//88
					listiImages.add(ImageIO.read(new File("./Pictures/Cards/PlantsCardClose.png")));//89
					listiImages.add(ImageIO.read(new File("./Pictures/WaterPro.png")));//90
					listiImages.add(ImageIO.read(new File("./Pictures/MushRoomPro.png")));//91
					listiImages.add(ImageIO.read(new File("./Pictures/IceArrow.png")));//92
					listiImages.add(ImageIO.read(new File("./Pictures/Digger.png")));//93
					listiImages.add(ImageIO.read(new File("./Pictures/DiggerUnderground.png")));//94
				}catch (IOException e) {
					e.printStackTrace();
				}
		Ground root=selectGround(width,height,sizeCell,l1, view,  context,listiImages);	//sélection du plateau par le joueur
		int nbLines=root.getNbLines();
		int nbColums=root.getNbColumns();
		sizeCell=root.getSizeCell();

		int heigthGround=sizeCell*nbLines;	//dimensions du plateau
		int widthGround=sizeCell*nbColums;
		//Cr�ation niveau de jeu
		Cards[] cards=start(root,view,context,sizeCell,listiImages,(int)width,(int)height,parametersConfig);	//sélection des plantes par le joueur

		//Obtention des cartes � jouer
		for (Cards cards2 : cards) {
			root.addCard(cards2);

		}
		root.addShovel();	//ajout de la pelle pour retirer une plante
		
		Point2D.Float cursor;//Pour la souris
		Cards selectionCard =null;
		Timer tesTimer=new Timer(80);
		view.draw(context, root,heigthGround,widthGround,listiImages,l1);	//dessine le plateau

		Timer clickTimer=new Timer(80);
		view.drawEnvironnement(context,root,sizeCell*nbLines,sizeCell*nbColums,(int)width-sizeCell*nbColums,(int)width,(int)height,listiImages);	
		//dessine l'arrière plan et autres

		//Boucle Pricipale
		while (true) {

			if (!root.getLevel().play(root, sizeCell)) { // progression du niveau et d�tection de victoire
				System.out.println("Vous avez �liminez tous les zombies! Bravo!");
				view.winGame(context, width, height);
				Timer endTimer = new Timer(5000);
				while (!endTimer.enoughDelayWithoutReset()) {
				}
				context.exit(0);
				break;
			}

			root.linesAttack();	//attaque

			if (root.zombiesWin()) { // d�tection de d�faite
				System.out.println("Les zombies ont envahis la maison! Vous avez perdu!");
				view.looseGame(context, width, height);
				Timer endTimer = new Timer(5000);
				while (!endTimer.enoughDelayWithoutReset()) {

				}
				context.exit(0);
				break;
			}

			if (tesTimer.enoughDelay()) {	//fréquence d'affichage du plateau
				view.draw(context, root, heigthGround, widthGround, listiImages, root.getLevel());

			}

			if (!clickTimer.enoughDelayWithoutReset()) {
				continue;
			}

			Event event = context.pollOrWaitEvent(80); // modifier pour avoir un affichage fluide
			if (event == null) { // no event
				continue;
			}

			Action action = event.getAction();
			if (action == Action.KEY_PRESSED) {
				if (event.getKey() == KeyboardKey.T) {	//mode test sur la touche T
					if (Bot.getSpeed()==1) {
						Bot.setSpeed(parametersConfig.getSpeed());
						System.out.println("Mode test active");
					}

					else {
						Bot.setSpeed(1);
						System.out.println("Mode test desactive");
					}
				}

				else if (event.getKey()==KeyboardKey.UNDEFINED) {	//menu pause ave ECHAP
					System.out.println("Partie en pause");

					view.drawPause(context,listiImages,0,0,(int) width,(int) height);
					while(true) {	//boucle pause
						ArrayList<Button> buttons = new ArrayList<>();	//liste des boutons du menu à afficher
						buttons.add(new Button(new Rectangle((int) width/3, (buttons.size()*(int)height/15)+(int)height/15,(int)width/3, (int)height/15), "Reprendre"));
						buttons.add(new Button(new Rectangle((int) width/3, (int)height/15+((buttons.size()*(int)height/15)+((int)height/15)*buttons.size()+1),(int)width/3, (int)height/15), "Sauvegarder"));
						buttons.add(new Button(new Rectangle((int) width/3, (int)height/15+((buttons.size()*(int)height/15)+((int)height/15)*buttons.size()+1),(int)width/3, (int)height/15), "Charger sauvegarde"));
						buttons.add(new Button(new Rectangle((int) width/3, (int)height/15+((buttons.size()*(int)height/15)+((int)height/15)*buttons.size()+1),(int)width/3, (int)height/15), "Recommencer"));
						buttons.add(new Button(new Rectangle((int) width/3, (int)height/15+((buttons.size()*(int)height/15)+((int)height/15)*buttons.size()+1),(int)width/3, (int)height/15), "Quitter"));

						for (Button button : buttons) {	//affichage des boutons
							button.draw(view, context, listiImages);
						}

						Event event2 = context.pollOrWaitEvent(80); // modifier pour avoir un affichage fluide
						if (event2 == null) { // no event
							continue;
						}

						Action action2 = event2.getAction();

						if (action2 != Action.POINTER_DOWN) {
							continue;
						}
						cursor = event2.getLocation();
						String res=null;
						for (Button button : buttons) {
							res=button.clicked((int)cursor.getX(), (int)cursor.getY());
							if (res!=null) {
								break;
							}
						}
						if (res!=null) {	//détection du bouton et action à effectuer
							boolean resume=false;
							switch (res) {
							case "Reprendre":	//reprise de la partie
								resume=true;
								break;

							case "Sauvegarder":	//sauvegarde de la partie
								try {
							         FileOutputStream fileOut = new FileOutputStream("save.ser");	
							         ObjectOutputStream out = new ObjectOutputStream(fileOut);
							         out.writeObject(root);
							         out.close();
							         fileOut.close();
							      } catch (IOException i) {
							         i.printStackTrace();
							      }
								System.out.println("Sauvegarde effectuée");
								break;

							case "Charger sauvegarde":	//chargement de la dernière sauvegarde
								try {
							         FileInputStream fileIn = new FileInputStream("save.ser");
							         ObjectInputStream in = new ObjectInputStream(fileIn);
							         root = (Ground) in.readObject();
							         in.close();
							         fileIn.close();
							      } catch (IOException i) {
							         i.printStackTrace();
							      }
								System.out.println("Chargement de la sauvegarde");
								resume=true;
								break;
							case "Recommencer":
								System.out.println("Restart");	//permet au joueur de retenter l'expérience
								PvzGameController.GameLoop(context);
								break;
							case "Quitter":
								System.out.println("Fin");	//sortie du jeu
								context.exit(0);
								return;
							default:
								break;
							}
							if (resume==true) {
								break;
							}
						}

					}
					view.drawEnvironnement(context,root,sizeCell*nbLines,sizeCell*nbColums,(int)width-sizeCell*nbColums,(int)width,(int)height,listiImages);
					System.out.println("Reprise de la partie");	
				}
			}

			if (action != Action.POINTER_DOWN) {
				continue;
			}

			cursor = event.getLocation();
			if(root.clickInSuns((int)cursor.x, (int)cursor.y)) {	//clic soleil et cartes
				continue;
			}

			selectionCard=root.clique((int)cursor.x, (int)cursor.y, selectionCard);
			if(selectionCard!=null) {
				System.out.println("Selection"+selectionCard);
			}


		}

	}

	public static Ground selectGround(float width, float height,int sizeCell,Level level,PvzGameView view,ApplicationContext context,ArrayList<Image> listImages) {
		int widthGround =(int) (width*0.8);
		int heigthGround =(int) (height*0.9);
		int sizeCell5,sizeCell6;
		if(widthGround/10<heigthGround/5) {
			sizeCell5=widthGround/10;
		}else {
			sizeCell5=heigthGround/5;
		}

		if(widthGround/10<heigthGround/6) {
			sizeCell6=widthGround/10;
		}else {
			sizeCell6=heigthGround/6;
		}
		ArrayList<Ground> GroundPlay = new ArrayList<Ground>();	//liste des différents plateaux
		GroundPlay.add(new DayGround(sizeCell5, level));
		GroundPlay.add(new NightGarden(sizeCell5, level));
		GroundPlay.add(new PoolGround(sizeCell6, level));
		GroundPlay.add(new FogPoolGround(sizeCell6, level));
		GroundPlay.add(new RoofGround(sizeCell5, level));
		Point2D.Float cursor;
		while (true) {	//menu de choix du plateau
			view.drawLittleGround(context,GroundPlay,(int) sizeCell5,listImages);	//affichage des plateaux


			Event event = context.pollOrWaitEvent(80); // modifier pour avoir un affichage fluide
			if (event == null) { // no event
				continue;
			}
			Action action = event.getAction();
			if (action == Action.KEY_PRESSED) {

				if(event.getKey()==  KeyboardKey.UNDEFINED){
					context.exit(0);
				}
			}

			if (action != Action.POINTER_DOWN) {
				continue;
			}
			cursor = event.getLocation();
			int startx = (int) (sizeCell5 * 0.3);
			int starty=0;
			for (int i = 0; i < GroundPlay.size(); i++) {
				if(i%3==0) {
					 startx = (int) (sizeCell5 * 0.3);
					 starty+=sizeCell5*2;
				}
				if (cursor.getX() >= startx  && cursor.getX() <= startx + (sizeCell5 * 3)) {
					if (cursor.getY() >= starty && cursor.getY() <= starty+(sizeCell5 * 1.5)){
						return GroundPlay.get(i);	//choix du joueur
					}
				}
				startx+=(sizeCell5*3+(int)sizeCell5*1.5);

			}
		}

	}
	public static Cards[] start(Ground ground,PvzGameView view, ApplicationContext context,int sizeCell,ArrayList<Image> listImages,int width, int height,Config config) throws IOException {
		ArrayList<Cards> plantsPlay = ground.cardsPlaying(config.getNbPlants(),height);//Adpater la taille du coup
		for (Cards cards2 : plantsPlay) {	//cartes des plantes en fonction du plateau sélectionné
			listImages.add(ImageIO.read(new File("./Pictures/Cards/"+cards2.selectPlants().tag()+"Card.png")));
			cards2.setCardImage(listImages.size()-1);
			listImages.add(ImageIO.read(new File("./Pictures/Cards/"+cards2.selectPlants().tag()+"CardClose.png")));
			cards2.setCloseImage(listImages.size()-1);
		}
		int i =0;
		Cards[] result = new Cards[config.getNbPlants()];	//choix du joueur
		Point2D.Float cursor;
		int y =0;
		Timer tesTimer = new Timer(80);
		ground.getLevel().initializeSpawn(ground, width, height);	//zombies à combattre
		Zombies[] zombiesPlaying = new Zombies[ground.getLevel().getZombiesPlaying().size()];
		
		for (int j = 0; j < ground.getLevel().getZombiesPlaying().size(); j++) {
			zombiesPlaying[j]=ground.getLevel().getZombiesPlaying().get(j);
		}
		ground.setZombiesPlaying(zombiesPlaying);
		view.drawSelectCards(context,ground,plantsPlay,sizeCell,result,y,listImages);	//affichage des cartes
		view.drawStartZombies(listImages,context,ground,sizeCell,y); //affichage des zombies
		while (true) {	//choix des cartes 
			if (tesTimer.enoughDelay()) {
				view.drawSelectCards(context,ground,plantsPlay,sizeCell,result,y,listImages);

			}
			Event event = context.pollOrWaitEvent(80); // modifier pour avoir un affichage fluide
			if (event == null) { // no event
				continue;
			}
			Action action = event.getAction();	//défilement des différents en choix avec les touches flèches du haut et du bas
			if (action == Action.KEY_PRESSED) {

				if(event.getKey()== KeyboardKey.DOWN) {
					if(!(y>=((plantsPlay.size()/3-1)*(sizeCell*1))+sizeCell*0.5)) {

						y+=sizeCell;
					}
				}else if(event.getKey()== KeyboardKey.UP){
					if(!(y==0)) {
						y-=sizeCell;
					}
				}else if(event.getKey()==  KeyboardKey.UNDEFINED){
					context.exit(0);
				}
			}

			if (action != Action.POINTER_DOWN) {
				continue;
			}

			for (Cards cards : plantsPlay) {		//sélection des cartes
				cursor = event.getLocation();
				if(i==config.getNbPlants()) {	//retour des cartes sélectionnées
					return result;
				}
				if(cards.clickInCards((int)cursor.getX(), (int)cursor.getY())) {


					if(cards.getSelected()) {
						if(i==0) {

							cards.setSelected(false);
							result[0]=cards;
							System.out.println("Vous avez choisi: "+cards);

							i++;
							continue;
						}

						int max=i;
						boolean increment = true;
						for (int j = 0; j < max; j++) {
							if((result[j].equals(cards))) {
								increment=false;
								break;
							}
						}
						if(increment==true) {

							if(cards.getSelected()) {
								result[i]=cards;
								cards.setSelected(false);
								System.out.println("Vous avez choisi: "+cards);
							}
							i++;
							continue;
						}




					}
					cards.setSelected(true);

				}
			}


		}

	}
	public static void main(String[] args) {	//lancement de l'application
		Application.run(Color.WHITE, t -> {
			try {
				GameLoop(t);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch bcloseImage);Imagelock
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
}
