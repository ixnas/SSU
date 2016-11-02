package nl.han.ica.spaceworld;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import processing.core.PConstants;
import java.util.List;

/**
 * @author Ralph Niels De spelerklasse (het paarse visje)
 */
public class Player extends AnimatedSpriteObject implements ICollidableWithGameObjects, IAlarmListener {

	final int size = 50;
	final int speed = 7;
	private final Spaceworld world;
	private int health;
	private boolean kogelKlaar = true;
	private final double kogelsPerSeconde = 1.6;
	private boolean naarlinks, naarrechts, naarboven = false;

	/**
	 * Constructor
	 * 
	 * @param world
	 *            Referentie naar de wereld
	 */
	public Player (Spaceworld world) {
		super (new Sprite ("src/main/java/nl/han/ica/spaceworld/media/spelerschip.png"), 3);
		this.world = world;
		setCurrentFrameIndex (2);
		setFriction (0.30f);
		health = 100;
		startAlarm ();
	}

	@Override
	public void update () {
		if (getX () <= 0) {
			setxSpeed (0);
			setX (0);
		}
		if (getX () >= world.getWidth () - size) {
			setxSpeed (0);
			setX (world.getWidth () - size);
		}
		if (getHealth () <= 0) {
			health = 0;
			world.deleteGameObject (this);
		}
		if (getHealth () > 100) {
			health = 100;
		}
		if (naarlinks) {
			setDirectionSpeed (270, speed);
			setCurrentFrameIndex (0);
		} else if (naarrechts) {
			setDirectionSpeed (90, speed);
			setCurrentFrameIndex (1);
		} else if (naarboven) {
			setCurrentFrameIndex (2);
			if (kogelKlaar) {
				final Kogel k = new PlayerKogel (world, world.kogelSound);
				world.addGameObject (k, getX () + getWidth () / 2, getY () - 30);
				kogelKlaar = false;
			}
		}
	}

	@Override
	public void keyPressed (int keyCode, char key) {
		if (keyCode == PConstants.LEFT) {
			naarlinks = true;
		}
		if (keyCode == PConstants.UP) {
			// schiet
			naarboven = true;
		}
		if (keyCode == PConstants.RIGHT) {
			naarrechts = true;
		}
		if (key == ' ') {
			// spaie - terug
		}
	}

	@Override
	public void keyReleased (int keyCode, char key) {
		if (keyCode == PConstants.LEFT) {
			naarlinks = false;
		} else if (keyCode == PConstants.RIGHT) {
			naarrechts = false;
		} else if (keyCode == PConstants.UP) {
			naarboven = false;
		}
	}

	public int getHealth () {
		return health;
	}

	@Override
	public void gameObjectCollisionOccurred (List <GameObject> collidedGameObjects) {
		for (final GameObject g : collidedGameObjects) {
			if (g instanceof Enemy) {
				health = health - 50;
				world.deleteGameObject (g);
			} else if (g instanceof Kogel) {
				health = health - 10;
				world.deleteGameObject (g);
			} else if (g instanceof LevensPakket) {
				health = health + 40;
			}
		}
	}
	
	private void startAlarm () {
		final double alarmTijd = 1 / kogelsPerSeconde;
		final Alarm alarm = new Alarm ("Kogel klaar", alarmTijd);
		alarm.addTarget (this);
		alarm.start ();
	}

	@Override
	public void triggerAlarm (String alarmName) {
		kogelKlaar = true;
		startAlarm ();
	}
}
