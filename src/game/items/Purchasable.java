package game.items;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * An interface representing items that can be purchased in the game.
 */
public interface Purchasable {

    /**
     * Gets the price of the item.
     *
     * @return The price of the item.
     */
    int getPrice();

    /**
     * Handles the purchase of the item by a specific actor.
     *
     * @param actor The actor purchasing the item.
     */
    String purchasedBy(Actor actor);

}
