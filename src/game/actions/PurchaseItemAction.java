package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Purchasable;

/**
 * An action to purchase the purchasable
 */
public class PurchaseItemAction extends Action {

    private Purchasable purchasable;

    /**
     * constructor
     * @param purchasable The purchasable to be purchased
     */
    public PurchaseItemAction(Purchasable purchasable) {
        this.purchasable = purchasable;
    }

    /**
     * Execute the purchase action, checking if actor's balance is enough
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A string indicating the result of the purchase action
     */
    public String execute(Actor actor, GameMap map) {
        float price = purchasable.getPrice();
        if (actor.getBalance() >= price) {
            return purchasable.purchasedBy(actor);
        }
        return "Not enough credits to purchase " + purchasable + ".";
    }

    /**
     * Provides a description of the purchase action for display in menus.
     *
     * @param actor The actor performing the action.
     * @return A string describing the purchase action.
     */
    public String menuDescription(Actor actor) {
        return actor.toString() + " purchases " + purchasable.toString();
    }
}
