package com.example.csc207simulator.game2.gametwobackend;

import com.example.csc207simulator.GameManager;
import com.example.csc207simulator.Player;

import java.util.Arrays;
import java.util.Random;

/**
 * A class responsible for managing game two.
 */
public class GameTwoManager extends GameManager {
    /**
     * Manages all the Teachers' statuses for this GameOneManager.
     */
    private TeacherStatusManager teacherManager;

    /**
     * The professor of this turn.
     */
    private Professor professor;

    /**
     * The ta of this turn.
     */
    private TA ta;



    /**
     * A constructor for a new GameTwoManager.
     *
     * @param knowledge      the knowledge going into the game.
     */
    public GameTwoManager(int knowledge, Player player) {
        super(knowledge);
        this.teacherManager = new TeacherStatusManager(player);
        professor = teacherManager.createPro();
        ta = teacherManager.createTa();
    }


    /**
     * Return if the player's guess about the professor and the ta is correct.
     * @param guess    the player's guess(1 is professor and 0 is ta)
     *
     * @return whether the player's guess is correct.
     */
    public boolean checkCorrect(int guess) {
        Random randomInt = new Random();
        int randomNum = randomInt.nextInt(100);
        // To create the random guessing number
        return guess == randomNum % 2;

    }

    /**
     * Return if the player's trade with the professor is successful.
     * @param num    the knowledge the player wants to trade.
     *
     * @return whether while trading with the Professor, the player has enough knowledge
     */
    public boolean checkNumPro(int num, Player player) {
        return player.hasEnough(num);
    }

    /**
     * Return the integer, the number of the knowledge the player get from the professor.
     * @param num    the knowledge the player wants to trade.
     *
     * @return how much knowledge the Professor should give.
     */
    public int showNumPro(int num,Player player) {
        if (checkNumPro(num, player)) {
            return professor.giveKnowledge(num);
        } else {
            return 0;
        }
    }

    /**
     * Return if the player's trade with the ta is successful.
     * @param num    the knowledge the player wants to trade.
     *
     * @return whether while trading with the Ta, the player has enough knowledge
     */
    public boolean checkNumTa(int num, Player player){
        return player.hasEnough(num);
    }

    /**
     * Return the integer, the number of the knowledge the player get from the ta.
     * @param num    the knowledge the player wants to trade.
     * @return  how much knowledge the Ta should give.
     */
    public int showNumTa(int num, Player player) {
        if (checkNumTa(num, player)) {
            return ta.giveKnowledge(num);
        } else {
            return 0;
        }
    }

    /**
     * Updates the current state of game two.
     */
    @Override
    protected void update() {
//        this.teacherManager.update();
    }


    public void add(int num) {
        teacherManager.add(num);
    }

    /**
     * Decide when the game end
     */
    public boolean isFinished() {
        return this.teacherManager.isEmpty();
    }


    /**
     * Return the traded result of the player with the teachers; if the status list contains all 0,
     * the the player failed to trade with every professor and every ta.
     */
    public boolean isEmpty() {
        return this.teacherManager.isEmpty();
    }


}
