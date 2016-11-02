package nl.han.ica.spaceworld;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.FilePersistence;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.IPersistence;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import processing.core.PApplet;

/**
 * @author Ivo Reumkens en Sjoerd Scheffers
 */
@SuppressWarnings("serial")
public class Spaceworld extends GameEngine {

    private Sound backgroundSound;
    public Sound kogelSound;
    private TextObject dashboardText;
    private EnemySpawner enemySpawner;
    private int score;
    private IPersistence persistence;
    private Player player;


    public static void main(String[] args) {
        PApplet.main(new String[]{"nl.han.ica.spaceworld.Spaceworld"});
    }

    /**
     * In deze methode worden de voor het spel
     * noodzakelijke zaken geïnitialiseerd
     */
    @Override
    public void setupGame() {

        int worldWidth=600;
        int worldHeight=800;

        initializeSound();
        createDashboard(worldWidth, 200);

        createObjects();
        initializePersistence();
        createEnemySpawner();

        createViewWithoutViewport(worldWidth, worldHeight);

    }

    /**
     * Creeërt de view zonder viewport
     * @param screenWidth Breedte van het scherm
     * @param screenHeight Hoogte van het scherm
     */
    private void createViewWithoutViewport(int screenWidth, int screenHeight) {
        View view = new View(screenWidth,screenHeight);
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
        
    }


    /**
     * Maakt de spelobjecten aan
     */
    private void createObjects() {
        player = new Player(this);
        addGameObject(player, 275, 750);
    }

    /**
     * Maakt de spawner voor de bellen aan
     */
    public void createEnemySpawner() {
    	float enemysPerSecond = (int) score/100 + 1;
        enemySpawner = new EnemySpawner(this, enemysPerSecond);
    }

    /**
     * Maakt het dashboard aan
     * @param dashboardWidth Gewenste breedte van dashboard
     * @param dashboardHeight Gewenste hoogte van dashboard
     */
    private void createDashboard(int dashboardWidth,int dashboardHeight) {
        Dashboard dashboard = new Dashboard(10,5, dashboardWidth, dashboardHeight);
        dashboardText = new TextObject("");
        dashboard.addGameObject(dashboardText);
        addDashboard(dashboard);
    }

    /**
     * Initialiseert de opslag van de bellenteller
     * en laadt indien mogelijk de eerder opgeslagen
     * waarde
     */
    private void initializePersistence() {
        persistence = new FilePersistence("main/java/nl/han/ica/spaceworld/media/score.txt");
        if (persistence.fileExists()) {
            score = Integer.parseInt(persistence.loadDataString());
            refreshDasboardText();
        }
    }
    
    @Override
    public void update() {
    	refreshDasboardText();
    }

    /**
     * Vernieuwt het dashboard
     */
    private void refreshDasboardText() {
        dashboardText.setText("Score: "+ score + "\nHealth: " + player.getHealth ());
    }

    /**
     * Verhoogt de teller voor het aantal
     * geknapte bellen met 1
     */
    public void increaseScore(int aantal) {
        score = score + aantal;
        persistence.saveData(Integer.toString(score));
        refreshDasboardText();
    }
}
