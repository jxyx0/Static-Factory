package game.items;

import edu.monash.fit2099.engine.actors.Actor;


/**
 * Consumable interface for items that could be used/ has special functionality
 */
public interface Consumable {
    /**
     * to use the item
     * @param actor the actor that consumes the item
     * @return a string describe the consumption
     */
    String consume(Actor actor);

    /**
     * remove the consumable item from the actor inventory
     * @param actor the actor whose inventory to remove the item from
     */
    void removeFromInventory(Actor actor);
}
