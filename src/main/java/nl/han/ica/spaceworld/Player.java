package nl.han.ica.spaceworld;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import processing.core.PVector;

import java.util.List;

/**
 * @author Ralph Niels
 * De spelerklasse (het paarse visje)
 */
public class Player extends AnimatedSpriteObject implements ICollidableWithGameObjects, IAlarmListener {

    final int size=50;
    final int speed = 7;
    private final Spaceworld world;
    private int health;
    private boolean kogelKlaar = true;
    private double kogelsPerSeconde = 1.6;
    private boolean naarlinks = false;
    private boolean naarrechts = false;
    private boolean naarboven = false;

    /**
     * Constructor
     * @param world Referentie naar de wereld
     */
    public Player(Spaceworld world) {
        super(new Sprite("src/main/java/nl/han/ica/spaceworld/media/player.png"),3);
        this.world=world;
        setCurrentFrameIndex(2);
        setFriction(0.30f);
        health = 100;
        startAlarm();
    }

    @Override
    public void update() {
        if (getX()<=0) {
            setxSpeed(0);
            setX(0);
        }
        if (getX()>=world.getWidth() - size) {
            setxSpeed(0);
            setX(world.getWidth() - size);
        }
        if (getHealth () <= 0) {
        	world.deleteGameObject (this);
        }
        if (naarlinks) {
            setDirectionSpeed(270, speed);
            setCurrentFrameIndex(0);
        } else if (naarrechts) {
            setDirectionSpeed(90, speed);
            setCurrentFrameIndex(1);
        } else if (naarboven) {
        	setCurrentFrameIndex(2);
        	if (kogelKlaar) {
	        	Kogel k = new Kogel(world, world.kogelSound, true);
	        	world.addGameObject(k, getX()+getWidth()/2, getY()-30);
	        	kogelKlaar = false;
        	}        	
        }
    }
    @Override
    public void keyPressed(int keyCode, char key) {
        if (keyCode == world.LEFT) {
        	naarlinks = true;
        }
        if (keyCode == world.UP) {
            // schiet
        	naarboven = true;
        }
        if (keyCode == world.RIGHT) {
        	naarrechts = true;
        }
        if (key == ' ') {
            // spaie - terug
        }
    }
    
    public void keyReleased (int keyCode, char key) {
    	if (keyCode == world.LEFT) {
    		naarlinks = false;
    	} else if (keyCode == world.RIGHT) {
    		naarrechts = false;
    	} else if (keyCode == world.UP) {
    		naarboven = false;
    	}
    }
    
    public int getHealth () {
    	return health;
    }
    
    public void gameObjectCollisionOccurred (List <GameObject> collidedGameObjects) {
    	for (GameObject g:collidedGameObjects) {
    		if (g instanceof Enemy) {
    			health = health - 50;
    			world.deleteGameObject (g);
    		} else if (g instanceof Kogel) {
    			health = health - 10;
    			world.deleteGameObject (g);
    		}
    	}
    }
    
    private void startAlarm() {
    	double alarmTijd = 1 / kogelsPerSeconde;
    	Alarm alarm = new Alarm("Kogel klaar", alarmTijd);
    	alarm.addTarget(this);
    	alarm.start();
    }

	@Override
	public void triggerAlarm(String alarmName) {
		kogelKlaar = true;
		startAlarm();
	}
}
