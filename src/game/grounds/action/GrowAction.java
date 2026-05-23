package game.grounds.action;

import edu.monash.fit2099.engine.positions.Location;
import game.grounds.Plant;


/**
 * an action where the tree can grow into the next stage
 */
public class GrowAction implements TreeAction {

    private int keyAge;

    /**
     * constructor
     * @param keyAge
     */
    public GrowAction(int keyAge){
        this.keyAge = keyAge;
    }

    /**
     * check if the plant is old enough to grow into the next stage
     * @param plant the plant that will execute the action
     * @param location the plant's location
     */
    @Override
    public void execute(Plant plant, Location location) {
        if(plant.getAge() >= keyAge){
            plant.grow();
        }
    }

}
