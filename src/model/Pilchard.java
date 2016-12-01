package model;

public class Pilchard extends Fish {
    
    protected static final int MAX_AGE = 10;
    protected static final int REPRODUCTION_INTERVAL = 4;
    protected static final String DISPLAY = ".";

    /**
     * Builds a new pilchard.
     */
    public Pilchard(int x, int y) {
        super(x, y);
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    public int getReproductionInterval() {
        return REPRODUCTION_INTERVAL;
    }

    @Override
    public String getDisplay() {
        return display;
    }
}
