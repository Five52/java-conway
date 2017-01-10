import java.util.ArrayList;

public class BabyStage implements Stage {
    /**
     * Move a shark to a random spot.
     * A baby shark can't take another shark's spot.
     * @param Shark the shark to move
     * @return Element the element the shark swapped with, or null if no movement is possible.
     */
    @Override
    public Element move(Shark shark) {
        ArrayList<Element> nearby = new ArrayList<Element>();
        nearby.addAll(shark.getNearbySea());
        nearby.addAll(shark.getNearbyPilchards());
        int size = nearby.size();
        if (size == 0) {
            return null;
        }
        int random = (int) (Math.random() * size);
        Element target = nearby.get(random);
        shark.swap(target);
        return target;
    }
}
