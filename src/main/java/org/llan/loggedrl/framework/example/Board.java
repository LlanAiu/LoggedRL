package org.llan.loggedrl.framework.example;

public class Board {
    private int[][] board;
    private int[] heights;

    private final int HEIGHT = 6;
    private final int WIDTH = 7;

    public Board() {
        this.board = new int[HEIGHT][WIDTH];
        this.heights = new int[WIDTH];
    }

    public boolean canPlace(int column) {
        return heights[column] < HEIGHT - 1;
    }

    public boolean place(int column, int playerId) {
        if (heights[column] < HEIGHT - 1) {
            board[heights[column]][column] = playerId;
            heights[column]++;
            return true;
        }
        return false;
    }

    public boolean checkWin(int playerId) {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (board[i][j] == playerId) {
                    if (j + 3 < WIDTH &&
                            board[i][j + 1] == playerId &&
                            board[i][j + 2] == playerId &&
                            board[i][j + 3] == playerId) {
                        return true;
                    }
                    if (i + 3 < HEIGHT) {
                        if (board[i + 1][j] == playerId &&
                                board[i + 2][j] == playerId &&
                                board[i + 3][j] == playerId) {
                            return true;
                        }
                        if (j + 3 < WIDTH &&
                                board[i + 1][j + 1] == playerId &&
                                board[i + 2][j + 2] == playerId &&
                                board[i + 3][j + 3] == playerId) {
                            return true;
                        }
                        if (j - 3 >= 0 &&
                                board[i + 1][j - 1] == playerId &&
                                board[i + 2][j - 2] == playerId &&
                                board[i + 3][j - 3] == playerId) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
