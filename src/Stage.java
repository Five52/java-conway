interface Stage {
    /**
     * Move a shark to a nearby element.
     * @return Element the element the shark swapped with, null if no movement.
     */
    Element move(Shark shark);
}
