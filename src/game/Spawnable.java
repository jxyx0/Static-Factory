package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

/**
 * an interface for classes that could be spawned
 * @param <T>
 */
public interface Spawnable <T>{

    /**
     * return a new instance of the spawnable
     * @return
     */
    Spawnable<T> createNewInstance();

    /**
     * @return the spawn rate of the spawnable
     */
    float getSpawnRate();

    /**
     * add the spawnable to the given location
     * @param location
     */
    void addToLocation(Location location);

}
