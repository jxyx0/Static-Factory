package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.enums.Identity;
import game.actions.AttackAction;

/**
 * a behaviour that reprensents an enmy sttack behaviour
 */
public class EnemyAttackBehaviour implements Behaviour {
    private Weapon weapon;
    public EnemyAttackBehaviour(Weapon weapon){
        this.weapon = weapon;
    }
    /**
     * check if the surrounding has actor that is vulnerable, if there is, this actor will attack
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit: map.locationOf(actor).getExits()){
            Location surrounding = exit.getDestination();
            if(surrounding.containsAnActor() && surrounding.getActor().hasCapability(Identity.VULNERABLE_TO_ENEMY) &&
                    !surrounding.getActor().hasCapability(Identity.HOSTILE)){
                Actor target = surrounding.getActor();
                return new AttackAction(target, exit.getName(), weapon);
            }
        }
        return null;
    }
}
