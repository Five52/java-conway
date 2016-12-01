public abstract class Fish extends Element {

    protected static final int MAX_AGE;
    protected static final int REPRODUCTION_INTERVAL;
    
    protected int currentAge;

    public Fish(int x, int y) {
        this.currentAge = 0;
        this.x = x;
        this.y = y;
    }
    
    public int getCurrentAge() {
        return this.age;
    }
    
    public void age() {
        this.currentAge++;
    }

    public void reproduce() {
        
    }
    
    public abstract int getMaxAge();
    public abstract int getCycleDuration();
    public abstract String getDisplay();
}
