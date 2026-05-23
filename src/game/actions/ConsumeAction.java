package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Consumable;

/**
 * An action to consume/use the consumable
 */
public class ConsumeAction extends Action {

    private Consumable consumable;

    /**
     * constructor
     * @param consumable the consumable to be consumed
     */
    public ConsumeAction(Consumable consumable)
    {
        this.consumable = consumable;
    }

    /**
     * consume the consumable and remove the item from actor's inventory
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        String retval = consumable.consume(actor);
        consumable.removeFromInventory(actor);

        if (!actor.isConscious()) {
            retval += "\n" + actor.unconscious(map);
        }

        return menuDescription(actor) + "\n" + retval;
    }

    /**
     * return the description
     * @param actor The actor performing the action.
     * @return a description of the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " uses " + consumable.toString();
    }
}
