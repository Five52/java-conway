package elements;

import game.GameOfLife;

import java.util.ArrayList;

public class Pilchard extends Fish {

    protected static final int MAX_AGE = 15;
    protected static final int REPRODUCTION_INTERVAL = 5;
    protected static final String DISPLAY = "o";

    /**
     * Builds a new pilchard.
     */
    public Pilchard(GameOfLife game, int x, int y) {
        super(game, x, y);
        this.reproductionCountdown = REPRODUCTION_INTERVAL;
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

    @Override
    public void die() {
        this.game.getPilchards().remove(this);
        this.game.setElement(this.x, this.y, new Sea(this.game, this.x, this.y));
    }

    @Override
    protected void reproduce() {
        ArrayList<Sea> surroundings = this.getNearbySea();
        int random = (int) Math.random() * surroundings.size();
        Sea s = surroundings.get(random);
        this.game.addPilchard(new Pilchard(this.game, s.getX(), s.getY()));
        this.hasJustReproduced = true;
    }

    @Override
    protected void move() {
        ArrayList<Sea> surroundingsSea = new ArrayList<Sea>();
        surroundingsSea.addAll(this.getNearbySea());
        int size = surroundingsSea.size();
        if (size == 0) {
            return;
        }
        int random = (int) (Math.random() * size);
        Sea target = surroundingsSea.get(random);
        this.swap(target);
    }


    /**
     * Check if the pilchard is healthy,
     * meaning if it is not too old.
     * @return boolean true if the fish can play another cycle
     */
    @Override
    protected boolean isHealthy() {
        return this.currentAge < MAX_AGE;
    }
}
