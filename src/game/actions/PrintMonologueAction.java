package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Monologable;
import game.Monologue;

import java.util.ArrayList;
import java.util.Random;

/**
 * An action that represents print monologue action
 */
public class PrintMonologueAction extends Action {

    private Monologable monologable;

    private Random random = new Random();

    /**
     * Constructor.
     */
    public PrintMonologueAction(Monologable monologable) {
        this.monologable = monologable;
    }

    /**
     * Perform the print monologue action.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened (the result of the action being performed) that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ArrayList<Monologue> validList = new ArrayList<>();
        ArrayList<Monologue> monologueList = monologable.createMonologueList(actor);

        for(Monologue monologue: monologueList) {
            if(monologue.isAvailable()) {
                validList.add(monologue);
            }
        }

        if(!validList.isEmpty()) {
            Monologue selectedMonologue = validList.get(random.nextInt(validList.size()));
            return monologable + ": " + selectedMonologue.getSentence();
        }
        return "Error";
    }

    /**
     * Describe the print monologue action will be performed if this Action is chosen in the menu.
     * @param actor The actor performing the action.
     * @return the action description to be displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " listens to " + monologable;
    }
}
