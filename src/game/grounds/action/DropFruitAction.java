package game.grounds.action;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.Spawnable;
import game.grounds.Plant;
import game.grounds.action.TreeAction;

import java.util.List;
import java.util.Random;

public class DropFruitAction implements TreeAction {

    private Spawnable fruit;
    private Random random = new Random();

    /**
     * constructor
     * @param fruit the fruit to produce
     */
    public DropFruitAction(Spawnable fruit){
        this.fruit = fruit;
    }

    /**
     * check if the plant can drop fruit. If it can, it will drop at fruit at a random exit
     * @param actor
     * @param location
     */
    @Override
    public void execute(Plant actor, Location location) {
        if(random.nextFloat() > fruit.getSpawnRate()) {
            // do not spawn
            return;
        }
        List<Exit> exits = location.getExits();
        int randomInt = random.nextInt(exits.size());
        Location chosenLocation = exits.get(randomInt).getDestination();
        fruit.createNewInstance().addToLocation(chosenLocation);

    }

}
