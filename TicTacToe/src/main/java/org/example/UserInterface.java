package org.example;
import java.util.Scanner;

public class UserInterface {

    public void welcomePrint () {
        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("On your turn, first enter the row that you'd like to select (1-3)");
        System.out.println("Then you'll be asked for the column (1-3)");
        System.out.println("First player to 3-in-a-row wins! Good luck!");
    }

    public int getUserInputInt (Scanner keyboard){
        int userChoice = -1;
        boolean inputValid = false;
        while(!inputValid){

            String input = keyboard.nextLine();
            try{
                int numberInput = Integer.parseInt(input);
                if(0 < numberInput && numberInput <= 3){
                    userChoice = numberInput - 1;
                    inputValid = true;
                }else {
                    System.out.println("Please try again (Enter a number 1-3)");
                }
            }catch (NumberFormatException ex){
                System.out.println("Enter a number, 1-3");
            }
        }
        return userChoice;
    }

    public boolean playAgain (Scanner keyboard){
        System.out.println("Do you want to play again? (y/n");
        return (keyboard.nextLine().toLowerCase().equals("y") || keyboard.nextLine().toLowerCase().contains("yes"));
    }

    public void announceDraw (){
        System.out.println("It's a draw, Good game!");
    }

    public void announceWinner(Player player){
        System.out.println(player + " wins!");
    }
}
