package client;

import game.GameOfLife;

import java.util.ArrayList;

public class Client {
    public static void main(String[] args) {
        int width = 40;
        int height = 15;
        int nbSharks = 5;
        int nbPilchards = 10;
        // A bigger example :
        // width = 100
        // height = 50
        // nbSharks = 4
        // nbPilchards = 300
        if (args.length == 0) {
            System.out.println(
                "Default values used :"
                + "\n    width: 40"
                + "\n    height: 15"
                + "\n    nbSharks: 5"
                + "\n    nbPilchards: 30"
            );
        } else if (args.length != 4) {
            System.err.println("USAGE: java Client <width> <height> <nbSharks> <nbPilchards>");
            System.exit(1);
        } else {
            width = Integer.valueOf(args[0]);
            height = Integer.valueOf(args[1]);
            nbSharks = Integer.valueOf(args[2]);
            nbPilchards = Integer.valueOf(args[3]);
        }
        GameOfLife game = new GameOfLife(width, height, nbSharks, nbPilchards);
        game.play();
    }
}
