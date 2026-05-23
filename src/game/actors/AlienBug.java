package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.enums.Identity;
import game.behaviours.PickUpTrashBehaviour;
import game.behaviours.FollowBehaviour;

import java.util.List;
import java.util.Random;

/**
 * A class that represents the Alien Bug that live on the moon
 *
 * Alien Bug's charactersitics:
 *  - can't attack but hostile
 *  - will drop all possession when dead
 *  - will wander before it meets player
 *  - will follow players forever after it meets player
 *  - will prioritise picking up trash to follow player
 *
 */

public class AlienBug extends Creature {
    /**
     * The follow target of the bug
     */
    private Actor target = null;
    /**
     * The bug's name
     */
    private String name;
    private Random random = new Random();
    /**
     * The constructor of the AlienBug class.
     */
    public AlienBug() {
        super("AlienBug", 'a', 2, 0.1f);
        super.addCapability(Identity.HOSTILE);
        super.addCapability(Identity.SPACE_TRAVELLER);
        behaviours.add(0, new PickUpTrashBehaviour());
        name = String.format("Feature-%03d", random.nextInt(1000));
    }

    /**
     * it will check if the otherActor is the intern, if it is intern, it will add the follow behaviour to the bug
     * and remove the wanderer behaviour
     * @param otherActor the other actor in its surrounding
     */
    private void checkForPlayer(Actor otherActor){
        if(otherActor.hasCapability(Identity.INTERN)){
            target =otherActor;
            behaviours.add(1, new FollowBehaviour(target));
            int lastIndex = behaviours.size()-1;
            behaviours.remove(lastIndex);
        }
    }

    /**
     * Other actor that is hostile to enemy is allowed to attack the bug
     * The bug will always check if the other actor is player
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a list of action that tho other actor can do to it
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        if(target == null){
            checkForPlayer(otherActor);
        }
        ActionList actions = super.allowableActions(otherActor, direction, map);
        if(otherActor.hasCapability(Identity.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    /**
     * When the actor is unconscious, it means it is dead.
     * When dead, alien bug will drop everything.
     * @param actor the perpetrator
     * @param map where the actor fell unconscious
     * @return a string that describe the actor's death
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        Location currentLocation = map.locationOf(this);
        for(Item item: super.getItemInventory()){
            currentLocation.addItem(item);
        }
        return super.unconscious(actor, map);
    }

    /**
     * create a new instance of alien bug
     * @return new instance of alien bug
     */
    @Override
    public Creature createNewInstance() {
        return new AlienBug();
    }

    /**
     * Return the alien bug's name
     * @return name
     */
    @Override
    public String toString() {
        return name+ " (" +
                super.getAttribute(BaseActorAttributes.HEALTH) + "/" +
                super.getAttributeMaximum(BaseActorAttributes.HEALTH) +
                ")";
    }
}
