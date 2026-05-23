package game.items;

import edu.monash.fit2099.engine.actors.Actor;


/**
 * a fruit class that represent a small fruit
 */
public class SmallFruit extends Fruit {


    /**
     * heal the player by one points
     * @param actor the actor that consumes the item
     * @return
     */
    @Override
    public String consume(Actor actor) {
        String actorString = actor.toString();
        actor.heal(1);
        return this.toString() + " heals " + actorString + " by one points";
    }

    /**
     * constructor
     */
    public  SmallFruit(){
        super("small fruit", 'o', true, 0.3f);
    }


    /**
     * return a new instance of its class
     * @return a new instance
     */
    @Override
    public Fruit createNewInstance() {
        return new SmallFruit();
    }
}
