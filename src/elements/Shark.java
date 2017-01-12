package elements;

import game.GameOfLife;
import stages.*;

import java.util.ArrayList;

public class Shark extends Fish {

    protected static final int MAX_AGE = 45;
    protected static final int REPRODUCTION_INTERVAL = 6;
    /**
     * Maximal time a shark has to live when he has just eaten
     */
    protected static final int EATING_INTERVAL = 5;
    protected static final String DISPLAY = "S";


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
        this.reproductionCountdown = REPRODUCTION_INTERVAL;
        this.eatingCountdown = EATING_INTERVAL;
        this.hasJustEaten = false;
        this.currentStage = StageFactory.getAdultStage();
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

    protected void ageReproduction() {
        if (this.currentStage instanceof AdultStage) {
            super.ageReproduction();
        }
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
    public void die() {
        this.game.getSharks().remove(this);
        this.game.setElement(this.x, this.y, new Sea(this.game, this.x, this.y));
    }

    @Override
    protected void reproduce() {
        ArrayList<Sea> surroundings = this.getNearbySea();
        int random = (int) Math.random() * surroundings.size();
        Sea s = surroundings.get(random);
        this.game.addShark(new Shark(this.game, s.getX(), s.getY()));
        this.hasJustReproduced = true;
    }

    @Override
    protected void move() {
        Element e = this.currentStage.move(this);
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
        return this.currentAge < MAX_AGE && this.eatingCountdown > 0;
    }

    /**
     * Eat the pilchard
     * @param Pilchard the pilchard which has been eaten
     */
    protected void eat(Pilchard pilchard) {
        pilchard.die();
        this.hasJustEaten = true;
    }
}
