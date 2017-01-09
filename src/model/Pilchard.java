package model;

public class Pilchard extends Fish {
    
    protected static final int MAX_AGE = 10;
    protected static final int REPRODUCTION_INTERVAL = 2;
    protected static final String DISPLAY = ".";

    /**
     * Builds a new pilchard.
     */
    public Pilchard(int x, int y) {
        super(x, y);
    }
}
