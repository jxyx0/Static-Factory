package game.grounds.action;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.Plant;

/**
 * an interface for TreeAction
 */
public interface TreeAction {

    /**
     * To carry out the action
     * @param plant the plant that is executing the action
     * @param location the plant's location
     */
    void execute(Plant plant, Location location);

}
