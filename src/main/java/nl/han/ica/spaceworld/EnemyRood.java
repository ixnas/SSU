package nl.han.ica.spaceworld;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

/**
 * @author Ivo Reumkens & Sjoerd Scheffers Een GroenSchip is een type vijand dat
 *         naar de speler toe beweegt
 */
public class EnemyRood extends Enemy {

	/**
	 * Constructor
	 * 
	 * @param world
	 *            Referentie naar de wereld
	 */
	public EnemyRood (Spaceworld world) {
		super (new Sprite ("src/main/java/nl/han/ica/spaceworld/media/roodschip.png"));
		this.world = world;
		health = 40;
		setySpeed (1);
	}

}
