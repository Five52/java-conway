public class Sea extends Element {
    
    protected static final String DISPLAY = " ";

    /**
     * Builds a new Sea element.
     */
    public Sea(GameOfLife game, int x, int y) {
        super(game, x, y);
    }

    @Override
    public String toString() {
        return DISPLAY;
    }
}
