package com.xx3.lepoox.Model;

import android.util.Pair;

public class Game {

    private Pair<Player, Player> players;
    private int goal;

    /**
     * Getter for players
     *
     * @return A Pair<Player, Player>
     */
    public Pair<Player, Player> getPlayers() {
        return players;
    }

    /**
     * Getter for goal
     *
     * @return The goal
     */
    public int getGoal() {
        return goal;
    }

}
