package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actions.AttackAction;
import game.behaviours.EnemyAttackBehaviour;
import game.enums.Identity;


/**
 * a creature that has hostile nature
 */
public abstract class HostileCreature extends Creature {
    private Weapon weapon;

    /**
     * The constructor of the Actor class.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public HostileCreature(String name, char displayChar, int hitPoints, Weapon weapon, float spawnRate) {
        super(name, displayChar, hitPoints, spawnRate);
        super.addCapability(Identity.HOSTILE);
        behaviours.add(0, new EnemyAttackBehaviour(weapon));
        this.weapon = weapon;

    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        if(otherActor.hasCapability(Identity.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }



}
