package nl.han.ica.spaceworld;

public abstract class KnopStart extends Knop {

	public KnopStart(Spaceworld world, int width, int height, int x, int y, String tekst) {
		super(world, width, height, x, y, tekst);
	}
	
	public void doeKnopActie() {
		System.out.println("Start Game!");
		// StartGame.StartGame(world);
	}

}
