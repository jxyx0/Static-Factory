package game.grounds;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Spawnable;
import game.actors.Creature;
import game.actors.HuntsmanSpider;

import java.util.List;
import java.util.Random;

/**
 * A ground that will spawn a certain creature
 */
public class Crater extends Ground implements Spawner {

    private Creature spawnCreature;
    private Random random = new Random();
    /**
     * The default constructor of the Actor class.
     */
    public Crater() {

        super('u');
        this.spawnCreature = new HuntsmanSpider();
    }

    /**
     * Constructor that takes in the creatures to be spawned.
     * @param spawnCreature
     */
    public Crater(Creature spawnCreature){
        super('u');
        this.spawnCreature = spawnCreature;
    }

    /**
     * try to spawn creature every tick
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        spawn(spawnCreature, getRandomLocation(location));
    }

    /**
     * Spawn the given spawnable creature at the given location
     * @param spawnable the creature to be spawned
     * @param location the location to spawn the creature on
     */
    @Override
    public void spawn(Spawnable spawnable, Location location){
        if(spawnable == null) return;
        if(random.nextFloat() < spawnable.getSpawnRate()){
            spawnable.createNewInstance().addToLocation(location);
        }
    };

    /**
     * return a random location around the crater
     * @param location the location of the crater
     * @return
     */
    public Location getRandomLocation(Location location){
        List<Exit> exits = location.getExits();
        int randomInt = random.nextInt(exits.size());
        Location chosenLocation = exits.get(randomInt).getDestination();
        return chosenLocation;
    }


}
