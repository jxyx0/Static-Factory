package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;

import java.util.Random;

/**
 * an item that represents an energy drink
 */
public class EnergyDrink extends Item implements Purchasable, Consumable {

    private Random random = new Random();

    private int price;

    /**
     * default constructor
     */
    public EnergyDrink(){
        super("Energy Drink", '*', true);
        this.price = 10;
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
     *
     * @param actor The actor purchasing the item.
     * @return A string indicating the result of the purchase action.
     */
    @Override
    public String purchasedBy(Actor actor) {
        int actualPrice = getPrice();
        if(random.nextFloat() <= 0.2) {
            actualPrice *= 2;
        }
        if (actor.getBalance() >= actualPrice) {
            actor.deductBalance(actualPrice);
            actor.addItemToInventory(new EnergyDrink());
            return actor + " successfully purchased " + this + " for " + actualPrice + " credits.";
        }
        return "Not enough credits to purchase " + this + ".";
    }

    /**
     * to use the item
     * @param actor the actor that consumes the item
     * @return a string describe the consumption
     */
    @Override
    public String consume(Actor actor) {
        actor.heal(1);
        return this + " heals " + actor + " by one points";
    }

    /**
     * remove the item from the actor's inventory
     * @param actor the actor whose inventory to remove the item from
     */
    @Override
    public void removeFromInventory(Actor actor) {
        actor.removeItemFromInventory(this);
    }

    /**
     * add a consume action to the allowable actions
     * @param otherActor the actor that owns the item
     * @return action list
     */
    @Override
    public ActionList allowableActions(Actor otherActor) {
        ActionList actions = super.allowableActions(otherActor);

        actions.add(new ConsumeAction(this));

        return actions;
    }

}
