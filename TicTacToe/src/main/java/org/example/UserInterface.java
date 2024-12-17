package org.example;
import java.util.Scanner;

public class UserInterface {

    public String getUserMenuInput (Scanner keyboard, String [] array){
        String userChoice = "";
        boolean inputValid = false;
        while(!inputValid){

            String input = keyboard.nextLine();
            try{
                int numberInput = Integer.parseInt(input);
                if(input.length() > 0 && numberInput <= array.length){
                    userChoice = array[numberInput-1];
                    inputValid = true;
                }else {
                    System.out.println("Sorry, that is not a correct option. Please try again");
                }
            }catch (NumberFormatException ex){
                System.out.println("Sorry, that is not a correct option. Please try again");
            }
        }
        return userChoice;
    }

    public String getUserInput(Scanner keyboard){
        String input = "";
        boolean validInput = false;
        while(!validInput){
            input = keyboard.nextLine();
            if(input.length()>0){
                validInput = true;
            }else {
                System.out.println("Please make a valid entry");
            }
        }
        return input;
    }
}
