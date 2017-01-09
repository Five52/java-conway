public class BabyStage implements Stage {
    @Override
    public Element move(Shark shark) {
        ArrayList<Element> surroundings = shark.getSurroundings();
        int size = surroundings.size();
        if (size == 0) {
            return null;
        }
        int random = (int) Math.random() * surroundings.size();
        Element target = surroundings.get(random);
        shark.swap(target);
        return target;
    }
}
