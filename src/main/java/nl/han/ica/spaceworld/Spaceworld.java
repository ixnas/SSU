package nl.han.ica.spaceworld;

import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import processing.core.PApplet;

/**
 * @author Ivo Reumkens en Sjoerd Scheffers
 */
@SuppressWarnings("serial")
public class Spaceworld extends GameEngine {

	private Sound backgroundSound;
	public Sound kogelSound, playerHitSound, enemyHitSound;
	public GameObject startKnop, scoreBordKnop, helpKnop, afsluitKnop, terugKnop;

	public static void main(String[] args) {
		PApplet.main(new String[] { "nl.han.ica.spaceworld.Spaceworld" });
	}

	/**
	 * In deze methode worden de voor het spel noodzakelijke zaken
	 * geïnitialiseerd
	 */
	@Override
	public void setupGame() {

		int worldWidth = 600;
		int worldHeight = 800;

		createObjects();
		initializeSound();
		createViewWithoutViewport(worldWidth, worldHeight);

	}

	/**
	 * Maak het venster aan met background plaatje
	 */
	private void createViewWithoutViewport(int screenWidth, int screenHeight) {
		View view = new View(screenWidth, screenHeight);
		view.setBackground(loadImage("src/main/java/nl/han/ica/spaceworld/media/bg.jpg"));

		setView(view);
		size(screenWidth, screenHeight);
	}

	/**
	 * Initialiseert geluid
	 */
	private void initializeSound() {
		backgroundSound = new Sound(this, "src/main/java/nl/han/ica/spaceworld/media/main.mp3");
		backgroundSound.loop(-1);
		kogelSound = new Sound(this, "src/main/java/nl/han/ica/spaceworld/media/kogel.mp3");
		playerHitSound = new Sound(this, "src/main/java/nl/han/ica/spaceworld/media/playerhit.mp3");
		enemyHitSound = new Sound(this, "src/main/java/nl/han/ica/spaceworld/media/enemyhit.mp3");
	}

	/**
	 * Maakt de spelobjecten aan
	 */
	private void createObjects() {
		// LOGO
		Logo logo = new Logo();
		addGameObject(logo, 0, 50);
		// KNOPPEN
		startKnop = new KnopStart(this, 300, 100, 150, 300, "Start Spel");
		addGameObject(startKnop, 150, 300);

		helpKnop = new KnopHelp(this, 300, 100, 150, 450, "Help");
		addGameObject(helpKnop, 150, 450);

		afsluitKnop = new KnopAfsluiten(this, 300, 100, 150, 600, "Afsluiten");
		addGameObject(afsluitKnop, 150, 600);
	}

	@Override
	public void update() {

	}

}
