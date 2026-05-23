package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.action.TreeAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * a ground class that represents a generic plant on the moon
 */
public class Plant extends Ground {

    /**
     * the age of the tree
     */
    private int age;
    private Random random = new Random();
    private List<Stage> stages = new ArrayList<>();
    protected int currentStageIndex = 0;
    private Stage currentStage;
    private char currentChar;

    /**
     * the constructor
     * @param displayChar
     */
    public Plant(char displayChar){
        super(displayChar);
        currentStageIndex = 0;
        currentStage = new Stage(displayChar);
        currentChar = displayChar;
    }

    /**
     * Constructor
     * @param displayChar
     * @param stages the list of stages that represents stages in tree life cycle
     */
    public Plant(char displayChar, List<Stage> stages) {
        super(displayChar);
        currentChar = displayChar;
        this.stages = stages;
        age = 0;
        currentStageIndex = 0;
        currentStage = stages.get(currentStageIndex);
    }

    /**
     * set the lifecycle
     * @param stages
     */
    public void setStages(List<Stage> stages){
        this.stages = stages;
        currentStageIndex = 0;
        currentStage = stages.get(currentStageIndex);
    }
    public int getAge(){ return this.age; }


    /**
     * move into the next stage and update the value
     */
    public void grow(){
        currentStageIndex += 1;
        currentStage = stages.get(currentStageIndex);
        currentChar = stages.get(currentStageIndex).getCharacter();
        super.setDisplayChar(currentChar);
        age = 0;
    }



    /**
     * increment age in each tick and execute the current stage's tree actions.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        for(TreeAction treeAction: currentStage.getTreeActions()){
            treeAction.execute(this, location);
        }
        age++;

    }


}
