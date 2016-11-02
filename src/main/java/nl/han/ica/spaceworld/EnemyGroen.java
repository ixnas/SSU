package nl.han.ica.spaceworld;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

/**
 * @author Ivo Reumkens & Sjoerd Scheffers Een GroenSchip is een type vijand dat
 *         naar de speler toe beweegt
 */
public class EnemyGroen extends Enemy {

	/**
	 * Constructor
	 * 
	 * @param world
	 *            Referentie naar de wereld
	 */
	public EnemyGroen (Spaceworld world) {
		super (new Sprite ("src/main/java/nl/han/ica/spaceworld/media/groenschip.png"));
		this.world = world;
		health = 20;
		setySpeed (1);
	}

}
