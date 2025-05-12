import java.util.*;

public class Table {
    private Deck deck;
    private Player[] players;
    private int[] ranking;

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
        Map<String, Integer> ranking = new HashMap<>();
        ranking.put("dd",1);
        ranking.put("cr",2);
        ranking.put("ht",3);
        ranking.put("cb",4);
        return ranking.get(x); //help me with the if same condition
    }
    private int valueDecode(String x) { //get the card value
        Map<String, Integer> pic = new HashMap<>();
        pic.put("2",2);
        pic.put("3",3);
        pic.put("4",4);
        pic.put("5",5);
        pic.put("6",6);
        pic.put("7",7);
        pic.put("8",8);
        pic.put("9",9);
        pic.put("10",10);
        pic.put("J",11);
        pic.put("Q",12);
        pic.put("K",13);
        pic.put("A",14);
        return pic.get(x);
    }
    public int[] cardDecoder(String code) { //turn code to usable value
        String[] split = code.split("\\|");
        int[] result = new int[2];
        result[0] = suitDecode(split[0]);
        result[1] = valueDecode(split[1]);
        return result;
    }

    //testing
    public void showRanking() {
        for (int i : ranking) {
            System.out.println(i + ". " + players[i]);
        }
    }
}
