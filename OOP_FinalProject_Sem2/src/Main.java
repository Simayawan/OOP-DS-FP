import com.sun.jdi.IntegerValue;

import java.util.Arrays;
import java.util.Scanner;

enum gameState{ // game states
    IDLE, //idle state
    PREFLOP,
    FLOP,
    TURN,
    RIVER,
    SHOWDOWN,
    WINNER,
    NEW_GAME
}

public class Main {
    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        Table table = new Table(2);

        table.addMoney(0, 500);
        table.addMoney(1, 500);

        gameState states = gameState.IDLE;

        boolean gameRunning = true;


        while(gameRunning){
            switch(states) {

                case IDLE:

                    boolean PlayExitOptionRunning = true;

                    while(PlayExitOptionRunning) {
                        System.out.println("[1]PLay  [2]Exit");
                        String PlayExitOption = scr.nextLine();
                        if (PlayExitOption == "1") {
                            if (table.seeMoney(0) >= 5 && table.seeMoney(1) >= 5) {
                                states = gameState.PREFLOP;
                            }
                        }

                        if (PlayExitOption == "2") {
                            gameRunning = false;
                        } else {
                            System.out.println("Please choose one valid choice");
                        }
                    }

                case PREFLOP:
                    table.drawCard(0, 2);
                    table.drawCard(1, 2);

                    table.addBet(0, 5);
                    table.addBet(1, 5);

                    System.out.println("Blind: ");

                    System.out.println("Your cards: ");
                    System.out.println(table.seeHand(0));

                    System.out.println("[1]Raise  [2]Call [3]Fold");

                    String options = scr.nextLine();

                    boolean RaiseCallFoldOptions = true;

                    while (RaiseCallFoldOptions) {
                        switch (options) {
                            case "1":

                                String raiseOpt = scr.nextLine();

                                if (Integer.parseInt(raiseOpt) > table.seeMoney(0) || Integer.parseInt(raiseOpt) <= 0) {
                                    System.out.println("Not enough money");

                                }
                                if (Integer.parseInt(raiseOpt) == table.seeMoney(0)) {
                                    System.out.println("All in!");
                                    //continue later

                                } else {
                                    table.addBet(0, Integer.parseInt(raiseOpt));
                                    table.addBet(1, Integer.parseInt(raiseOpt));
                                    System.out.println("Player 2 called!");
                                }


                            case "2":


                            case "3":


                            default:

                        }
                    }

                case FLOP:

                case TURN:

                case RIVER:

                case SHOWDOWN:

                case WINNER:

                case NEW_GAME:

            }
        }
    }
}



