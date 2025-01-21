package org.example;

import java.util.Scanner;

public class Game {
    private char[][] board;
    private Scanner scanner;


    public Game(Scanner scanner) {
        this.scanner = scanner;
        this.board = new char[][]{
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ' , ' ', ' '}
        };
    }

    public void play() {
        UserInterface userInterface = new UserInterface();
        userInterface.welcomePrint();

        int userWins = 0;
        int computerWins = 0;
        int draws = 0;
        boolean sessionOver;
        char activePlayer = 'X';
        do {
            boolean gameOver = false;
            Board.displayBoard(board);

            do {
                //Each player takes a turn
                if (activePlayer == 'X') {
                    System.out.println("Player turn");
                    userTurn(userInterface, activePlayer);
                } else {
                    System.out.println("Computer turn");
                    Computer.turn(board, activePlayer);
                }
                //Display the turn taken
                Board.displayBoard(board);
                //Check if game is over (exit loop if it is)
                if (Board.checkWin(activePlayer, board)) {
                    if(activePlayer == 'X'){
                        userWins++;
                    }else{
                        computerWins++;
                    }
                    userInterface.announceWinner(activePlayer);
                    break;
                } else if (Board.isBoardFull(board)) {
                    userInterface.announceDraw();
                    draws++;
                    break;
                }

                //Switch active player
                if (activePlayer == 'X') {
                    activePlayer = 'O';
                } else {
                    activePlayer = 'X';
                }
            } while (!gameOver);
            System.out.printf("Your wins: %d, Computer wins: %d, Draws: %d%n", userWins, computerWins, draws);
            sessionOver = !userInterface.playAgain(scanner);
            this.board = new char[][]{
                    {' ', ' ', ' '},
                    {' ', ' ', ' '},
                    {' ' , ' ', ' '}
            };
            //Switch active player
            if (activePlayer == 'X') {
                activePlayer = 'O';
            } else {
                activePlayer = 'X';
            }
        } while (!sessionOver);
    }


    public void userTurn(UserInterface userInterface, char player) {
        boolean validPlaySelected = false;
        int column = -1;
        int row = -1;
        do {
            //Get the coordinates from the user
            System.out.println("Enter the row number you'd like to play in: (1-3)");
            row = userInterface.getUserInputInt(scanner);
            System.out.println("Enter the column number you'd like to play in: (1-3)");
            column = userInterface.getUserInputInt(scanner);
            //If available, assigns the space to the active player
            if(board[row][column] == ' '){
                board[row][column] = player;
                validPlaySelected = true;
            }else {
                row += 1;
                column += 1;
                System.out.println("You selected row: " + row + " column: " + column );
                System.out.println("That space was taken. Try again");
            }
            //Continue soliciting user input until they enter a valid row & column
        } while (!validPlaySelected);
    }
    public void printStats(){
        System.out.println();
    }
}
