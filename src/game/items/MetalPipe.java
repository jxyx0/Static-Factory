package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.SellItemAction;
import game.enums.Identity;

/**
 * a weapon item that represents a metal pipe
 */
public class MetalPipe extends WeaponItem implements Sellable {

    private final int sellAmount;

    /**
     * Constructor.
     */
    public MetalPipe() {
        super("metal pipe", '!', 1, "attacks", 20);
        this.sellAmount = 35;
    }

    /**
     * if the actor nearby is  hostile, player could have the option to use this weapon item to attack the enemy
     * also handles the selling action of the actor on this item
     * @param otherActor the other actor
     * @param location the location of the other actor
     * @return A list of actions that the actor can do
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {

        ActionList actions =  super.allowableActions(otherActor, location);

        if (otherActor.hasCapability(Identity.HOSTILE)) {
            actions.add(new AttackAction(otherActor, location.toString(),this));
        }

        if (otherActor.hasCapability(Identity.TRADER)) {
            actions.add(new SellItemAction(this));
        }

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
        actor.addBalance(getSellAmount());
        return actor.toString() + " sold " + this + " for " + getSellAmount() + " credits.";
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
