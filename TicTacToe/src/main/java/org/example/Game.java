package org.example;

import java.util.Scanner;
import java.util.List;
import java.util.logging.Level;

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
        do{
            boolean gameOver = false;
            do{
                //Each player takes a turn, starting with the user
                for(Player player : Player.values()){
                    if(player == Player.X) {
                        playerTurn(userInterface, player);
                    }else{
                        computerTurn(board, player);
                    }
                    //Display the turn taken
                    board.displayBoard();
                    //Check if game is over
                    gameOver = (board.checkWin(player) || board.isBoardFull());
                    if(board.checkWin(player)){
                        userInterface.announceWinner(player);
                    }else {
                        userInterface.announceDraw();
                    }
                }
            }while(!gameOver);
            sessionOver = !userInterface.playAgain(scanner);
            this.board = new Board();
        }while(!sessionOver);
    }


    public void playerTurn(UserInterface userInterface, Player player){
        boolean validPlaySelected;
        int column = -1;
        int row = -1;
        do {
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

    public void computerTurn (Board impBoard, Player computer){
        //Copy of the board so we can look at specific locations
        Player[][] board = impBoard.getBoard();
//        The fun part:
//      Check if win possible; First check if computer can win, then check if player needs to be blocked
        for(Player player : Player.values()) {
            for (int i = 0; i < 3; i++) {
                // Check rows
                if (board[i][0] == computer && board[i][1] == computer && board[i][2] == null) {
                    impBoard.takeSquare(i, 2, computer);
                }
                if (board[i][1] == computer && board[i][2] == computer && board[i][0] == null) {
                    impBoard.takeSquare(i, 0, computer);
                }
                if (board[i][0] == computer && board[i][2] == computer && board[i][1] == null) {
                    impBoard.takeSquare(i, 1, computer);
                }

                // Check columns
                if (board[0][i] == computer && board[1][i] == computer && board[2][i] == null) {
                    impBoard.takeSquare(2, i, computer);
                }
                if (board[1][i] == computer && board[2][i] == computer && board[0][i] == null) {
                    impBoard.takeSquare(0, i, computer);
                }
                if (board[0][i] == computer && board[2][i] == computer && board[1][i] == null) {
                    impBoard.takeSquare(1, i, computer);
                }
            }

            // Check diagonals (still looking for if win is possible)
            if (board[0][0] == computer && board[1][1] == computer && board[2][2] == null) {
                impBoard.takeSquare(2, 2, computer);
            }
            if (board[1][1] == computer && board[2][2] == computer && board[0][0] == null) {
                impBoard.takeSquare(0, 0, computer);
            }
            if (board[0][0] == computer && board[2][2] == computer && board[1][1] == null) {
                impBoard.takeSquare(1, 1, computer);
            }

            if (board[0][2] == computer && board[1][1] == computer && board[2][0] == null) {
                impBoard.takeSquare(2, 0, computer);
            }
            if (board[1][1] == computer && board[2][0] == computer && board[0][2] == null) {
                impBoard.takeSquare(0, 2, computer);
            }
            if (board[0][2] == computer && board[2][0] == computer && board[1][1] == null) {
                impBoard.takeSquare(1, 1, computer);
            }
        }
//        Check if loss imminent (to block a move)

//        Select a play
//        verify valid play
    }

}
