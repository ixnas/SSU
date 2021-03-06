package nl.han.ica.spaceworld;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;

/**
 * @author Ralph Niels Bel-klasse
 */
public class Kogel extends SpriteObject {

	private final Spaceworld world;
	private final int kogelWidth = 8;
	private final int kogelHeight = 30;

	/**
	 * Constructor
	 * 
	 * @param bubbleSize
	 *            Afmeting van de bel
	 * @param world
	 *            Referentie naar de wereld
	 * @param popSound
	 *            Geluid dat moet klinken als de bel knapt
	 */
	public Kogel (Spaceworld world, Sound kogelSound, boolean speler, String pad) {
		super (new Sprite (pad));
		this.world = world;
		if (speler) {
			setySpeed (-6);
		} else {
			setySpeed (6);
		}
		/*
		 * De volgende regels zijn in een zelfgekend object nodig om
		 * collisiondetectie mogelijk te maken.
		 */
		setHeight (kogelHeight);
		setWidth (kogelWidth);

		kogelSound.rewind ();
		kogelSound.play ();
	}

	@Override
	public void update () {
		if (getY () < -kogelHeight || getY () > world.getHeight ()) {
			world.deleteGameObject (this);
		}
	}

}
