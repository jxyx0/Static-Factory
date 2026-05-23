package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.enums.Identity;

/**
 * A class that represents the floor inside a building.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class Floor extends Ground {
    public Floor() {
        super('_');
    }

    /**
     * only allow the actor with space traveller identity to enter
     * @param actor the Actor to check
     * @return a boolean indicating whether the actor can enter
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Identity.SPACE_TRAVELLER);
    }
}
