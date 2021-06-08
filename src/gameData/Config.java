package gameData;

public class Config {
	private int speed;
	private int nbPlants;
	private WaveConfig[] waves;
	
	//Fichier de configuration xml, getter et setter pour tous les champs et constructeur par défaut
	
	public Config() {}

	public int getSpeed() {
		return speed;
	}

	public int getNbPlants() {
		return nbPlants;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setNbPlants(int nbPlants) {
		this.nbPlants = nbPlants;
	}

	public WaveConfig[] getWaves() {
		return waves;
	}

	public void setWaves(WaveConfig[] waves) {
		this.waves = waves;
	}

}
