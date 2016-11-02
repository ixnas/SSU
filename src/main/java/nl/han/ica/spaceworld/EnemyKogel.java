package nl.han.ica.spaceworld;

import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;

import java.util.List;

public class EnemyKogel extends Kogel implements ICollidableWithGameObjects {
	Spaceworld world;

	public EnemyKogel (Spaceworld world, Sound kogelSound) {
		super (world, kogelSound, false, "src/main/java/nl/han/ica/spaceworld/media/enemykogel.png");
		this.world = world;
	}

	@Override
	public void gameObjectCollisionOccurred (List <GameObject> collidedGameObjects) {
		for (final GameObject g : collidedGameObjects) {
			if (g instanceof Player) {
				// TODO: speel geluid als je schip raakt en delete groenschip
				// GroenSchip.deleteSchip();
				world.deleteGameObject (this);
			}
		}
	}

}
