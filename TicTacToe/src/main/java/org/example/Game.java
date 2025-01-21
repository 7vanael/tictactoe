package org.example;

import java.util.Scanner;

public class Game {
    private Board board;
    private Scanner scanner;


    public Game(Scanner scanner){
        this.scanner = scanner;
        this.board = new Board();
    }

    public void play(){
        UserInterface userInterface = new UserInterface();
        userInterface.welcomePrint();
        board.displayBoard();
        boolean sessionOver;
        Player activePlayer = Player.X;
        do{
            boolean gameOver = false;
            do{
                //Each player takes a turn, starting with the user

                if(activePlayer == Player.X) {
                    System.out.println("Player turn");
                    userTurn(userInterface, activePlayer);
                }else{
                    System.out.println("Computer turn");
                    Computer.turn(board, activePlayer);
                }
                //Display the turn taken
                board.displayBoard();
                //Check if game is over
                if(board.checkWin(activePlayer)){
                    gameOver = true; //Could this be removed? Maybe, but don't want an infinite while true loop
                    userInterface.announceWinner(activePlayer);
                    break;
                }else if (board.isBoardFull()){
                    gameOver = true;
                    userInterface.announceDraw();
                    break;
                }
                //Switch active player
                if (activePlayer == Player.X){
                    activePlayer = Player.O;
                }else {
                    activePlayer = Player.X;
                }
            }while(!gameOver);
            sessionOver = !userInterface.playAgain(scanner);
            this.board = new Board();
            gameOver = false;
        }while(!sessionOver);
    }


    public void userTurn(UserInterface userInterface, Player player){
        boolean validPlaySelected;
        int column = -1;
        int row = -1;
        do {
            //Get the coordinates from the user
            row = userInterface.getUserInputInt(scanner);
            column = userInterface.getUserInputInt(scanner);
            //If available, assigns the space to the active player
            validPlaySelected = board.takeSquare(row, column, player);
            if(!validPlaySelected){
                System.out.println("You selected row: " + row +1 + " column: " + column + 1);
                System.out.println("That space was taken. Try again");
            }
            //Continue soliciting user input until they enter a valid row & column
        }while(!validPlaySelected);
    }

//    public void computerTurn (Board impBoard, Player computer) {
//        int[] move = getComputerMove(impBoard, computer);
//        impBoard.takeSquare(move[0], move[1], computer);
//    }
//
//    public int[] getComputerMove(Board board, Player computer){
//
//        int[] moveToMake = new int[2];
////      First check if computer can win, then check if
////      the other player has a winning move and steal it
//        moveToMake = winningMove(board);
//        if(moveToMake != null){
////        Stop thinking and take the win if you have it
//            return moveToMake;
//        }
//        //Then, take the center if available
//        int[] center = new int[] {1, 1};
//        if (board.getSquare(center) != null){
//            return center;
//        }
//        //Then, try to get a corner
//         List<int[]> corners = Arrays.asList(
//                 new int[] {0, 0},
//                 new int[] {0, 2},
//                 new int[] {2, 0},
//                 new int[] {2, 2}
//         );
//        moveToMake = getSpecificType(corners);
//        if( moveToMake!= null){
//            return moveToMake;
//        }
//
//        //If no corners available, then we are left with sides
//        List<int[]> sides = Arrays.asList(
//          new int[] {0, 1},
//          new int[] {1, 0},
//          new int[] {1, 2},
//          new int[] {2, 1}
//        );
//        moveToMake = getSpecificType(sides);
//        if( moveToMake!= null){
//            return moveToMake;
//        }
//
//        return moveToMake;
//    }
//
//    public int[] winningMove (Board boardImp){
////       First check if computer can win, then check if
////      the other player has a winning move and steal it
//
//        //copy of the board for referencing
//        Player[][] board = boardImp.getBoard();
//        int[] winningMove = new int[2];
//        for(Player player : Player.values()) {
//            for (int i = 0; i < 3; i++) {
//                // Check rows
//                if (board[i][0] == player && board[i][1] == player && board[i][2] == null) {
//                    winningMove = new int[] {i, 2};
//                    return winningMove;
//                }
//                if (board[i][1] == player && board[i][2] == player && board[i][0] == null) {
//                    winningMove = new int[] {i, 0};
//                    return winningMove;
//                }
//                if (board[i][0] == player && board[i][2] == player && board[i][1] == null) {
//                    winningMove = new int[] {i, 1};
//                    return winningMove;
//                }
//
//                // Check columns
//                if (board[0][i] == player && board[1][i] == player && board[2][i] == null) {
//                    winningMove = new int[] {2, i};
//                    return winningMove;
//                }
//                if (board[1][i] == player && board[2][i] == player && board[0][i] == null) {
//                    winningMove = new int[] {0, i};
//                    return winningMove;
//                }
//                if (board[0][i] == player && board[2][i] == player && board[1][i] == null) {
//                    winningMove = new int[] {1, i};
//                    return winningMove;
//                }
//            }
//            // Check diagonals (still looking for if win is possible)
//            if (board[0][0] == player && board[1][1] == player && board[2][2] == null) {
//                winningMove = new int[] {2, 2};
//                return winningMove;
//            }
//            if (board[1][1] == player && board[2][2] == player && board[0][0] == null) {
//                winningMove = new int[] {2, 0};
//                return winningMove;
//            }
//            if (board[0][0] == player && board[2][2] == player && board[1][1] == null) {
//                winningMove = new int[] {1, 1};
//                return winningMove;
//            }
//
//            if (board[0][2] == player && board[1][1] == player && board[2][0] == null) {
//                winningMove = new int[] {2, 0};
//                return winningMove;
//            }
//            if (board[1][1] == player && board[2][0] == player && board[0][2] == null) {
//                winningMove = new int[] {0, 2};
//                return winningMove;
//            }
//            if (board[0][2] == player && board[2][0] == player && board[1][1] == null) {
//                winningMove = new int[] {1, 1};
//                return winningMove;
//            }
//        }
//        return new int[2];
//    }
//
//    public int[] getSpecificType(List<int[]> types){
//        List<int[]> availableSpecificType = types
//                .stream()
//                .filter(t -> board.getSquare(t)!= Player.O && board.getSquare(t)!= Player.X )
//                .toList();
//        if(!availableSpecificType.isEmpty()){
//            if (availableSpecificType.size() == 1){
//                return availableSpecificType.get(0);
//            }else {
//                for(int[] type : availableSpecificType){
//
//                    /*
//                    The important logic remaining goes here!!
//                     */
//                }
//            }
//        }
//        return new int[2];
//    }
}
