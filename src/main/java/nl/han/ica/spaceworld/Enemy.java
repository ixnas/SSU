package nl.han.ica.spaceworld;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.*;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

/**
 * @author Ivo Reumkens & Sjoerd Scheffers Een Enemy is een vijand waaruit de
 *         verchillende schepen worden gemaakt
 */
public abstract class Enemy extends SpriteObject implements ICollidableWithGameObjects, IAlarmListener {

	protected Spaceworld world;
	protected int health;
	protected int item;

	/**
	 * Maak een Swordfish aan met een sprite
	 * 
	 * @param sprite
	 *            De sprite die aan dit object gekoppeld moet worden
	 */
	protected Enemy (Sprite sprite) {
		super (sprite);
		startAlarm ();
	}

	/*
	 * WIst de enemy als deze buiten het scherm komt
	 */
	@Override
	public void update () {
		if (getY () > world.getHeight () + getHeight ()) {
			deleteSchip ();
		}
		if (health <= 0) {
			dropItem ();
			world.increaseScore (5);
			world.deleteGameObject (this);
		}
	}

	public void dropItem () {
		if (Math.random () > 0.90) {
			LevensPakket levenspakket = new LevensPakket (world);
			world.addGameObject (levenspakket, getX () + getWidth ()/2-20, getY () + getHeight ()-20);
		}
	}

	public void schiet () {

	}

	@Override
	public void triggerAlarm (String alarmName) {
		if (isVisible ()) {
			System.out.println ("YEY");
			final Kogel k = new EnemyKogel (world, world.kogelSound);
			world.addGameObject (k, getX () + getWidth () / 2, getY () + getHeight () + 1);
			startAlarm ();
		}
	}

	@Override
	public boolean isVisible () {
		if (health <= 0) {
			return false;
		} else {
			return true;
		}
	}

	public void startAlarm () {
		final Alarm alarm = new Alarm ("Enemy schiet", Math.random () * 10);
		alarm.addTarget (this);
		alarm.start ();
	}

	public void deleteSchip () {
		world.deleteGameObject (this);
	}

	@Override
	public void gameObjectCollisionOccurred (List <GameObject> collidedGameObjects) {
		for (final GameObject g : collidedGameObjects) {
			if (g instanceof PlayerKogel) {
				// TODO: speel geluid als je schip raakt en delete groenschip
				// GroenSchip.deleteSchip();
				health = health - 10;
			}
		}
	}

}
