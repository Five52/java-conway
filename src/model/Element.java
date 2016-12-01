package model;

public abstract class Element {
    
    protected static String display;

    protected GameOfLife game;
    protected int x;
    protected int y;

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
}
