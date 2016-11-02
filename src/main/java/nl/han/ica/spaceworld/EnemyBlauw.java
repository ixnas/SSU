package nl.han.ica.spaceworld;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

/**
 * @author Ivo Reumkens & Sjoerd Scheffers Een GroenSchip is een type vijand dat
 *         naar de speler toe beweegt
 */
public class EnemyBlauw extends Enemy {

	/**
	 * Constructor
	 * 
	 * @param world
	 *            Referentie naar de wereld
	 */
	public EnemyBlauw (Spaceworld world) {
		super (new Sprite ("src/main/java/nl/han/ica/spaceworld/media/blauwschip.png"));
		this.world = world;
		health = 30;
		setySpeed (2);
	}

}
