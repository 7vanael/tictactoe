package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Computer {
    public static void turn(Board board, Player player) {
        Board experimentalBoard = new Board();
        //sets our experimental board to look like our starting board so we don't mess it up
        experimentalBoard.setBoard(board.getBoard());
        int[] move = Computer.getComputerMove(experimentalBoard, player);
        if (!board.takeSquare(move[0], move[1], player)) {
            throw new RuntimeException("Computer tried to take an occupied square");
        }
    }

    public static int[] getComputerMove(Board board, Player player) {
        /*
        Ok, but what if we tried recursion?
        we stop iterating the chain after:
        - win condition met (for either player), or board is full
        if(not terminal block of chain){
        What's the step?
        - for each possible square available, try taking it,
         score it for how often downstream it results in a win
         -Evaluate the score of the current board
        }
        summation & return
         */

        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = null;
        List<int[]> availSpaces = new ArrayList<int[]>();
        availSpaces = Board.getAvailableSpaces(board);
        // Try each available move
        for (int[] space : availSpaces) {
            //make a new board, replicate the old board but take the move in question
            Board newBoard = new Board();
            newBoard.setBoard(board.getBoard().clone());
            newBoard.takeSquare(space[0], space[1], player);
            int score = evaluateMove(newBoard, player, 0);

            // Update best move if this score is better
            if (score > bestScore) {
                bestScore = score;
                bestMove = new int[]{space[0], space[1]};
            }

        }
        return bestMove;

/*
        *****This is the initial logic to decide on the computer moves before
        * considering recursion. It started looking like the options were
        * going flat when having to actually decide between available corner
        * or side spaces. I paused pursuing that track to investigate a recursive
        * solution possibility; See above.
        * ******
        *
        int[] moveToMake = new int[2];
//      First check if computer can win, then check if
//      the other player has a winning move and steal it
        moveToMake = winningMove(board);
        if(moveToMake.length == 2){
//        Stop thinking and take the win if you have it
            return moveToMake;
        }
        //Then, take the center if available
        int[] center = new int[] {1, 1};
        //TO-DO: Will the center == null?
        if (board.getSquare(center) != null){
            return center;
        }
        //Then, try to get a corner
        List<int[]> corners = Arrays.asList(
                new int[] {0, 0},
                new int[] {0, 2},
                new int[] {2, 0},
                new int[] {2, 2}
        );
        moveToMake = getSpecificType(board, corners);
        if( moveToMake!= null){
            return moveToMake;
        }

        //If no corners available, then we are left with sides
        List<int[]> sides = Arrays.asList(
                new int[] {0, 1},
                new int[] {1, 0},
                new int[] {1, 2},
                new int[] {2, 1}
        );
        moveToMake = getSpecificType(board, sides);
        if( moveToMake!= null){
            return moveToMake;
        }

        return moveToMake;
    }

    public static int[] winningMove (Board boardImp){
//       First check if computer can win, then check if
//      the other player has a winning move and steal it

        //copy of the board for referencing
        Player[][] board = boardImp.getBoard();
        int[] winningMove = new int[2];

        //TO-DO: Could do away with for each, instead just take two calls to the contained
        //code, first with the player.O, then player.X?
        for(Player player : Player.values()) {
            for (int i = 0; i < 3; i++) {
                // Check rows
                if (board[i][0] == player && board[i][1] == player && board[i][2] == null) {
                    winningMove = new int[] {i, 2};
                    return winningMove;
                }
                if (board[i][1] == player && board[i][2] == player && board[i][0] == null) {
                    winningMove = new int[] {i, 0};
                    return winningMove;
                }
                if (board[i][0] == player && board[i][2] == player && board[i][1] == null) {
                    winningMove = new int[] {i, 1};
                    return winningMove;
                }

                // Check columns
                if (board[0][i] == player && board[1][i] == player && board[2][i] == null) {
                    winningMove = new int[] {2, i};
                    return winningMove;
                }
                if (board[1][i] == player && board[2][i] == player && board[0][i] == null) {
                    winningMove = new int[] {0, i};
                    return winningMove;
                }
                if (board[0][i] == player && board[2][i] == player && board[1][i] == null) {
                    winningMove = new int[] {1, i};
                    return winningMove;
                }
            }
            // Check diagonals (still looking for if win is possible)
            if (board[0][0] == player && board[1][1] == player && board[2][2] == null) {
                winningMove = new int[] {2, 2};
                return winningMove;
            }
            if (board[1][1] == player && board[2][2] == player && board[0][0] == null) {
                winningMove = new int[] {2, 0};
                return winningMove;
            }
            if (board[0][0] == player && board[2][2] == player && board[1][1] == null) {
                winningMove = new int[] {1, 1};
                return winningMove;
            }

            if (board[0][2] == player && board[1][1] == player && board[2][0] == null) {
                winningMove = new int[] {2, 0};
                return winningMove;
            }
            if (board[1][1] == player && board[2][0] == player && board[0][2] == null) {
                winningMove = new int[] {0, 2};
                return winningMove;
            }
            if (board[0][2] == player && board[2][0] == player && board[1][1] == null) {
                winningMove = new int[] {1, 1};
                return winningMove;
            }
        }
        //no winning move available
        return new int[0];
    }

    public static int[] getSpecificType(Board board, List<int[]> types){
        List<int[]> availableSpecificType = types
                .stream()
                .filter(t -> board.getSquare(t)!= Player.O && board.getSquare(t)!= Player.X )
                .toList();
        if(!availableSpecificType.isEmpty()){
            if (availableSpecificType.size() == 1){
                return availableSpecificType.get(0);
            }else {
                for(int[] type : availableSpecificType){


                    The important logic remaining goes here!!
                    But... it is quite cumbersome. Rethink needed. Save this for later


                }
            }
        }
        */
    }

    public static int evaluateMove(Board board, Player player, int depth) {
        // Get opponent's symbol
        Player opponent = (player == Player.X) ? Player.O : Player.X;

        // Check terminal states
        if(board.checkWin(player)){
            //These weights or parameters to the scoring might need to be adjusted...
            return 10 - depth;
        } else if (board.checkWin(opponent)) {
            return depth - 10;
        } else if (board.isBoardFull()) {
            return 0;
        }

        List<int[]> remainingSpacesAvailable = new ArrayList<int[]>();
        remainingSpacesAvailable = Board.getAvailableSpaces(board);

        //We expect the computer to go second, when there should be an even number of
        //open spaces remaining
        if (remainingSpacesAvailable.size() % 2 == 0) {
            int bestScore = Integer.MIN_VALUE;
            // Try each available move
            for (int[] space : remainingSpacesAvailable) {
                board.takeSquare(space[0], space[1], player);
                int score = evaluateMove(board, player, depth +1);
                bestScore = Math.max(score, bestScore);
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            // Try each available move
            for (int[] space : remainingSpacesAvailable) {
                board.takeSquare(space[0], space[1], opponent);
                int score = evaluateMove(board, player, depth +1);
                bestScore = Math.min(score, bestScore);

            }
            return bestScore;
        }

    }
}
