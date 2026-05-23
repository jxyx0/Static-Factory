package game.grounds;

import game.grounds.action.TreeAction;

import java.util.ArrayList;
import java.util.List;

/**
 * a class that represent s stage in a plant's lifecycle
 */
public class Stage {
    private int keyAge;
    private List<TreeAction> treeActions = new ArrayList<>();
    private char character;

    public Stage(char character ){
        this.character = character;
    }

    public Stage(int keyAge, char character){
        this.keyAge = keyAge;
        this.character = character;
    }

    /**
     * constructor
     * @param keyAge
     * @param
     * @param character
     */
    public Stage(int keyAge,  char character, List<TreeAction> treeActions ){
        this.keyAge = keyAge;
        this.character = character;
        this.treeActions = treeActions;
    }

    /**
     * add a new tree action to the stage
     * @param treeAction the treeaction that the tree can do
     */
    public void addTreeAction(TreeAction treeAction){
        treeActions.add(treeAction);
    }

    /**
     * return the age that the plant can enter the next stage
     * @return
     */
    public int getKeyAge(){ return keyAge; }

    public List<TreeAction> getTreeActions(){ return treeActions; }
    /**
     * return the display character of this stage
     * @return
     */
    public char getCharacter(){ return character; }
}
