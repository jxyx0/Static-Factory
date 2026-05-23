package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.enums.Identity;

import java.util.Random;

/**
 * a weapon item that represents a dragon slayer replica
 */
public class DragonSlayerReplica extends WeaponItem implements Purchasable {

    private Random random = new Random();

    private int price;

    /**
     * default constructor
     */
    public DragonSlayerReplica(){
        super("Dragon Slayer Replica", 'x', 50, "attacks", 75);
        this.price = 100;
    }

    /**
     * if the actor nearby is hostile, player could have the option to use this weapon item to attack the enemy
     * @param otherActor the other actor
     * @param location the location of the other actor
     * @return action list
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions =  super.allowableActions(otherActor, location);
        if(otherActor.hasCapability(Identity.HOSTILE)){
            actions.add( new AttackAction(otherActor, location.toString(),this));
        }
        return actions;
    }

    /**
     * return the price of the item
     * @return the price
     */
    @Override
    public int getPrice() {
        return this.price;
    }

    /**
     * Handles the purchase of the item by deducting the price from the actor's balance.
     * There's a 50% chance that the actor will actually receive the replica.
     *
     * @param actor The actor purchasing the item.
     * @return A string indicating the result of the purchase action.
     */
    @Override
    public String purchasedBy(Actor actor) {
        actor.deductBalance(getPrice());
        if(random.nextFloat() <= 0.5) {
            actor.addItemToInventory(new DragonSlayerReplica());
            return actor + " successfully purchased " + this + " for " + getPrice() + " credits.";
        }
        return getPrice() + " credits are taken from " + actor + ", but " + actor + " doesn't receive anything in return!";
    }

}
