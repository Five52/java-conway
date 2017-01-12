package elements;

import game.GameOfLife;

import java.util.ArrayList;

public abstract class Fish extends Element {

    /**
     * Maximum age of the element.
     */
    protected static int MAX_AGE;

    /**
     * Reproduction interval of the fish.
     */
    protected static int REPRODUCTION_INTERVAL;

    /**
     * Current age of the fish.
     */
    protected int currentAge;

    /**
     * Countdown of reproduction.
     * If it is down to 0, the fish's priority is to reproduce.
     */
    protected int reproductionCountdown;

    /**
     * Defines if the fish has just reproduced
     */
    protected boolean hasJustReproduced;

    /**
     * Builds a new fish.
     * @param game The game
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public Fish(GameOfLife game, int x, int y) {
        super(game, x, y);
        this.currentAge = 0;
        this.hasJustReproduced = false;
    }

    /**
     * Returns the current age of the fish.
     * @return currentAge The current age of the fish.
     */
    public int getCurrentAge() {
        return this.currentAge;
    }

    /**
     * Increase the age of the fish and decrease the reproduction countdown.
     */
    public void age() {
        this.currentAge++;
        this.ageReproduction();
    }

    /**
     * Reduce the reproduction countdown while resetting reproduction parameters
     */
    protected void ageReproduction() {
        if (this.hasJustReproduced) {
            this.reproductionCountdown = this.getReproductionDuration();
            this.hasJustReproduced = false;
        } else if (this.reproductionCountdown-- <= 1) {
            // Making sure the reproduction countdown doesn't go negative
            this.reproductionCountdown = 1;
        }
    }

    /**
     * Check if the fish can reproduce.
     * The reproduction countdown is checked at 1 or less because the the reproduction is made AFTER checking,
     * whereas eating and aging are made BEFORE checking.
     * Checking if the reproduction countdown is at 0 makes the fish reproduce one cycle too late.
     * @return boolean true if the fish can reproduce.
     */
    public boolean canReproduce() {
        return this.reproductionCountdown <= 1
            && this.getNearbySea().size() != 0;
    }

    /**
     * Get the surroundings elements of the fish.
     * @return surroundings an ArrayList of the surroundings elements
     */
    public ArrayList<Element> getSurroundings() {
        ArrayList<Element> surroundings = new ArrayList<Element>();

        int startX = this.getX() - 1;
        int endX = this.getX() + 1;
        int startY = this.getY() - 1;
        int endY = this.getY() + 1;

        // On évite les effets de bords
        if (startX < 0) {
            startX = 0;
        }
        if (endX > this.game.getWidth() - 1) {
            endX = this.getX();
        }
        if (startY < 0) {
            startY = 0;
        }
        if (endY > this.game.getHeight() - 1) {
            endY = this.getY();
        }

        for (int i = startX; i < endX + 1; i++) {
            for (int j = startY; j < endY + 1; j++) {
                if (i == this.x && j == this.y) {
                    continue;
                }
                surroundings.add(this.game.getElement(i, j));
            }
        }
        return surroundings;
    }

    /**
     * Returns the Sea element nearby the fish.
     * @return ArrayList<Sea> The arrayList of the surroundings Sea elements.
     */
    public ArrayList<Sea> getNearbySea() {
        ArrayList<Element> surroundings = this.getSurroundings();
        ArrayList<Sea> surroundingsSea = new ArrayList<Sea>();
        for (Element surroundingElement : surroundings) {
            if (surroundingElement instanceof Sea) {
                surroundingsSea.add((Sea) surroundingElement);
            }
        }
        return surroundingsSea;
    }

    /**
     * Returns the max age of the fish.
     */
    public abstract int getMaxAge();

    /**
     * Returns the reproduction duration of the fish.
     */
    public abstract int getReproductionDuration();

    /**
     * Play the fish's turn.
     * @return boolean true if the fish stays alive
     */
    public boolean playAndStayAlive() {
        if (this.canReproduce()) {
            this.reproduce();
        } else {
            this.move();
        }
        this.age();
        if (!this.isHealthy()) {
            this.die();
            return false;
        }
        return true;
    }

    /**
     * Make the fish die
     */
    public abstract void die();

    /**
     * Create a new fish
     */
    protected abstract void reproduce();

    /**
     * Move the fish
     */
    protected abstract void move();

    /**
     * Check if the fish is healthy
     * @return boolean true if the fish can play another cycle
     */
    protected abstract boolean isHealthy();
}
