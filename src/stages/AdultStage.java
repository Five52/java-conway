package stages;

import elements.*;

import java.util.ArrayList;

public class AdultStage extends Stage {
    /**
     * Move a shark to the nearest pilchard, or randomly if there isn't any.
     * An adult shark can take another shark's spot.
     * @param Shark the shark to move
     * @return Element the element the shark swapped with, or null if no movement is possible
     */
    @Override
    public Element move(Shark shark) {
        // If we have pilchards at one cell from the shark
        ArrayList<Pilchard> nearbyPilchards = shark.getNearbyPilchards();
        int size = nearbyPilchards.size();
        if (size != 0) {
            int random = (int) (Math.random() * size);
            Element target = nearbyPilchards.get(random);
            shark.swap(target);
            return target;
        }
        // Else we look for the nearest pilchard
        ArrayList<Pilchard> pilchards = shark.getGame().getPilchards();
        if (pilchards.size() != 0) {
            Pilchard nearest = this.getNearestPilchard(shark, pilchards);
            Element target = this.nextElementInBestPath(shark, nearest);
            shark.swap(target);
            return target;
        }

        // Else we move the shark randomly
        ArrayList<Sea> nearbySea = shark.getNearbySea();
        size = nearbySea.size();
        if (size == 0) {
            return null;
        }
        int random = (int) (Math.random() * size);
        Element target = nearbySea.get(random);
        shark.swap(target);
        return target;
    }

    /**
     * Retrieve the nearest pilchard from the shark.
     * @param Shark the shark
     * @param ArrayList<Pilchard> the list of pilchards to search into
     * @return Pilchard the nearest pilchard
     */
    protected Pilchard getNearestPilchard(Shark shark, ArrayList<Pilchard> pilchards) {
        Pilchard nearestPilchard = null;
        int nearestPilchardDistance = Integer.MAX_VALUE;
        for (Pilchard p : pilchards) {
            int pDistance = this.calculateDistance(shark, p);
            if (pDistance < nearestPilchardDistance) {
                nearestPilchard = p;
                nearestPilchardDistance = pDistance;
            }
        }
        return nearestPilchard;
    }

    /**
     * Calculate the distance between a shark and a pilchard.
     * Since the fishes move from cell to cell and since they can move diagonally,
     * the distance is simply obtained by calculating the differences between their respective abscisses and ordinates,
     * and returning the biggest difference.
     * @param Shark the shark
     * @param Pilchard the pilchard
     * @return int the distance between a shark and a pilchard
     */
    protected int calculateDistance(Shark s, Pilchard p) {
        int dx = Math.abs(s.getX() - p.getX());
        int dy = Math.abs(s.getY() - p.getY());
        return Math.max(dx, dy);
    }

    /**
     * Calculate the best path to go from a shark to a pilchard.
     * We calculate the oriented angle and define the best cell
     * to go next accordingly.
     * @param Shark the pilchard being chased
     * @param Pilchard the pilchard being chased
     * @return Element the element to go to follow the best path
     */
    protected Element nextElementInBestPath(Shark s, Pilchard p) {
        // Define the coordinates of the absciss vector
        int abscissX = 1;
        int abscissY = 0;
        // Define the coordinates of the vector defined from Shark to Pilchard
        int vectorX = p.getX() - s.getX();
        int vectorY = p.getY() - s.getY();
        // Calculate the oriented angle from the absciss vector to the Shark-Pilchard vector
        // The angle is in the intervall ]-Pi ; Pi]
        double angle = Math.atan2(
            abscissX * vectorY - abscissY * vectorX,
            abscissX * vectorX + abscissY * vectorY
        );

        int elementCoordinateX = s.getX();
        int elementCoordinateY = s.getY();
        // Increase or decrease elementCoordinateX
        if (angle > (5 * Math.PI / 8) || angle <= (-5 * Math.PI / 8  )) {
            elementCoordinateX--;
        } else if (angle > (-3 * Math.PI / 8) && angle <= (3 * Math.PI / 8)) {
            elementCoordinateX++;
        }
        // Increase or decrease elementCoordinateY
        if (angle > (-7 * Math.PI / 8) && angle <= (- Math.PI / 8)) {
            elementCoordinateY--;
        } else if (angle > (Math.PI / 8) && angle <= (7 * Math.PI / 8)) {
            elementCoordinateY++;
        }

        return s.getGame().getElement(elementCoordinateX, elementCoordinateY);
    }

    @Override
    public void changeStageIfNeeded(Shark shark) {
        return;
    }
}
