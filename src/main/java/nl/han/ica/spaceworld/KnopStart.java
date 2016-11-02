package nl.han.ica.spaceworld;

import processing.core.PGraphics;

public class KnopStart extends Knop {

	public KnopStart(Spaceworld world, int width, int height, int x, int y, String tekst) {
		super(world, width, height, x, y, tekst);
	}
	
	public void doeKnopActie() {
		System.out.println("Start Game!");
		StartGame game = new StartGame(world);
	}

}
