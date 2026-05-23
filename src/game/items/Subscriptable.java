package game.items;

/**
 * An interface representing items that can be subscribed in the game.
 */
public interface Subscriptable {

    /**
     * return the subscription fee of the item
     * @return the subscription fee
     */
    int getSubscriptionFee();

    /**
     * return the subscription description of the item
     * @return the subscription description
     */
    String getSubscribeDescription();


    /**
     * Set isSubscribed to true if payment received, else false
     * @param subscriptionSuccess
     */
    void isSubscribed(boolean subscriptionSuccess);
}
