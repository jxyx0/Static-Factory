package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Sellable;

/**
 * An action class to handle an item selling action by the player to a trader
 */
public class SellItemAction extends Action {

    private Sellable sellable;

    /**
     * Constructor
     *
     * @param sellable Item to be sold
     */
    public SellItemAction(Sellable sellable) {
        this.sellable = sellable;
    }

    /**
     * Handles the execution for selling this item
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String representing the action result
     */
    public String execute(Actor actor, GameMap map) {
        String retval = sellable.sellItem(actor, map);

        sellable.removeFromInventory(actor);

        if (!actor.isConscious()) {
            retval += "\n" + actor.unconscious(map);
        }

        return retval;
    }

    /**
     * Provides the menu description of this action
     *
     * @param actor The actor performing the action.
     * @return String representing the option for selling action
     */
    public String menuDescription(Actor actor) {
        return actor.toString() + " sells " + sellable;
    }
}
