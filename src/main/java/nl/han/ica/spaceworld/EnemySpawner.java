package nl.han.ica.spaceworld;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;

import java.util.Random;

/**
 * @author Ralph Niels
 * Klasse die Bubbles aanmaakt met configureerbare
 * snelheid.
 */
public class EnemySpawner implements IAlarmListener {

    private float enemysPerSecond;
    private Random random;
    private Spaceworld world;

    /** Constructor
     * @param world Referentie naar de wereld
     * @param popSound Geluid dat moet klinken als een bel knapt
     * @param enemysPerSecond Aantal bellen dat per seconden gemaakt moet worden
     */
    public EnemySpawner(Spaceworld world,float enemysPerSecond) {
        this.enemysPerSecond=enemysPerSecond;
        this.world=world;
        random=new Random();
        startAlarm();
    }

    private void startAlarm() {
        Alarm alarm = new Alarm("New enemy",1/enemysPerSecond);
        alarm.addTarget(this);
        alarm.start();
    }

    @Override
    public void triggerAlarm(String alarmName) {
    	// type enemy bepalen
    	int enemyType = random.nextInt(100) + 0;
    	
    	// enemy aanmaken
    	if (enemyType >= 0 && enemyType <= 60) {
    		EnemyGroen groenSchip = new EnemyGroen(world);
    		world.addGameObject(groenSchip, random.nextInt(world.getWidth() - 36) + 0,-50);
    	}
    	else if (enemyType > 60 && enemyType <= 80) {
    		EnemyBlauw blauwSchip = new EnemyBlauw(world);
    		world.addGameObject(blauwSchip, random.nextInt(world.getWidth() - 36) + 0,-50);
    	}
    	else if (enemyType > 80 && enemyType <= 100) {
    		EnemyRood roodSchip = new EnemyRood(world);
    		world.addGameObject(roodSchip, random.nextInt(world.getWidth() - 36) + 0,-50);
    	}
    	
        // timer weer starten
        startAlarm();
    }

}
