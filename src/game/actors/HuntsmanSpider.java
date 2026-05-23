package game.actors;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * a hostile creature that represents huntsman spider
 */
public class HuntsmanSpider extends HostileCreature {

    /**
     * default constructor
     */
    public HuntsmanSpider() {
        super("Huntsman Spider", '8', 1, new IntrinsicWeapon(1, "attacks", 25), 0.05f);
    }


    /**
     * create a new instance of huntsman spider
     * @return new instance of huntsman spider
     */
    @Override
    public Creature createNewInstance() {
        return new HuntsmanSpider();
    }
}
