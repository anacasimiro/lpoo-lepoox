package com.xx3.lepoox.model;

import android.util.Pair;

public class Game {

    private GameSettings settings;
    private Pair<Player, Player> players;
    private int goal;

    /**
     * Getter for settings
     *
     * @return The GameSettings
     */
    public GameSettings getSettings() {
        return settings;
    }

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
