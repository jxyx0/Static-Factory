package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.ArrayList;

/**
 * Abstract class to represent actors that cannot wander around
 */
public abstract class IdleActor extends Actor {

    private ArrayList<Behaviour> behaviours = new ArrayList<>();

    /**
     * Constructor
     *
     * @param name String to represent name of actor
     * @param displayChar Char to represent the actor
     * @param hitPoints Integer to represent the actor's hit points
     */
    public IdleActor(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    /**
     * Handles the actions of the actor at each turn
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return The action to be done by the actor at this turn
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }

        return new DoNothingAction();
    }
}
