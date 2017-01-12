package elements;

import game.GameOfLife;
import stages.*;

import java.util.ArrayList;

public class Shark extends Fish {

    protected static final int MAX_AGE = 20;
    protected static final int REPRODUCTION_INTERVAL = 4;
    protected static final String DISPLAY = "O";

    /**
     * Maximal time a shark has to live when he has just eaten
     */
    protected static final int EATING_INTERVAL = 5;

    /**
     * Time left for shark to eat before dying
     */
    protected int eatingCountdown;

    /**
     * Defines if the shark has just eaten
     */
    protected boolean hasJustEaten;

    /**
     * Current evolution stage of the shark.
     */
    protected Stage currentStage;

    /**
     * Builds a new shark.
     */
    public Shark(GameOfLife game, int x, int y) {
        super(game, x, y);
        this.eatingCountdown = EATING_INTERVAL;
        this.hasJustEaten = false;
        this.currentStage = StageFactory.getBabyStage();
    }

    public void setStage(Stage stage) {
        this.currentStage = stage;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    public int getReproductionDuration() {
        return REPRODUCTION_INTERVAL;
    }

    @Override
    public void play() {

    }

    @Override
    public String toString() {
        return DISPLAY;
    }

    /**
     * Age the shark, decrease the reproduction cooldown, the countdown eating
     * and change its stage if needed.
     */
    public void age() {
        super.age();
        if (this.hasJustEaten) {
            this.eatingCountdown = EATING_INTERVAL;
            this.hasJustEaten = false;
        } else if (this.eatingCountdown-- <= 0) {
            this.eatingCountdown = 0;
        }
        this.currentStage.changeStageIfNeeded(this);
    }

    /**
     * Returns the Pilchards nearby the shark.
     * @return ArrayList<Pilchard> The arrayList of the nearby pilchards.
     */
    public ArrayList<Pilchard> getNearbyPilchards() {
        ArrayList<Element> surroundings = this.getSurroundings();
        ArrayList<Pilchard> pilchards = new ArrayList<Pilchard>();
        for (Element surroundingElement : surroundings) {
            if (surroundingElement instanceof Pilchard) {
                pilchards.add((Pilchard) surroundingElement);
            }
        }
        return pilchards;
    }

    @Override
    protected void reproduce() {
        ArrayList<Sea> surroundings = this.getNearbySea();
        int random = (int) Math.random() * surroundings.size();
        Sea s = surroundings.get(random);
        this.game.addShark(new Shark(this.game, s.getX(), s.getY()));
    }

    @Override
    protected void move() {
        Element e = this.currentStage.move();
        // Switching with a pilchard means the shark eats it.
        if (e instanceof Pilchard) {
            this.eat((Pilchard) e);
        }
    }

    /**
     * Check if the shark is healthy,
     * meaning if it is not too old and if it has eaten recently.
     * @return boolean true if the fish can play another cycle
     */
    @Override
    protected boolean isHealthy() {
        return this.age < MAX_AGE && this.eatingCountdown > 0;
    }

    /**
     * Eat the pilchard
     * @param Pilchard the pilchard which has been eaten
     */
    protected void eat(Pilchard pilchard) {
        pilchard.die();
        this.hasEaten = true;
    }
}
