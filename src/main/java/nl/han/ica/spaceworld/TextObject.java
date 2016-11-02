package nl.han.ica.spaceworld;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PConstants;
import processing.core.PGraphics;

/**
 * @author Ralph Niels Wordt gebruikt om een tekst te kunnen afbeelden
 */
public class TextObject extends GameObject {

	private String text;

	public TextObject (String text) {
		this.text = text;
	}

	public void setText (String text) {
		this.text = text;
	}

	@Override
	public void update () {
	}

	@Override
	public void draw (PGraphics g) {
		g.textAlign (PConstants.LEFT, PConstants.TOP);
		g.textSize (50);
		g.text (text, getX (), getY ());
	}
}
