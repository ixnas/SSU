package nl.han.ica.spaceworld;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.UserInput.IMouseInput;
import processing.core.PGraphics;

public abstract class Knop extends GameObject implements IMouseInput {
	
	protected Spaceworld world;
	protected int width;
	protected int height;
	protected int x;
	protected int y;
	protected String tekst = "Knop";
	
	public Knop(Spaceworld world, int width, int height, int x, int y, String tekst) {
		this.world = world;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.tekst = tekst;
		
		setWidth(width);
		setHeight(height);
	}
	
	private boolean muisOverKnop(int mouseX, int mouseY) {
		if (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height) {
			return true;
		} else {
			return false;
		}
	}
	
	protected void doeKnopActie() {
	}
	
	@Override
	public void mouseClicked(int x, int y, int button) {
		if (muisOverKnop(x, y) && button == world.LEFT) {
			doeKnopActie();
		}
	}

	@Override
	public void update() {
	}

	@Override
	public void draw(PGraphics g) {		
		// KNOP
		g.rectMode(world.CORNER);
		//world.noStroke ();
		//world.fill (0, 0, 0, 50);
		g.fill(255, 0, 0, 100);
		g.rect(x, y, width, height, 10, 10, 10, 10);
		// TEKST
		g.fill(255);
		g.textSize(24);
		g.textAlign(g.CENTER, g.CENTER);
		g.text(tekst, x + width/2, y + height/2);
	}
	
}
