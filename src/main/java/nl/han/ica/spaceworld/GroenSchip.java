package nl.han.ica.spaceworld;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

import java.util.List;

/**
 * @author Ivo Reumkens & Sjoerd Scheffers
 * Een GroenSchip is een type vijand dat naar de speler
 * toe beweegt
 */
public class GroenSchip extends SpriteObject implements ICollidableWithGameObjects {

    private Spaceworld world;
    private int health;

    /**
     * Constructor
     * @param world Referentie naar de wereld
     */
    public GroenSchip(Spaceworld world) {
        this (new Sprite ("src/main/java/nl/han/ica/spaceworld/media/groenschip.png"));
        this.world = world;
        health = 20;
    }

    /**
     * Maak een Swordfish aan met een sprite
     * @param sprite De sprite die aan dit object gekoppeld moet worden
     */
    private GroenSchip(Sprite sprite) {
        super(sprite);
        setySpeed(1);
    }

    @Override
    public void update() {
        if (getX()+getWidth()<=0) {
            setX(world.getWidth());
        }
        if (health <= 0) {
        	world.increaseScore (5);
        	world.deleteGameObject (this);
        }

    }
    
    public void deleteSchip() {
    	world.deleteGameObject(this);
    }
    
    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g:collidedGameObjects) {
            if (g instanceof Kogel) {
            	// TODO: speel geluid als je schip raakt en delete groenschip
            	//GroenSchip.deleteSchip();
                health = health - 10;
            }
        }
    }
}
