package model;

public class Sea extends Element {
    
    protected static final String DISPLAY = " ";

    /**
     * Builds a new Sea element.
     */
    public Sea(int x, int y) {
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
