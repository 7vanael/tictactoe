package org.example;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public static List<int[]> getAvailableSpaces(char[][] boardImp){
        List<int[]> availSpaces = new ArrayList<int[]>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (boardImp[i][j] == ' ') {
                    availSpaces.add(new int[] {i, j});
                }
            }
        }
        return availSpaces;
    }

    public static void displayBoard(char[][] board) {
        System.out.println();
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                line.append(board[i][j]);
                if(j < 2){
                    line.append(" | ");
                }
            }
            System.out.println(line);
            line.setLength(0);
            if(i < 2){
                System.out.println("--|---|---");
            }
        }
    }


    public static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkWin(char player, char[][] board) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] &&
                    board[i][1] == board[i][2] &&
                    board[i][2] == player) {
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == board[1][j] &&
                    board[1][j] == board[2][j] &&
                    board[2][j] == player) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }
}
