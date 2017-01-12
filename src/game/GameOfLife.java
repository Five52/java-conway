package game;

import elements.*;

import java.util.ArrayList;

public class GameOfLife {

    protected Element[][] elements;
    protected int width;
    protected int height;
    protected ArrayList<Shark> sharks;
    protected ArrayList<Pilchard> pilchards;

    /**
     * Builds a new game of life.
     * @param width The width of the grid
     * @param height The height of the grid
     * @param nbSharks The number of sharks in the grid
     * @param nbPilchards The number of pilchards in the grid
     */
    public GameOfLife(int width, int height, int nbSharks, int nbPilchards) {
        this.width = width;
        this.height = height;
        this.elements = new Element[width][height];
        this.sharks = new ArrayList<Shark>();
        this.pilchards = new ArrayList<Pilchard>();

        this.initGame(nbSharks, nbPilchards);
        this.randomize();
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Element getElement(int x, int y) {
        return this.elements[x][y];
    }

    public void setElement(int x, int y, Element e) {
        this.elements[x][y] = e;
    }

    public ArrayList<Shark> getSharks() {
        return this.sharks;
    }

    public ArrayList<Pilchard> getPilchards() {
        return this.pilchards;
    }

    /**
     * Kill the given fish and replace it by a Sea element.
     * A Pilchard is killed when :
     * 1. Eaten by a shark
     * 2. End of life
     * A Shark is killed when :
     * 1. Has not eaten for a while
     * 2. End of life
     * @param fish The fish to kill
     */
    public void kill(Fish fish) {
        int x = fish.getX();
        int y = fish.getY();
        this.elements[x][y] = new Sea(this, x, y);
    }

    /**
     * Add a fish.
     * @param Fish the fish
     */
    protected void addFish(Fish fish) {
        int x = fish.getX();
        int y = fish.getY();
        this.elements[x][y] = fish;
    }

    /**
     * Add a pilchard.
     * @param Pilchard the pilchard
     */
    public void addPilchard(Pilchard p) {
        this.addFish(p);
        this.pilchards.add(p);
    }

    /**
     * Add a shark.
     * @param Shark the shark
     */
    public void addShark(Shark s) {
        this.addFish(s);
        this.sharks.add(s);
    }

    /**
     * Initialize the game.
     * The sharks are placed in the beginning of the grid,
     * the pilchards just after the sharks,
     * on the rest of the grid, sea elements are put.
     * @param nbSharks The number of sharks to put on the grid
     * @param nbPilchards The number of pilchards to put on the grid
     */
    protected void initGame(int nbSharks, int nbPilchards) {
        int x;
        int y;
        for (int i = 0; i < nbSharks; i++) {
            x = i % this.width;
            y = i / this.width;
            Shark shark = new Shark(this, x, y);
            this.elements[x][y] = shark;
            this.sharks.add(shark);

        }
        for (int i = nbSharks; i < nbSharks + nbPilchards; i++) {
            x = i % this.width;
            y = i / this.width;
            Pilchard pilchard = new Pilchard(this, x, y);
            this.elements[x][y] = pilchard;
            this.pilchards.add(pilchard);
        }
        for (int i = nbSharks + nbPilchards; i < this.width * this.height; i++) {
            x = i % this.width;
            y = i / this.width;
            this.elements[x][y] = new Sea(this, x, y);
        }
    }

    /**
     * Randomize the elements to mix them and have a random grid.
     */
    protected void randomize() {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                int x = (int) (Math.random() * this.width);
                int y = (int) (Math.random() * this.height);
                this.swap(this.elements[i][j], this.elements[x][y]);
            }
        }
    }

    /**
     * Do a cycle of game by making play every fish, sharks first.
     */
    public void doCycle() {
        int initialSize = this.sharks.size();
        for (int i = 0; i < this.sharks.size() && i < initialSize; i++) {
            if (!this.sharks.get(i).playAndStayAlive()) {
                // Since an element of the list has been removed,
                // we need to decrement the counter to stay in range
                i--;
            }
        }
        initialSize = this.pilchards.size();
        for (int i = 0; i < this.pilchards.size() && i < initialSize; i++) {
            if (!this.pilchards.get(i).playAndStayAlive()) {
                // Same logic here
                i--;
            }
        }
        this.display();
    }

    /**
     * Play the game until there is no fish left.
     */
    public void play() {
        int nbCycles = 0;
        this.display();
        while (this.sharks.size() > 0 && this.pilchards.size() > 0) {
            System.out.println("Cycle #" + nbCycles++);
            this.doCycle();
        }
    }

    /**
     * Display the grid with borders.
     */
    protected void display() {
        String line = "+-";
        for (int x = 0; x < this.width; x++) {
            line += "-";
        }
        line += "-+";
        System.out.println(line);

        for (int y = 0; y < this.height; y++) {
            line = "| ";
            for (int x = 0; x < this.width; x++) {
                line += this.elements[x][y].toString();
            }
            line += " |";
            System.out.println(line);
        }

        line = "+-";
        for (int x = 0; x < this.width; x++) {
            line += "-";
        }
        line += "-+";
        System.out.println(line);
    }

    /**
     * Swaps two cells of the grid.
     * @param e1 The first element to swap
     * @param e2 The second element to swap
     */
    public void swap(Element e1, Element e2) {
        // Swap cells in the game
        Element temp = this.elements[e1.getX()][e1.getY()];
        this.elements[e1.getX()][e1.getY()] = this.elements[e2.getX()][e2.getY()];
        this.elements[e2.getX()][e2.getY()] = temp;

        // Swap cells coords
        int tempX = e1.getX();
        int tempY = e1.getY();
        e1.setX(e2.getX());
        e1.setY(e2.getY());
        e2.setX(tempX);
        e2.setY(tempY);
    }
}
