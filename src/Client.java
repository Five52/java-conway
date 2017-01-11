import java.util.ArrayList;

import game.GameOfLife;

public class Client {
    public static void main(String[] args) {
        GameOfLife game = new GameOfLife(15, 8, 20, 30);
        game.display();
    }
}
