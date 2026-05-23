package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.SellItemAction;
import game.enums.Identity;

import java.util.Random;

/**
 * an item that represents a toilet paper roll
 */
public class ToiletPaperRoll extends Item implements Purchasable, Sellable {

    private Random random = new Random();
    private int price;
    private final int sellAmount;
    private final int killRate;

    /**
     * default constructor
     */
    public ToiletPaperRoll(){
        super("Toilet Paper Roll", 's', true);
        this.price = 5;
        this.sellAmount = 1;
        this.killRate = 50;
    }

    /**
     * return the price of the item
     * @return the price
     */
    @Override
    public int getPrice() {
        return this.price;
    }

    /**
     * Handles the purchase of the item by deducting the price from the actor's balance.
     *
     * @param actor The actor purchasing the item.
     * @return A string indicating the result of the purchase action.
     */
    @Override
    public String purchasedBy(Actor actor) {
        int actualPrice = getPrice();
        if(random.nextFloat() <= 0.75) {
            actualPrice = 1;
        }
        actor.deductBalance(actualPrice);
        actor.addItemToInventory(new ToiletPaperRoll());
        return actor + " successfully purchased " + this + " for " + actualPrice + " credits.";
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
     * Handles the results of selling and events that may occur after
     *
     * @param actor Actor who is selling the item form their inventory
     * @param map GameMap to represent the map of the game
     * @return String to represent the results of the selling action
     */
    public String sellItem(Actor actor, GameMap map) {
        if (random.nextInt(100) <= killRate) {
            actor.hurt(Integer.MAX_VALUE);
            return "Humanoid Figure kills the Intern!";
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

    /**
     * Removes the item from the actor's inventory
     *
     * @param actor The item will be removed from this actor's inventory
     */
    public void removeFromInventory(Actor actor) {
        actor.removeItemFromInventory(this);
    }
}
