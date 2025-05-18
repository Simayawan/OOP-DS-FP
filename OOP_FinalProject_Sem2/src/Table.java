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
    private int suitDecode(String x) { //get the suit value
        Map<String,Integer> ranking = Map.of(
                "dd",1,
                "cb",2,
                "ht",3,
                "sp",4
        );
        return ranking.get(x);
    }
    private int valueDecode(String x) { //get the card value
        Map<String, Integer> pic = Map.ofEntries(
            Map.entry("2", 2),
            Map.entry("3", 3),
            Map.entry("4", 4),
            Map.entry("5", 5),
            Map.entry("6", 6),
            Map.entry("7", 7),
            Map.entry("8", 8),
            Map.entry("9", 9),
            Map.entry("10",10),
            Map.entry("J", 11),
            Map.entry("Q", 12),
            Map.entry("K", 13),
            Map.entry("A", 14)
        );
        return pic.get(x);
    }
    public int[] cardDecoder(String code) { //turn code to usable value (remember, there is no error handling here)
        String[] split = code.split("\\|");
        int[] result = new int[2];
        result[0] = suitDecode(split[0]);
        result[1] = valueDecode(split[1]);
        return result;
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
    public void resetBet(int player) {
        players[player].resetBet();
    }

    //add money
    public void addMoney(int player, double amount) {
        players[player].addMoney(amount);
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