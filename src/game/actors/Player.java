package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.enums.Identity;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the Player.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Player extends Actor {
    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Identity.HOSTILE_TO_ENEMY);
        this.addCapability(Identity.VULNERABLE_TO_ENEMY);
        this.addCapability(Identity.SPACE_TRAVELLER);
        this.addCapability(Identity.INTERN);
    }

    /**
     * allow player to choose an action out of all the actions that the player can do
     * add the actions that the player's behaviour allow
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {


        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();
        System.out.println("HP: " + super.getAttribute(BaseActorAttributes.HEALTH));
        System.out.println("Wallet: " + super.getBalance() + " credits");

        // return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }

}
