public class GameOfLife {

    public Element[][] elements;
    public int width;
    public int height;

    public GameOfLife(int width, int height, int nbSharks, int nbPilchards) {
        this.width = width;
        this.height = height;
        this.elements = new Element[width][height];

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

    public void kill(Fish fish) {
        int x = fish.getX();
        int y = fish.getY();
        this.elements[x][y] = new Sea(this, x, y);
    }

    protected void initGame(int nbSharks, int nbPilchards) {
        int x;
        int y;
        for (int i = 0; i < nbSharks; i++) {
            x = i % this.width;
            y = i / this.height;
            this.elements[x][y] = new Shark(this, x, y);
        }
        for (int i = nbSharks; i < nbSharks + nbPilchards; i++) {
            x = i % this.width;
            y = i / this.height;
            this.elements[x][y] = new Pilchard(this, x, y);
        }
        for (int i = nbSharks + nbPilchards; i < this.width * this.height; i++) {
            x = i % this.width;
            y = i / this.height;
            this.elements[x][y] = new Sea(this, x, y);
        }
    }

    protected void randomize() {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                
                int x = (int) (Math.random() * this.width);
                int y = (int) (Math.random() * this.height);

                // Swap cells in the game
                Element temp = this.elements[i][j];
                this.elements[i][j] = this.elements[x][y];
                this.elements[x][y] = temp;

                // Swap cells coords
                int tempX = this.elements[i][j].getX();
                int tempY = this.elements[i][j].getY();
                this.elements[i][j].setX() = this.elements[x][y].getX();
                this.elements[i][j].setY() = this.elements[x][y].getY();
                this.elements[x][y].setX() = tempX;
                this.elements[x][y].setY() = tempY;
            }
        }
    }

    protected void display() {
        String line = "+-";
        for (int i = 0; i < this.width; i++) {
            line += "-";
        }
        line += "-+";
        System.out.println(line);

        for (int i = 0; i < this.width; i++) {
            line = "| ";
            for (int j = 0; j < this.height; j++) {
                line += this.elements[i][j].toString();
            }
            line += " |";
            System.out.println(line);
        }

        line = "+-";
        for (int i = 0; i < this.width; i++) {
            line += "-";
        }
        line += "-+";
        System.out.println(line);
    }
}
