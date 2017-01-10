import java.util.ArrayList;

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
    public void play() {
        ArrayList<Sea> surroundingsSea = new ArrayList<Sea>();
        surroundingsSea.addAll(this.getNearbySea());
        int size = surroundingsSea.size();
        if (size == 0) {
            return;
        }
        int random = (int) Math.random() * size;
        Sea target = surroundingsSea.get(random);
        if (this.hasToReproduce) {
            int i = target.getX();
            int j = target.getY();
            this.game.elements[i][j] = new Pilchard(this.game, i, j);
        } else {
            this.swap(target);
        }
    }

    @Override
    public String toString() {
        return DISPLAY;
    }
}
