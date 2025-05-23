import com.sun.jdi.IntegerValue;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        Table table = new Table(2);

        table.addMoney(0, 500);
        table.addMoney(1, 500);

        boolean running = true;

        while(running) {
            System.out.println("[1]Play");
            System.out.println("[2]Quit");

            String input = scr.nextLine();

            switch (input) {
                case "1":
                    table.resetCheck();
                    table.drawCard(0, 2);
                    table.drawCard(1, 2);
                    table.revealCard(3);

                    System.out.println(table.seeTable());
                    System.out.println(table.seeHand(0));

                    table.addBet(0, 5);
                    table.addBet(1, 5);

                    double combinedBet = table.seeBet(0) + table.seeBet(1);


                    System.out.println("current pot: " + combinedBet);


                    System.out.println("[1]Fold [2]Check [3]Raise");
                    String choice = scr.nextLine();

                    switch(choice) {

                        case "1":
                            System.out.println("You folded");
                            table.resetBet();

                            table.addMoney(1, combinedBet);

                            System.out.println("current pot: " + combinedBet);
                            System.out.println("Your money: " + table.seeMoney(0));
                            System.out.println("Opponent's money: " + table.seeMoney(1));

                            table.clearTable();

                            table.removeCard(0);
                            table.removeCard(1);

                            if(table.seeMoney(0) <= 0.0 || table.seeMoney(1) <= 0.0){
                                running = false;
                            }
                            break;

                        case "2":
                            System.out.println("You checked");
                            break;

                        case "3":
                            System.out.println("Input your raise: ");
                            String raise = scr.nextLine();

                            if (Integer.parseInt(raise) > table.seeMoney(0)) {
                                System.out.println("Not enough money");
                                break;
                            }

                            if (Integer.parseInt(raise) == table.seeMoney(0)) {
                                System.out.println("All in");
                                table.addBet(0, Integer.parseInt(raise));
                                if(table.seeMoney(0) > table.seeMoney(1)){
                                    System.out.println("Opponent folded!");
                                    table.addMoney(0, table.seeBet(0) + table.seeBet(1));
                                    table.clearTable();
                                    table.removeCard(0);
                                    table.removeCard(1);
                                    break;
                                }
                                break;
                            }

                            else { //scenario where the ideal raise conditions are met
                                System.out.println("You Raised");
                                table.addBet(0, Integer.parseInt(raise));
                                System.out.println("Opponent called");
                                table.addBet(1, Integer.parseInt(raise)); //opponent instantly calls

                                double currentBet = combinedBet + table.seeBet(0) + table.seeBet(1);

                                System.out.println("Current bet: " + currentBet);
                                break;
                            }

                        default:
                            System.out.println("Invalid input");
                            break;
                    }

                    break;
                case "2":
                    System.out.println("Session Ended");
                    running = false;
            }
        }
    }
}


