package nl.han.ica.spaceworld;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

public class LevensPakket extends SpriteObject implements ICollidableWithGameObjects {
	
	private Spaceworld world;

	public LevensPakket (Spaceworld world) {
		super (new Sprite ("src/main/java/nl/han/ica/spaceworld/media/levenspakket.png"));
		this.world = world;
		setySpeed (1);
	}
	
	public void update () {
		/*
		if (getY () > world.getHeight () + getHeight ()) {
			world.deleteGameObject (this);
		}
		*/
	}
	
	public void gameObjectCollisionOccurred (List <GameObject> collidedGameObjects) {
		for (final GameObject g : collidedGameObjects) {
			if (g instanceof Player) {
				world.deleteGameObject (this);
			}
		}
	}

}
