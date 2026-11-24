package com.frustration;

public class Player {
    private int playerId;
    private int position;
    private int roundCount;

    public Player(int playerId) {
        this.playerId = playerId;
        this.position = 0; // Start at position 0
        this.roundCount = 0;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getRoundCount() {
        return roundCount;
    }

    public void incrementRoundCount() {
        this.roundCount++;
    }
}