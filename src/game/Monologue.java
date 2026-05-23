package game;

/**
 * A class representing the monologue.
 */
public class Monologue {

    private String sentence;
    private boolean condition;

    public Monologue (String sentence, Boolean condition) {
        this.sentence = sentence;
        this.condition = condition;
    }

    /**
     * return the sentence of the monologue
     * @return the sentence of the monologue
     */
    public String getSentence() {
        return this.sentence;
    }

    /**
     * return true if the condition is met, else false
     * @return the boolean
     */
    public boolean isAvailable() {
        return this.condition;
    }
}