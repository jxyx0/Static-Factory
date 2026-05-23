package game;

import edu.monash.fit2099.engine.actors.Actor;

import java.util.ArrayList;

/**
 * An interface representing items/actors/grounds that can have monologue.
 */
public interface Monologable {

    /**
     * Create a list to store all monologues
     * @param actor the actor that owns the item
     * @return a list storing all monologues of the monologable
     */
    ArrayList<Monologue> createMonologueList(Actor actor);
}
