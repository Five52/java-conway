import java.util.ArrayList;

public class AdultStage implements Stage {
    /**
     * Move a shark to the nearest pilchard, or randomly if there isn't any.
     * An adult shark can take another shark's spot.
     * @param Shark the shark to move
     * @return Element the element the shark swapped with, or null if no movement is possible.
     */
    @Override
    public Element move(Shark shark) {
        ArrayList<Pilchard> nearbyPilchards = shark.getNearbyPilchards();
        int size = nearbyPilchards.size();
        if (size != 0) {
            int random = (int) (Math.random() * size);
            Element target = nearbyPilchards.get(random);
            shark.swap(target);
            return target;
        }
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

    protected Pilchard getNearestPilchard(Shark shark) {
        ArrayList<Pilchards> pilchards = shark.getGame().getPilchards();
        return null;
    }
}
