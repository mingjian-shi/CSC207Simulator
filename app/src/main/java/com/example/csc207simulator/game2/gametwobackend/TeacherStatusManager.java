package com.example.csc207simulator.game2.gametwobackend;

import com.example.csc207simulator.Player;
import com.example.csc207simulator.game1.GameOneBackend.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * A class responsible for managing the traded status for Teachers.
 */
public class TeacherStatusManager implements Observer {

    /**
     * A list of Teachers belonging to this TeacherStatusManager.
     */
    private ArrayList<Integer> teachers;

    /**
     * The current knowledge the Player has obtained from the Teachers.
     */
    private int currentKnowledge;

    /**
     * The player of the game.
     */
    private Player player;


    /**
     * Creates a TeacherStatusManager with specific numbers of teachers.
     */
    public TeacherStatusManager(Player player) {
        this.teachers = new ArrayList<>(5);
        this.player = player;
        this.currentKnowledge = player.getKnowledge();
    }


    /**
     * Create specific types of teachers for this teacher manager.
     */
    public Professor createPro() {
        Professor pro = new Professor();
        pro.addObserver(player);
        return new Professor();
    }

    /**
     * Create specific types of teachers for this teacher manager.
     */
    public TA createTa() {
        TA ta = new TA();
        ta.addObserver(player);

        return new TA();
    }


    /**
     * Updates the properties of this TeacherStatusManager based on a change in an Observable object, in this
     * case a Teacher, this TeacherStatusManager observes.
     *
     * @param o   the Observable object that has changed, in this case a Teacher.
     * @param arg the parameter passed through from the Observable object, in this case the
     *            integer knowledge.
     */
    @Override
    public void update(Observable o, Object arg) {
        List<Teacher> toRemove = new ArrayList<>();
        toRemove.add((Teacher)o);
        this.currentKnowledge += (int) arg;
    }

    /**
     * Add the current traded status for this TeacherStatusManager.
     * @param num the number represents the current traded status for each teacher
     */
    public void add(int num){
        teachers.add(num);
    }

    /**
     * Check the current traded status for this TeacherStatusManager.
     */
    boolean isEmpty() {
        //if the player choose not to trade
        if (teachers.isEmpty()) {
            return true;
        }
        for (int i : teachers) {
            // if any tradedstatus in the list is traded
            if (i == 1) {
                return false;
            }
        }
        // ir every teacher is not traded
        return true;
    }


}


