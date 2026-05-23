package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Monologable;
import game.Monologue;
import game.actions.PrintMonologueAction;
import game.actions.SubscribeAction;

import java.util.ArrayList;

/**
 * an item that represents an AI device
 */
public class AIDevice extends Item implements Purchasable, Monologable, Subscriptable {

    private int price;
    private int subscriptionFee;
    private int numOfTurns;
    private int subscriptionPeriod;
    private boolean isSubscribed;
    private String subscribeDescription;

    /**
     * default constructor
     */
    public AIDevice(){
        super("Astley, an AI device", 'z', true);
        this.price = 50;
        this.subscriptionFee = 1;
        this.numOfTurns = 0;
        this.subscriptionPeriod = 5;
        this.isSubscribed = true;
        this.subscribeDescription = "";
    }

    /**
     * Create a list to store all monologues
     * @param actor the actor that owns the item
     * @return a list storing all monologues of the monologable
     */
    @Override
    public ArrayList<Monologue> createMonologueList(Actor actor) {
        ArrayList<Monologue> monologueList = new ArrayList<>();
        monologueList.add(new Monologue("The factory will never gonna give you up, valuable intern!", true));
        monologueList.add(new Monologue("We promise we never gonna let you down with a range of staff benefits.", true));
        monologueList.add(new Monologue("We never gonna run around and desert you, dear intern!", true));
        monologueList.add(new Monologue("We never gonna make you cry with unfair compensation.", (actor.getItemInventory().size() > 10)));
        monologueList.add(new Monologue("Trust is essential in this business. We promise we never gonna say goodbye to a valuable intern like you.", (actor.getBalance() > 50)));
        monologueList.add(new Monologue("Don't worry, we never gonna tell a lie and hurt you, unlike those hostile creatures.", (actor.getAttribute(BaseActorAttributes.HEALTH) < 2)));
        return monologueList;
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
     * return the subscription fee of the item
     * @return the subscription fee
     */
    @Override
    public int getSubscriptionFee() {
        return this.subscriptionFee;
    }

    /**
     * return the subscription description of the item
     * @return the subscription description
     */
    @Override
    public String getSubscribeDescription() {
        return this.subscribeDescription;
    }

    /**
     * Set isSubscribed to true if payment received, else false
     * @param subscriptionSuccess
     */
    @Override
    public void isSubscribed(boolean subscriptionSuccess) {
        this.isSubscribed = subscriptionSuccess;
    }

    /**
     * Handles the purchase of the item by deducting the price from the actor's balance.
     *
     * @param actor The actor purchasing the item.
     * @return A string indicating the result of the purchase action.
     */
    @Override
    public String purchasedBy(Actor actor) {
        actor.deductBalance(getPrice());
        actor.addItemToInventory(new AIDevice());
        return actor + " successfully purchased " + this + " for " + getPrice() + " credits.";
    }

    /**
     * Inform a carried Item of the passage of time.
     *
     * This method is called once per turn, if the Item is being carried.
     * @param currentLocation The location of the actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        numOfTurns += 1;
        if (numOfTurns == subscriptionPeriod) {
            SubscribeAction subscribeAction = new SubscribeAction(this);
            System.out.println(subscribeAction.execute(actor, currentLocation.map()));
            numOfTurns = 0;
        }
    }

    /**
     * add a PrintMonologue action to the allowable actions
     * @param otherActor the actor that owns the item
     * @return action list
     */
    @Override
    public ActionList allowableActions(Actor otherActor) {
        ActionList actions = super.allowableActions(otherActor);

        if (isSubscribed) {
            actions.add(new PrintMonologueAction(this));
        }

        return actions;
    }
}
