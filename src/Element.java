public abstract class Element {
    
    /**
     * The display of the element.
     */
    protected static String DISPLAY;

    /**
     * The Game in which we are.
     */
    protected GameOfLife game;
    
    /**
     * X coordinate of the element.
     */
    protected int x;
    
    /**
     * Y coordinate of the element.
     */
    protected int y;

    /**
     * Builds a new element.
     * @param game The game in which we are
     * @param x The x of the element
     * @param y The y of the element
     */
    public Element(GameOfLife game, int x, int y) {
        this.game = game;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public GameOfLife getGame() {
        return this.game;
    }

    /**
     * Returns the display of the element.
     */
    public abstract String toString();

    public void swap(Element e) {
        this.game.swap(this, e);
    }
}
