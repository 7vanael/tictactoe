package org.example;

import java.util.Scanner;
import java.util.List;

public class Game {
    private Board board;
    private Scanner scanner;


    public Game(Scanner scanner){
        this.scanner = scanner;
        this.board = new Board();
    }

    public void play(){
        UserInterface userInterface = new UserInterface();
        /*
          Print Welcome screen
            Do
                Do
                  Turn(if player == computer){
                      Computer turn:
                        Check if opening move
                        Check if win possible
                        Check if loss imminent (to block a move)
                            Select a play
                            verify valid play
                  else
                      Prompt for user input
                        verify valid input
                        verify valid play
                  }
                  Record the play
                  Board.takeSquare
                  Print the board
                  Board.displayBoard
                  Check win conditions (including draw)
                    Set gameOver = true if draw or win/loss
                while(!gameOver)
                Declare winner
                Prompt user: Play again?
                    set sessionOver
            while(!sessionOver)
         */
    }

}
