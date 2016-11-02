package nl.han.ica.spaceworld;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;

public abstract class Knop extends GameObject {
	
	protected Spaceworld world;
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected String tekst = "Knop";
	
	public Knop(Spaceworld world, int width, int height, int x, int y, String tekst) {
		this.world = world;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.tekst = tekst;
	}
	
	private void tekenKnop() {
		// KNOP
		world.rectMode (world.CORNER);
		world.noStroke ();
		world.fill (0, 0, 0, 50);
		world.rect (x+10, y+10, width, height);
		// TEKST
		world.textSize (24);
		world.textAlign (world.CENTER, world.CENTER);
		world.text (tekst, x + width/2, y + height/2);
	}
	
	public boolean muisOverKnop() {
		if (world.mouseX > x && world.mouseX < x + width && world.mouseY > y && world.mouseY < y + height) {
			return true;
		} else {
			return false;
		}
	}
	
	protected abstract void doeKnopActie();
	
}
