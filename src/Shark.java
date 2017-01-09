public class Shark extends Fish {

    protected static final int MAX_AGE = 20;
    protected static final int REPRODUCTION_INTERVAL = 4;
    protected static final String DISPLAY = "O";

    /**
     * Builds a new shark.
     */
    public Shark(GameOfLife game, int x, int y) {
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
    public void play() {

    }
    
    @Override
    public String toString() {
        return DISPLAY;
    }
}
