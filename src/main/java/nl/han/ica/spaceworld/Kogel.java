package nl.han.ica.spaceworld;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import processing.core.PGraphics;

import java.util.List;

/**
 * @author Ralph Niels
 * Bel-klasse
 */
public class Kogel extends SpriteObject implements ICollidableWithGameObjects{

    private final Sound kogelSound;
    private Spaceworld world;
    private int kogelWidth = 8;
    private int kogelHeight = 30;

    /**
     * Constructor
     * @param bubbleSize Afmeting van de bel
     * @param world Referentie naar de wereld
     * @param popSound Geluid dat moet klinken als de bel knapt
     */
    public Kogel(Spaceworld world, Sound kogelSound, boolean speler) {
    	super(new Sprite("src/main/java/nl/han/ica/spaceworld/media/kogel.png"));
        this.world = world;
        this.kogelSound = kogelSound;
        if (speler) {
        	setySpeed(-6);
        } else {
        	setySpeed(6);
        }
        /* De volgende regels zijn in een zelfgekend object nodig
            om collisiondetectie mogelijk te maken.
         */
        setHeight(kogelHeight);
        setWidth(kogelWidth);
        
        kogelSound.rewind();
        kogelSound.play();
    }

    @Override
    public void update() {
        if (getY() <= -kogelHeight) {
            world.deleteGameObject(this);
        }
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g:collidedGameObjects) {
            if (g instanceof Enemy) {
            	// TODO: speel geluid als je schip raakt en delete groenschip
            	//GroenSchip.deleteSchip();
                world.deleteGameObject(this);
            }
        }
    }
}
