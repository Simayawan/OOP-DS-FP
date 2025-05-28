import com.sun.jdi.IntegerValue;

import java.nio.file.ClosedWatchServiceException;
import java.util.Arrays;
import java.util.Scanner;

enum gameState{ // To represent game states
    IDLE, //idle state
    PREFLOP, // first round of betting without table cards being revealed
    FLOP, // second round of betting with 3 cards revealed
    TURN, // third round of betting with 1 more card revealed
    RIVER, // fourth round of betting with the final card being reveled
    SHOWDOWN, // the final round where the player and the opponent cards are compared to the table hand
    NEW_GAME, // gives 2 choices, to play again or to quit, but both resets every value to give a clean slate for another game.
    NEW_GAME_ALL_IN
}

public class Main {
    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in); // initializes the scanner for the inputs later on
        Table table = new Table(2); // initializes the players, in this case it's 2

        table.addMoney(0, 500); // initializes the money for both player 1 and 2, in this case it's 500 each
        table.addMoney(1, 500);

        gameState states = gameState.IDLE; // initializes the default game state of the poker game, which is IDLE in this case, where nothing happens

        boolean gameRunning = true; // defines gameRunning as true for the purpose of the while loop


        while(gameRunning){ //while loop for the entire game
            switch(states) { //switch case to handle each game state

                case IDLE:

                    boolean PlayExitOptionRunning = true;

                    while(PlayExitOptionRunning) {
                        System.out.println("[1]PLay  [2]Exit");
                        String PlayExitOption = scr.nextLine();

                        switch (PlayExitOption) { // switch case to handle the play or exit option
                            case "1":
                                if (table.seeMoney(0) >= 5 && table.seeMoney(1) >= 5) { // makes sure that both players have money to gamble for the small pot and later
                                    PlayExitOptionRunning = false;
                                    states = gameState.PREFLOP;
                                    break;
                                }

                            case "2": // exits game
                                PlayExitOptionRunning = false;
                                gameRunning = false;
                                break;

                            default: // default case to handle unexpected inputs
                                System.out.println("Please choose one valid choice");
                                break;
                        }
                    }

                    break;

                case PREFLOP:
                    table.updateRanking();

                    //give two cards each for both players
                    table.drawCard(0, 2);
                    table.drawCard(1, 2);

                    // makes both players give up 5 each to the bet for the mandatory bet in the beginning of the game
                    table.addBet(0, 5);
                    table.addBet(1, 5);

                    System.out.println("Blind: " + (table.seeBet(0) + table.seeBet(1))); // adds the bet from the 1st and 2nd player for the blind
                    System.out.println("Your money: " + table.seeMoney(0));
                    System.out.println("Opponent's money: " + table.seeMoney(1));
                    System.out.println(" ");
                    System.out.println("Your cards: ");
                    System.out.println(table.seeHand(0));

                    System.out.println("[1]Raise  [2]Check [3]Fold");

                    String options = scr.nextLine();

                    boolean RaiseCallFoldOptions = true;

                    while (RaiseCallFoldOptions) { // switch case to handle the Raise, Fold, Call options
                        switch (options) {
                            case "1":
                                System.out.println("Your money: " + table.seeMoney(0));
                                System.out.println("Opponent's money: " + table.seeMoney(1));
                                System.out.println("Input your desired amount to bet: ");
                                String raiseOpt = scr.nextLine();

                                //makes sure the player doesn't bet more money than what he has and handles negative values
                                if (Integer.parseInt(raiseOpt) > table.seeMoney(0) || Integer.parseInt(raiseOpt) <= 0) {
                                    System.out.println("Not enough money");

                                }
                                // makes sure that if the player bets all of his money it would initiate an all in
                                if (Integer.parseInt(raiseOpt) == table.seeMoney(0)) {
                                    System.out.println("All in!");

                                    table.revealCard(5);

                                    table.addBet(0, Integer.parseInt(raiseOpt));
                                    table.addBet(1, Integer.parseInt(raiseOpt));

                                    RaiseCallFoldOptions = false;
                                    states = gameState.SHOWDOWN;

                                    // handles normal situations where the player bets money that is within the player money amount.
                                } else {
                                    table.addBet(0, Integer.parseInt(raiseOpt));
                                    table.addBet(1, Integer.parseInt(raiseOpt));
                                    System.out.println("Player 2 called!"); // reactionary AI for the 2nd player
                                    RaiseCallFoldOptions = false;
                                    states = gameState.FLOP;
                                }
                                break;


                            case "2": // the players doesnt make any bets
                                RaiseCallFoldOptions = false;
                                states = gameState.FLOP;
                                break;



                            case "3": // player folds
                                System.out.println("You Folded!");

                                table.addMoney(1, (table.seeBet(0) + table.seeBet(1)));

                                RaiseCallFoldOptions = false;
                                states = gameState.NEW_GAME;
                                break;

                            default: // default case to handle unexpected inputs
                                System.out.println("Please enter a valid input.");
                                break;
                        }
                    }
                    break;

                case FLOP:
                    table.updateRanking();
                    System.out.println("Bet: " + (table.seeBet(0) + table.seeBet(1)));
                    System.out.println("Your money: " + table.seeMoney(0));
                    System.out.println("Opponent's money: " + table.seeMoney(1));

                    System.out.println(" ");

                    System.out.println("Table cards: ");
                    table.revealCard(3);
                    System.out.println(table.seeTable());

                    System.out.println(" ");

                    System.out.println("Your cards: ");
                    System.out.println(table.seeHand(0));

                    System.out.println("[1]Raise  [2]Check [3]Fold");

                    String flopOptions = scr.nextLine();

                    boolean RaiseCallFoldOptionsFlop = true;

                    while (RaiseCallFoldOptionsFlop) {
                        switch (flopOptions) {
                            case "1":
                                System.out.println("Your money: " + table.seeMoney(0));
                                System.out.println("Opponent's money: " + table.seeMoney(1));
                                System.out.println("Input your desired amount to bet: ");
                                String raiseOpt = scr.nextLine();

                                if (Integer.parseInt(raiseOpt) > table.seeMoney(0) || Integer.parseInt(raiseOpt) <= 0) {
                                    System.out.println("Not enough money");

                                }
                                if (Integer.parseInt(raiseOpt) == table.seeMoney(0)) {
                                    System.out.println("All in!");
                                    RaiseCallFoldOptionsFlop = false;
                                    states = gameState.SHOWDOWN;

                                } else {
                                    table.addBet(0, Integer.parseInt(raiseOpt));
                                    table.addBet(1, Integer.parseInt(raiseOpt));
                                    System.out.println("Player 2 called!");
                                    RaiseCallFoldOptionsFlop = false;
                                    states = gameState.TURN;
                                }
                                break;


                            case "2":
                                RaiseCallFoldOptionsFlop = false;
                                states = gameState.TURN;
                                break;


                            case "3":
                                System.out.println("You Folded!");

                                table.addMoney(1, (table.seeBet(0) + table.seeBet(1)));

                                RaiseCallFoldOptionsFlop = false;
                                states = gameState.NEW_GAME;
                                break;

                            default:
                                System.out.println("Please enter a valid input.");
                                break;
                        }
                    }
                    break;

                case TURN:
                    table.updateRanking();
                    table.revealCard(1);
                    System.out.println("Bet: " + (table.seeBet(0) + table.seeBet(1)));
                    System.out.println("Your money: " + table.seeMoney(0));
                    System.out.println("Opponent's money: " + table.seeMoney(1));

                    System.out.println(" ");

                    System.out.println("Table cards: ");
                    System.out.println(table.seeTable());

                    System.out.println(" ");

                    System.out.println("Your cards: ");
                    System.out.println(table.seeHand(0));

                    System.out.println("[1]Raise  [2]Check [3]Fold");

                    String turnOptions = scr.nextLine();

                    boolean RaiseCallFoldOptionsTurn = true;

                    while (RaiseCallFoldOptionsTurn) {
                        switch (turnOptions) {
                            case "1":
                                System.out.println("Your money: " + table.seeMoney(0));
                                System.out.println("Opponent's money: " + table.seeMoney(1));
                                System.out.println("Input your desired amount to bet: ");
                                String raiseOpt = scr.nextLine();

                                if (Integer.parseInt(raiseOpt) > table.seeMoney(0) || Integer.parseInt(raiseOpt) <= 0) {
                                    System.out.println("Not enough money");

                                }
                                if (Integer.parseInt(raiseOpt) == table.seeMoney(0)) {
                                    System.out.println("All in!");
                                    RaiseCallFoldOptionsTurn = false;
                                    states = gameState.SHOWDOWN;

                                } else {
                                    table.addBet(0, Integer.parseInt(raiseOpt));
                                    table.addBet(1, Integer.parseInt(raiseOpt));
                                    System.out.println("Player 2 called!");
                                    RaiseCallFoldOptionsTurn = false;
                                    table.revealCard(2);
                                    states = gameState.RIVER;
                                }
                                break;


                            case "2":
                                RaiseCallFoldOptionsTurn = false;
                                states = gameState.RIVER;
                                break;


                            case "3":
                                System.out.println("You Folded!");

                                table.addMoney(1, (table.seeBet(0) + table.seeBet(1)));

                                RaiseCallFoldOptionsTurn = false;
                                states = gameState.NEW_GAME;
                                break;

                            default:
                                System.out.println("Please enter a valid input.");
                                break;
                        }
                    }

                    break;

                case RIVER:
                    table.updateRanking();
                    table.revealCard(1);
                    System.out.println("Bet: " + (table.seeBet(0) + table.seeBet(1)));
                    System.out.println("Your money: " + table.seeMoney(0));
                    System.out.println("Opponent's money: " + table.seeMoney(1));

                    System.out.println(" ");

                    System.out.println("Table cards: ");
                    System.out.println(table.seeTable());

                    System.out.println(" ");

                    System.out.println("Your cards: ");
                    System.out.println(table.seeHand(0));

                    System.out.println("[1]Raise  [2]Check [3]Fold");

                    String riverOptions = scr.nextLine();

                    boolean RaiseCallFoldOptionsRiver = true;

                    while (RaiseCallFoldOptionsRiver) {
                        switch (riverOptions) {
                            case "1":
                                System.out.println("Your money: " + table.seeMoney(0));
                                System.out.println("Opponent's money: " + table.seeMoney(1));
                                System.out.println("Input your desired amount to bet: ");
                                String raiseOpt = scr.nextLine();

                                if (Integer.parseInt(raiseOpt) > table.seeMoney(0) || Integer.parseInt(raiseOpt) <= 0) {
                                    System.out.println("Not enough money");

                                }
                                if (Integer.parseInt(raiseOpt) == table.seeMoney(0)) {
                                    System.out.println("All in!");
                                    RaiseCallFoldOptionsRiver = false;
                                    states = gameState.SHOWDOWN;

                                } else {
                                    table.addBet(0, Integer.parseInt(raiseOpt));
                                    table.addBet(1, Integer.parseInt(raiseOpt));
                                    System.out.println("Player 2 called!");
                                    RaiseCallFoldOptionsRiver = false;
                                    states = gameState.SHOWDOWN;
                                }
                                break;


                            case "2":
                                RaiseCallFoldOptionsRiver = false;
                                states = gameState.SHOWDOWN;
                                break;


                            case "3":
                                System.out.println("You Folded!");

                                table.addMoney(1, (table.seeBet(0) + table.seeBet(1)));

                                RaiseCallFoldOptionsRiver = false;
                                states = gameState.NEW_GAME;
                                break;

                            default:
                                System.out.println("Please enter a valid input.");
                                break;
                        }
                    }

                    break;

                case SHOWDOWN:
                    table.updateRanking();
                    System.out.println("Your money: " + table.seeMoney(0));
                    System.out.println("Opponent's money: " + table.seeMoney(1));

                    System.out.println(" ");

                    System.out.println("Table cards: ");
                    System.out.println(table.seeTable());

                    System.out.println(" ");

                    System.out.println("Your cards: ");
                    System.out.println(table.seeHand(0));

                    System.out.println(" ");
                    System.out.println("Opponent cards");
                    System.out.println(table.seeHand(1));

                    if(table.winner() == 0){
                        System.out.println("Winner is player 1!");
                        table.addMoney(0, (table.seeBet(0) + table.seeBet(1)));
                        states = gameState.NEW_GAME;
                    }
                    if(table.winner() == 1) {
                        System.out.println("Winner is player 2!");
                        table.addMoney(1, (table.seeBet(0) + table.seeBet(1)));
                        states = gameState.NEW_GAME;
                    }
                    break;

                case NEW_GAME:
                    System.out.println("[1]Quit [2]Play Again");
                    String QuitPlayOption = scr.nextLine();

                    switch(QuitPlayOption){
                        case "1":
                            gameRunning = false;
                            break;

                        case "2":

                            String loser;

                            if(table.winner() == 1){
                                loser = Integer.toString(0);
                            } else {
                                loser = Integer.toString(1);
                            }

                            if(table.seeMoney(0) > 5){
                                table.resetBet();
                                table.removeCard(0);
                                table.removeCard(1);
                                table.resetDeck();
                                states = gameState.PREFLOP;
                                break;
                            } else {
                                System.out.println("Player " + loser + "can't continue");
                                states = gameState.IDLE;
                                break;
                            }

                    }
                    break;
            }
        }
    }
    // hi :)
}
