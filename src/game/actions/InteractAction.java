package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.Interactable;

/**
 * An action to handle the interact action on a ground
 */
public class InteractAction extends Action {

    private Interactable interactable;

    /**
     * Constructor
     *
     * @param interactable Ground that is interactable
     */
    public InteractAction(Interactable interactable)
    {
        this.interactable = interactable;
    }

    /**
     * Handles the execute action
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A string to show the results of th action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String str = interactable.interact(actor);
        return menuDescription(actor) + "\n" + str;
    }

    /**
     * Shows the menu for user interaction
     *
     * @param actor The actor performing the action.
     * @return A string to show an action choice for the user
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + interactable.getAction();
    }
}
