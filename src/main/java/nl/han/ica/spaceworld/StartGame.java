package nl.han.ica.spaceworld;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.FilePersistence;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.IPersistence;

public class StartGame {
	
	private Spaceworld world;
	private TextObject dashboardText;
    private EnemySpawner enemySpawner;
    private Player player;
    private int score;
    private int highscore;
    private IPersistence persistence;
    
    public StartGame(Spaceworld world) {
    	this.world = world;
    	
    	world.deleteAllGameOBjects();
    	world.deleteAllDashboards();
    	
    	createObjects();
    	createEnemySpawner();	
    	initializePersistence();
    	createDashboard(world.getWidth(), 200);
	}
    
    /**
     * Maakt de spelobjecten aan
     */
    private void createObjects() {
        player = new Player(world);
        world.addGameObject(player, 275, 750);
    }
    
    /**
     * Maakt de spawner voor de enemys aan
     */
    public void createEnemySpawner() {
    	float enemysPerSecond = (int) score/100 + 1;
        enemySpawner = new EnemySpawner(world, enemysPerSecond);
    }
    
    /**
     * Maakt het dashboard aan
     * @param dashboardWidth Gewenste breedte van dashboard
     * @param dashboardHeight Gewenste hoogte van dashboard
     */
    private void createDashboard(int dashboardWidth,int dashboardHeight) {
        Dashboard dashboard = new Dashboard(10, 5, dashboardWidth, dashboardHeight);
        dashboardText = new TextObject("");
        dashboard.addGameObject(dashboardText);
        world.addDashboard(dashboard);
    }
    
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
     * Verhoogt de score
     */
    public void increaseScore(int aantal) {
        score = score + aantal;
        persistence.saveData(Integer.toString(score));
        refreshDasboardText();
    }
    
    /**
     * Initialiseert de opslag van de score en laadt 
     * indien mogelijk de eerder opgeslagen waarde
     */
    private void initializePersistence() {
        persistence = new FilePersistence("main/java/nl/han/ica/spaceworld/media/score.txt");
        if (persistence.fileExists()) {
            score = (Integer.parseInt(persistence.loadDataString()));
        }
    }
    
}
