import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Table {
    private Deck deck;
    private Player[] players;
    private int[] ranking;

    public Table(int numPlayer) {
        deck = new Deck();
        players = new Player[numPlayer];
        ranking = new int[numPlayer];
        for (int i = 0; i < numPlayer; i ++) {
            players[i] = new Player();
            ranking[i] = i;
        }
    }

    public boolean higherSuit(String x, String y) { //check x is higher than y
        Dictionary<String, Integer> ranking = new Hashtable<>();
        ranking.put("dd",1);
        ranking.put("cr",2);
        ranking.put("ht",3);
        ranking.put("cb",4);
        return ranking.get(x) > ranking.get(y); //help me with the if same condition
    }
    public boolean higherNumber(String x, String y) {
        Dictionary<String, Integer> pic = new Hashtable<>();
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
        return true;
    }

    public void showRanking() {
        for (int i : ranking) {
            System.out.println(i + ". " + players[i]);
        }
    }
}
