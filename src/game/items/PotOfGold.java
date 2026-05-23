package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actions.SellItemAction;
import game.enums.Identity;

import java.util.Random;

/**
 * Item class that represents a pot of gold
 */
public class PotOfGold extends Item implements Consumable, Sellable {

    private final int goldAmount;
    private final int sellAmount;
    private Random random = new Random();
    private final int stolenRate;

    /**
     * Constructor
     */
    public PotOfGold() {
        super("pot of gold", '$', true);
        this.goldAmount = 10;
        this.sellAmount = 300;
        this.stolenRate = 25;
    }

    /**
     * Handles the consume (use) action of actor on the item
     *
     * @param actor the actor that consumes the item
     * @return A string to show the action
     */
    public String consume(Actor actor) {
        actor.addBalance(goldAmount);
        return goldAmount + " credits has been added to " + actor.toString() + "'s wallet\n" +
                actor.toString() + " has " + actor.getBalance() + " credits in their wallet currently";
    }

    /**
     * Handles removing item from inventory
     *
     * @param actor the actor whose inventory to remove the item from
     */
    @Override
    public void removeFromInventory(Actor actor) {
        actor.removeItemFromInventory(this);
    }

    /**
     * Handles actions allowed to be performed on this item
     *
     * @param otherActor the actor that owns the item
     * @return A list of allowed actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor) {
        ActionList actions = super.allowableActions(otherActor);

        actions.add(new ConsumeAction(this));

        return actions;
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
        if (random.nextInt(100) <= stolenRate) {
            return "The factory took the " + this + " without paying anything!";
        }

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
}
