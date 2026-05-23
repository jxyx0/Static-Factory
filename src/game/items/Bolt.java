package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.SellItemAction;
import game.enums.Identity;

/**
 * An item that represents the bolt item
 */
public class Bolt extends Item implements Sellable {

    private final int sellAmount;

    /***
     * Constructor.
     */
    public Bolt() {
        super("large bolt", '+', true);
        this.sellAmount = 25;
    }

    /**
     * Returns the selling value of this item
     *
     * @return Integer to represent the selling value of this item
     */
    public int getSellAmount() {
        return sellAmount;
    }

    /**
     * Handles the results of the selling action and events that may occur after
     *
     * @param actor Actor who is selling the item form their inventory
     * @param map GameMap to represent the map of the game
     * @return String to represent the results of the selling action
     */
    public String sellItem(Actor actor, GameMap map) {
        actor.addBalance(getSellAmount());
        return actor.toString() + " sold " + this + " for " + getSellAmount() + " credits.";
    }

    /**
     * Handles the actions that the actor can do to this item with another actor
     *
     * @param actor the other actor
     * @param location the location of the other actor
     * @return A list of actions that the actor can do
     */
    public ActionList allowableActions(Actor actor, Location location) {
        ActionList actions = super.allowableActions(actor);

        if (actor.hasCapability(Identity.TRADER)) {
            actions.add(new SellItemAction(this));
        }

        return actions;
    }

    /**
     * Removes the item from the actor's inventory
     *
     * @param actor The item will be removed from this actor's inventory
     */
    public void removeFromInventory(Actor actor) {
        actor.removeItemFromInventory(this);
    }
}
