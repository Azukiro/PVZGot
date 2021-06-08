package gameData;

import java.io.Serializable;

public class Timer implements Serializable{
	private long starter;	//temps au départ
	private final long delay; //délai avant éventuelle action

	//Classe "chronomètre"

	public Timer(long d) {
		this.starter = System.currentTimeMillis();
		this.delay = d;
	}

	public boolean enoughDelay() {	//reset du timer si arrivé à terme
		if (enoughDelayWithoutReset()) {
			reset();
			return true;
		}
		return false;
	}

	public boolean enoughDelayWithoutBot() {
		if (enoughDelayWithoutResetAndBot()) {
			reset();
			return true;
		}
		return false;
	}

	public boolean enoughDelayWithoutResetAndBot() {
		long actual=System.currentTimeMillis();
		if(((starter+delay))<=actual) {
			return true;
		}
		return false;
	}

	public boolean enoughDelayWithoutReset() {	//indique si le timer est arrivé à son terme
		long actual=System.currentTimeMillis();
		if((starter+(delay/Bot.getSpeed()))<=actual) {
			return true;
		}
		return false;
	}

	public void reset() {		//reset indépendant du timer
		starter=System.currentTimeMillis();
	}

	public long chronoSecond() {	//temps du timer en secondes
		return chronomill()/1000;
	}
	public long chronomill() {	//temps du timer en millisecondes
		return (System.currentTimeMillis()-starter);
	}

	public long getDelay() {
		return delay;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "je commence � "+starter+" et est un d�lais de "+delay+" est maintenant il est "+System.currentTimeMillis();
	}


}
