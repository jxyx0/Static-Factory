package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Interface to handle player's interactions with a ground
 */
public interface Interactable {

    /**
     * To interact with the ground
     *
     * @param actor Actor that interacts with the ground
     * @return A string to show the result of user's interaction
     */
    String interact(Actor actor);

    /**
     * Returns the action of player on the ground
     *
     * @return A string that contains the player's action(s)
     */
    String getAction();
}
