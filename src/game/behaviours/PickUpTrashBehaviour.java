package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.List;
import java.util.Random;

public class PickUpTrashBehaviour implements Behaviour {

    Random random = new Random();

    /**
     * Always return the pick up action if the ground has any item, else return null
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        Location loction = map.locationOf(actor);
        List<Item> itemsOnGround = loction.getItems();
        if(itemsOnGround.size() > 0){
            int randomInt = random.nextInt(itemsOnGround.size());
            return new PickUpAction(itemsOnGround.get(randomInt));
        }

        return null;
    }
}
