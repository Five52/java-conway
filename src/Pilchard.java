public class Pilchard extends Fish {
    
    protected static final int MAX_AGE = 10;
    protected static final int REPRODUCTION_INTERVAL = 2;
    protected static final String DISPLAY = "-";

    /**
     * Builds a new pilchard.
     */
    public Pilchard(GameOfLife game, int x, int y) {
        super(game, x, y);
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
}
