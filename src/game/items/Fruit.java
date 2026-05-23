package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.Spawnable;

/**
 * A item class that represents a generic fruit
 */
public abstract class Fruit extends Item implements Spawnable<Fruit>, Consumable {
    private float spawnRate;

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public Fruit(String name, char displayChar, boolean portable, float dropRate) {
        super(name, displayChar, portable);
        this.spawnRate = dropRate;
    }

    /**
     * return the drop rate if the fruit
     * @return
     */
    @Override
    public float getSpawnRate(){ return spawnRate; }

    public abstract String consume(Actor actor);

    /**
     * remove the item from the actor's inventory
     * @param actor the actor whose inventory to remove the item from
     */
    @Override
    public void removeFromInventory(Actor actor) {
        actor.removeItemFromInventory(this);
    }

    /**
     * add the item to the given location
     * @param location
     */
    @Override
    public void addToLocation(Location location) {
        location.addItem(this);
    }

    /**
     * add a consume action to the allowable actions
     * @param otherActor the actor that owns the item
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor) {
        ActionList actions = super.allowableActions(otherActor);

        actions.add(new ConsumeAction(this));

        return actions;
    }

}
