package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Interface to represent items that can be sold to traders
 */
public interface Sellable {

    /**
     * Getter method to retrieve the sell value of an item
     *
     * @return Integer to represent the sell value
     */
    int getSellAmount();

    /**
     * Handles the results after selling an item
     *
     * @param actor Actor who is selling the item form their inventory
     * @param map GameMap to represent the map of the game
     * @return String to represent the result after selling
     */
    String sellItem(Actor actor, GameMap map);

    /**
     * Handles the removal of an item from the inventory
     *
     * @param actor The item will be removed from this actor's inventory
     */
    void removeFromInventory(Actor actor);
}
