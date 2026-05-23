package game.items;

import java.util.Random;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actions.SellItemAction;
import game.enums.Identity;

/**
 * Item class that represents a jar of pickles
 */
public class JarOfPickles extends Item implements Consumable, Sellable {

    private final int expireRate;
    private int sellAmount;
    private Random random = new Random();
    private final int doubleRate;
    private String condition = null;

    /**
     * Constructor
     */
    public JarOfPickles() {
        super("jar of pickles", 'n', true);
        this.expireRate = 50;
        this.sellAmount = 25;
        this.doubleRate = 50;

        if (random.nextInt(100) >= doubleRate) {
            this.sellAmount = sellAmount * 2;
            condition = "The factory pays double the price!";
        }
    }

    /**
     * Handles the consume criteria
     *
     * @param actor the actor that consumes the item
     * @return A string to show the consume action
     */
    public String consume(Actor actor) {
        Random random = new Random();
        int randomChance = random.nextInt(100);

        String actorString = actor.toString();

        if (randomChance > expireRate) {
            actor.heal(1);
            return this.toString() + " heals " + actorString + " by 1 point";

        } else {
            actor.hurt(1);
            return this.toString() + " is expired! " + actorString + " takes 1 damage";
        }
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
     * Handles removing item from inventory
     *
     * @param actor the actor whose inventory to remove the item from
     */
    @Override
    public void removeFromInventory(Actor actor) {
        actor.removeItemFromInventory(this);
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
        String retval =  actor.toString() + " sold " + this + " for " + getSellAmount() + " credits.";

        if (condition == null) {
            return retval;
        } else {
            return condition + "\n" + retval;
        }
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
