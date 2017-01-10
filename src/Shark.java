import java.util.ArrayList;

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

    /**
     * Returns the Pilchards nearby the shark.
     * @return ArrayList<Pilchard> The arrayList of the nearby pilchards.
     */
    public ArrayList<Pilchard> getNearbyPilchards() {
        ArrayList<Element> surroundings = this.getSurroundings();
        ArrayList<Pilchard> pilchards = new ArrayList<Pilchard>();
        for (Element surroundingElement : surroundings) {
            if (surroundingElement instanceof Pilchard) {
                pilchards.add((Pilchard) surroundingElement); 
            }
        }
        return pilchards;
    }
}
