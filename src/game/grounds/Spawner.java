package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.Spawnable;

/**
 * an interface for classes that could spawn a spawnable
 */
public interface Spawner {
    /**
     * spawn the spawnable at the given location
     * @param spawnable the class to spawn
     * @param location the location to spawn
     */
    void spawn(Spawnable spawnable, Location location);

}
