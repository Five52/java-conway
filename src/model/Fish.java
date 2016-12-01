package model;

import java.util.ArrayList;

public abstract class Fish extends Element {

    protected static final int MAX_AGE;
    protected static final int REPRODUCTION_INTERVAL;
    
    protected int currentAge;

    public Fish(GameOfLife game, int x, int y) {
        super(game, x, y);
        this.currentAge = 0;
    }
    
    public int getCurrentAge() {
        return this.currentAge;
    }
    
    public void age() {
        this.currentAge++;
    }

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
    
    public void reproduce() {
        
    }

    public abstract int getMaxAge();
    public abstract int getCycleDuration();
    public abstract String getDisplay();
}
