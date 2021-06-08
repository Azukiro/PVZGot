package gameData;

public class Bot {
	
	//mode test, augmente la vitesse du jeu
	
	private static int speed=1;
	private boolean active;

	public Bot() {
		speed = 1;
		active = false;
	}

	public static boolean isActive() {
		if (Bot.getSpeed()!=1) {
			return true;
		}
		return false;
	}
	
	public static void setSpeed(int speed) {
		Bot.speed = speed;
	}
	
	public static int getSpeed() {
		return speed;
	}

}
