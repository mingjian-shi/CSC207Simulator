package com.example.csc207simulator.game3.GameThreeBackend;

import com.example.csc207simulator.GameManager;
import com.example.csc207simulator.R;

/**
 * A class responsible for managing game three.
 */
public class GameThreeManager extends GameManager {

    /**
     * A boolean reprensents the finished status in game three.
     */
    boolean isFinsihed = false;

    /**
     * A int reprensents the score in game three.
     */
    private int score;

    /**
     * A int reprensents the secret in game three.
     */
    private int secret;

    /**
     * Create a new GameThreeManager.
     *
     * @param
     * @param knowledge        the knowledge going into the game.
     */
    public GameThreeManager(int knowledge) {
        super(knowledge);
    }


    /**
     * Return the state of the game.
     */
    public boolean isFinished() {
        return isFinsihed;
    }

    public int getScore() {
        return score;
    }


    /**
     * Updates the current state of game one.
     */
    @Override
    protected void update() {

    }

    /**
     * A Getter for secret.
     */
    public int getSecret() {
        return secret;
    }

    /**
     * A Setter for secret.
     * @param secret secret
     */
    public void setSecret(int secret) {
        this.secret = secret;
    }

    /**
     * A method responsible for changing the secret.
     * @param secret the secret responsible for changing th secret
     */
    public void subSecret(int secret) {
        this.secret = this.secret-secret;
    }

    /**
     * A Setter for finished.
     * @param setter the current status for game three
     */
    public void setFinished(boolean setter) {
        isFinsihed = setter;
    }

    /**
     * A Setter for score.
     * @param sco score
     */
    public void setscore(int sco) {
        score = sco;
    }

    /**
     * A method responsible for changing the score.
     * @param sco the score responsible for score change
     */
    public void addscore(int sco) {
        score = score + sco;
    }
}
