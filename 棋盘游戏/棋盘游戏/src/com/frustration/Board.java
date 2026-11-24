package com.frustration;

public class Board {
    private static final int BOARD_SIZE = 36; // Large board, 36 cells
    private static final int FINISH_POSITION = BOARD_SIZE;

    public Board() {
        // Board initialization
    }

    public int getFinishPosition() {
        return FINISH_POSITION;
    }

    public boolean isAtFinish(int position) {
        return position == FINISH_POSITION;
    }

    public boolean isValidPosition(int position) {
        return position >= 0 && position <= FINISH_POSITION;
    }
}