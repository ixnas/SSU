package nl.han.ica.spaceworld;

import processing.core.PGraphics;

public class KnopAfsluiten extends Knop {

	public KnopAfsluiten(Spaceworld world, int width, int height, int x, int y, String tekst) {
		super(world, width, height, x, y, tekst);
	}
	
	public void doeKnopActie() {
		world.exit();
	}

}
