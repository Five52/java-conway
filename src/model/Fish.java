package model;

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
     * Says if the fish has to reproduce (cycle in term).
     */
    protected boolean hasToReproduce;

    /**
     * Builds a new fish.
     * @param game The game
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public Fish(GameOfLife game, int x, int y) {
        super(game, x, y);
        this.currentAge = 0;
    }
    
    /**
     * Returns the current age of the fish.
     * @return currentAge, The current age of the fish.
     */
    public int getCurrentAge() {
        return this.currentAge;
    }
    
    /**
     * Age the fish (incrementation).
     */
    public void age() {
        this.currentAge++;
    }

    /**
     * Returns the boolean to say if the fish has to reproduce.
     * @return hasToReproduce, if the fish has to reproduce
     */
    public boolean hasToReproduce() {
        return hasToReproduce;
    }

    /**
     * Get the surroundings elements of the fish.
     * @return surroundings, an ArrayList of the surroundings elements
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
        if (endX > this.gameOfLife.getWidth() - 1) {
            endX = this.getX();
        }
        if (startY < 0) {
            startY = 0;
        }
        if (endY > this.gameOfLife.getHeight() - 1) {
            endY = this.getY();
        }
        
        for (int i = startX; i <= endX + 1; i++) {
            for (int j = startY - 1; j < endY - 1; j++) {
                if (i == this.x && j == this.y) {
                    continue;
                }
                surroundings.put(this.game.getElement(i, j));
            }
        }
        return surroundings;
    }
    
    /**
     * Returns the Sea element near by the fish.
     */
    public ArrayList<Sea> nearBySea() {
        ArrayList<Element> surroundings = this.getSurroundings();
        ArrayList<Sea> surroundingsSea = new ArrayList<Sea>();
        for (Element surroundingElement : surroundings) {
            if (surroundingElement instanceof Sea) {
                surroundingsSea.put(surroundingElement); 
            }
        }
        return surroundingsSea;
    }

    /**
     * Returns the max age of the fish.
     */
    public int getMaxAge() {
        return MAX_AGE;
    }
    
    /**
     * Return the cycle duration of the fish.
     */
    public int getReproductionDuration() {
        return REPRODUCTION_INTERVAL;
    }
}
