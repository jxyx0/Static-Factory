package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Spawnable;
import game.behaviours.WanderBehaviour;

import java.util.ArrayList;
import java.util.List;

/**
 * An actor class that represents the creature on the moon
 */
public abstract class Creature extends Actor implements Spawnable<Creature> {
    /**
     * a list of behaviour that the hostile creature possess, the behaviour is ranked and hence the more dominant
     * behaviour will has a lower index in the list
     */
    protected List<Behaviour> behaviours = new ArrayList<>();
    private float spawnRate;
    /**
     * The constructor of the Actor class.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Creature(String name, char displayChar, int hitPoints, float spawnRate) {
        super(name, displayChar, hitPoints);
        this.spawnRate = spawnRate;
        this.behaviours.add(new WanderBehaviour());
    }

    /**
     * return an action according to the actor's behaviour
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * at itself to the given location
     * @param location the location to add the creature on
     */
    @Override
    public void addToLocation(Location location) {
        if(!location.containsAnActor()) location.addActor(this);
    }

    public float getSpawnRate(){return  spawnRate; }

}
