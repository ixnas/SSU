package nl.han.ica.spaceworld;

import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;

import java.util.List;

public class PlayerKogel extends Kogel implements ICollidableWithGameObjects {
	Spaceworld world;

	public PlayerKogel (Spaceworld world, Sound kogelSound) {
		super (world, kogelSound, true, "src/main/java/nl/han/ica/spaceworld/media/spelerkogel.png");
		this.world = world;
	}

	@Override
	public void gameObjectCollisionOccurred (List <GameObject> collidedGameObjects) {
		for (final GameObject g : collidedGameObjects) {
			if (g instanceof Enemy) {
				// TODO: speel geluid als je schip raakt en delete groenschip
				// GroenSchip.deleteSchip();
				world.deleteGameObject (this);
			}
		}
	}

}
