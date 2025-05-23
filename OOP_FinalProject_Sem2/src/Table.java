import java.util.*;

public class Table {
    private Deck deck;
    private Player[] players;
    private int[] ranking;
    private Hand publicCard;

    //constructor
    public Table(int numPlayer) {
        deck = new Deck();
        players = new Player[numPlayer];
        ranking = new int[numPlayer];
        for (int i = 0; i < numPlayer; i ++) {
            players[i] = new Player();
            ranking[i] = i;
        }
        publicCard = new Hand();
    }
    public Table() {
        deck = new Deck();
        players = new Player[2];
        ranking = new int[2];
        for (int i = 0; i < 2; i ++) {
            players[i] = new Player();
            ranking[i] = i;
        }
    }
    
    //methods

    //decide winner
    public int winner() {
        if (players.length == 2) {
            CardCompare compare = new CardCompare(players[0].getHand(), players[1].getHand(), publicCard.getHand());
            return compare.winner();
        }
        return 0;
    }

    //update the ranking
    public void updateRanking() {
        for (int i=0; i< ranking.length-1;i++) {
            int max = ranking[i];
            for (int j=i+1;j< ranking.length;j++) {
                if (players[max].getMoney() < players[j].getMoney()) {
                    max = ranking[i];
                    int temp = ranking[i];
                    ranking[i] = ranking[j];
                    ranking[j] = temp;
                }
            }
        }
    }

    //bet
    public boolean addBet(int player,double amount) {
        return players[player].addBet(amount);
    }
    public void allIn(int player) {
        players[player].allIn();
    }
    public void resetBet() {
        for (int i: ranking) {
            players[i].resetBet();
        }
    }
    public double seeBet(int player) {return players[player].getBet();}

    //add money
    public void addMoney(int player, double amount) {
        players[player].addMoney(amount);
    }

    //see money
    public double seeMoney(int player){
        return players[player].getMoney();
    }

    //fold
    public void fold(int player) {
        if (players[player].getFold()) {
            players[player].resetFold();
        } else {
            players[player].fold();
        }
    }

    //card related stuff
    public void drawCard(int player, int amount) {
        if (amount == 1) {
            players[player].addCard(deck.draw());
        } else {
            players[player].addCard(deck.draw(amount));
        }
    }
    public void removeCard(int player) {
        players[player].removeHand();
    }
    public void resetCheck() {
        deck.resetCheck(2);
    }

    //see hand
    public ArrayList<String> seeHand(int player) {
        return players[player].getHand();
    }
    public ArrayList<String> seeTable() {
        return publicCard.getHand();
    }

    //reveal card
    public void revealCard() {
        publicCard.addCard(deck.draw());
    }
    public void revealCard(int amount) {
        publicCard.addCard(deck.draw(amount));
    }
    public void clearTable() {
        publicCard.resetHand();
    }

    //reset deck
    public void resetDeck() {
        deck.reset();
    }

    //testing
    public void showRanking() {
        for (int i : ranking) {
            System.out.println(i + ". " + players[i]);
        }
    }
}
