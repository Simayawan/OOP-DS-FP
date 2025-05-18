import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        Boolean running = true;
        Scanner scr = new Scanner(System.in);


        while(running){
            System.out.println("[1]Enter Queue");
            System.out.println("[2]Remove Name");
            System.out.println("[3]View Full Queue");
            System.out.println("[4]View Current Turn");

            System.out.println("Input Your Choice: ");
            String input = scr.nextLine();

            switch(input){
                case "1":
                    System.out.println("test1");
                    break;
                case "2":
                    System.out.println("test2");
                    break;
                case "3":
                    System.out.println("test3");
                    break;
                case "4":
                    System.out.println("test4");
                    break;
                default:
                    System.out.println("Sorry, that is not a valid input, please try again.");
            }

        }


    }
}
