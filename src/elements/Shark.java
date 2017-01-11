package elements;

import game.GameOfLife;
import stages.*;

import java.util.ArrayList;

public class Shark extends Fish {

    protected static final int MAX_AGE = 20;
    protected static final int REPRODUCTION_INTERVAL = 4;
    protected static final String DISPLAY = "O";

    /**
     * Maximal time a shark has to live when he has just eaten
    protected static final int FOOD_INTERVAL = 5;

    /**
     * Time left for shark to eat before dying
     */
    protected int countdownFood;
    protected Stage currentStage;

    /**
     * Builds a new shark.
     */
    public Shark(GameOfLife game, int x, int y) {
        super(game, x, y);
        this.currentStage = StageFactory.getBabyStage();
    }

    public void setStage(Stage stage) {
        this.currentStage = stage;
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
     * Age the shark and change its stage if needed.
     */
    public void age() {
        super.age();
        this.currentStage.changeStageIfNeeded(this);
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

    @Override
    protected void reproduce() {
        ArrayList<Sea> surroundings = this.getNearbySea();
        int random = (int) Math.random() * surroundings.size();
        Sea s = surroundings.get(random);
        this.game.addShark(new Shark(this.game, s.getX(), s.getY()));
    }
    
    @Override
    protected void move() {
        Element e = this.currentStage.move();
        if (e instanceof Pilchard) {

        }
    }
}
