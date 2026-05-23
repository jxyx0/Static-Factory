package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.InteractAction;

/**
 * Ground class that represents a puddle
 */
public class Puddle extends Ground implements Interactable {

    /**
     * Constructor
     */
    public Puddle() {
        super('~');
    }

    /**
     * Handles the player's interaction with the ground
     *
     * @param actor Actor that interacts with the ground
     * @return A string to show the result of user's interaction
     */
    public String interact(Actor actor) {
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 1);
        return "Maximum health of " + actor.toString() + " increased by 1 point";
    }

    /**
     * Handles the actions allowed to be performed by the actor
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return A list of actions that can be performed by the actor
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        if (location.containsAnActor()) {
            actions.add(new InteractAction(this));
        }

        return actions;
    }

    /**
     * Returns the action of player on the ground
     *
     * @return A string that contains the player's action(s)
     */
    public String getAction() {
        return " drinks water from puddle";
    }
}
