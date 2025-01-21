package org.example;

import java.util.ArrayList;
import java.util.List;

public class Computer {
    public static void turn(char[][] board, char player) {
        int[] move = Computer.getComputerMove(board, player);
        if(move == null){
            throw new RuntimeException("Unable to find a valid move for the computer to take");
        }
        if (board[move[0]][move[1]] ==' ') {
            board[move[0]][move[1]] = player;
        }else{
            throw new RuntimeException("Computer tried to take an occupied square");
        }
    }

    public static int[] getComputerMove(char[][] board, char player) {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = null;
        List<int[]> availSpaces = Board.getAvailableSpaces(board);
        // Try each available move
        for (int[] space : availSpaces) {
            board[space[0]][space[1]] = player;

            int score = evaluateMove(board, player, 0, false);

            board[space[0]][space[1]] = ' ';

            // Update best move if this score is better
            if (score > bestScore) {
                bestScore = score;
                bestMove = space;
            }

        }
        return bestMove;

    }

    public static int evaluateMove(char[][] board, char player, int depth, boolean isMaximizing) {
        // Get opponent's symbol
        char opponent = (player == 'X') ? 'O' : 'X';

        // Check terminal states
        if(Board.checkWin(player, board)){
            return 10 - depth;
        } else if (Board.checkWin(opponent, board)) {
            return depth - 10;
        } else if (Board.isBoardFull(board)) {
            return 0;
        }

        List<int[]> remainingSpacesAvailable = Board.getAvailableSpaces(board);

        //We expect the computer to go second, when there should be an even number of
        //open spaces remaining (this is Maximizing the score)
        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            // Try each available move, test it and undo it
            for (int[] space : remainingSpacesAvailable) {

                board[space[0]][space[1]] = player;
                int score = evaluateMove(board, player, depth +1, false);
                board[space[0]][space[1]] = ' ';

                bestScore = Math.max(score, bestScore);
            }
            return bestScore;

        } else { //This is minimizing the score
            int bestScore = Integer.MAX_VALUE;
            // Try each available move, test it, undo it
            for (int[] space : remainingSpacesAvailable) {
                board[space[0]][space[1]] = opponent;
                int score = evaluateMove(board, player, depth +1, true);
                board[space[0]][space[1]] = ' ';

                bestScore = Math.min(score, bestScore);

            }
            return bestScore;
        }
    }
}
