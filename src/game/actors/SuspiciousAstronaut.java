package game.actors;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Spawnable;

public class SuspiciousAstronaut extends HostileCreature implements Spawnable<Creature> {

    /**
     * default constructor
     */
    public SuspiciousAstronaut() {
        super("Suspicious Astronaut", 'ඞ', 99, new IntrinsicWeapon(Integer.MAX_VALUE, "attacks", 100), 0.05f);

    }

    /**
     * create a new instance of suspicious astronaut
     * @return new instance of suspicious astronaut
     */
    @Override
    public Creature createNewInstance() {
        return new SuspiciousAstronaut();
    }
}
