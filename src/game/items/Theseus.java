package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TeleportAction;

public class Theseus extends Item implements Purchasable{

    private int price;

    /***
     * Constructor.
     */
    public Theseus() {
        super("Theseus", '^', true);
        this.price = 100;
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
        if (actor.getBalance() >= actualPrice) {
            actor.deductBalance(actualPrice);
            actor.addItemToInventory(new Theseus());
            return actor + " successfully purchased " + this + " for " + actualPrice + " credits.";
        }
        return "Not enough credits to purchase " + this + ".";
    }

    /**
     * Returns a list of actions that can be performed with the Theseus item at the specified location
     *
     * @param location the location where the item is located
     * @return a list of actions that can be performed with the Theseus item
     */
    @Override
    public ActionList allowableActions(Location location) {
        ActionList actions = new ActionList();
        if (location.getItems().contains(this)) {
            actions.add(new TeleportAction());
        }
        return actions;
    }
}
