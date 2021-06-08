package gameData;

public class WaveConfig {
	private int nbZombies;
	private int spawnRate;
	
	//classe représentant une vague de zombies pour le fichier config.xml
	
	public WaveConfig() {}

	public WaveConfig(int nbZombies, int spawnRate) {
		super();
		this.nbZombies = nbZombies;
		this.spawnRate = spawnRate;
	}



	public int getNbZombies() {
		return nbZombies;
	}

	public void setNbZombies(int nbZombies) {
		this.nbZombies = nbZombies;
	}

	public int getSpawnRate() {
		return spawnRate;
	}

	public void setSpawnRate(int spawnRate) {
		this.spawnRate = spawnRate;
	}
	
	
}
