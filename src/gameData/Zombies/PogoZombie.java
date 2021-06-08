package gameData.Zombies;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import gameData.Coordinates;
import gameData.LivingBeing;

public class PogoZombie extends MetallicDefensesZombies {
	private boolean canJump = false;

	public PogoZombie(Coordinates coordinates) {
		super(100, 2, 2000, coordinates, 86, 10, 3);
	}

	@Override
	public int attack(LivingBeing l) {
		if (l.getHeight() < 1 && haveDefense()) {// Si le pogo a toujours sa defense pour sauter et que la plante n'as
													// pas une hauteur supérieur donc ne le bloque pas il peut sauter
			canJump = true;
			return 0;
		} else {
			canJump = false;
		}

		return super.attack(l);
	}

	@Override
	public void draw(Graphics2D graphics, int height, ArrayList<Image> imagesList) {
		if (canJump && !back) {
			canJump = false;
			getCoordinates().addSpeedToX(-((int) (height * 1.5)));
			;
		}
		super.draw(graphics, height, imagesList);
	}

}
