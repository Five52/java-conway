package stages;

import elements.Shark;
import elements.Element;

public abstract class Stage {
    protected static int NEXT_STAGE_TIME;
    /**
     * Move a shark to a nearby element.
     * @param Shark the shark
     * @return Element the element the shark swapped with, null if no movement
     */
    public abstract Element move(Shark shark);

    /**
     * Change the shark stage if its age is higher or equal to the time defined.
     * @param Shark the shark
     */
    public abstract void changeStageIfNeeded(Shark shark);
}
