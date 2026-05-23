package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Subscriptable;

/**
 * An action that represents subscribe action
 */
public class SubscribeAction extends Action {

    private Subscriptable subscriptable;

    /**
     * Constructor.
     */
    public SubscribeAction(Subscriptable subscriptable) {
        this.subscriptable = subscriptable;
    }

    /**
     * Perform the subscribe action.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened (the result of the action being performed) that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int subscriptionFee = subscriptable.getSubscriptionFee();
        if(actor.getBalance() >= subscriptionFee) {
            actor.deductBalance(subscriptionFee);
            subscriptable.isSubscribed(true);
            return "Subscription payment received!";
        }
        else {
            subscriptable.isSubscribed(false);
            return "Not enough credit to subscribe " + subscriptable;
        }
    }

    /**
     * Describe the subscribe action will be performed if this Action is chosen in the menu.
     * @param actor The actor performing the action.
     * @return the action description to be displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return subscriptable.getSubscribeDescription();
    }
}
