import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);

        boolean running = true;

        while(running){
            System.out.println("[1]Play");
            System.out.println("[2]Quit");

            String input = scr.nextLine();

            switch(input){
                case "1":
                   // Boolean round = true
                    // While (round){
                        
                    //}
                    break;
                case "2":
                    System.out.println("Session Ended");
                    running = false;
            }
        }
    }
}
