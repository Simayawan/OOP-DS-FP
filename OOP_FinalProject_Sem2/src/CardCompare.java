import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CardCompare {
    private ArrayList<String> p1 = new ArrayList<>();
    private ArrayList<String> p2 = new ArrayList<>();

    //constructor
    public CardCompare(ArrayList<String> p1, ArrayList<String> p2, ArrayList<String> table) {
        this.p1 = p1;
        this.p1.addAll(table);
        this.p2 = p2;
        this.p2.addAll(table);
    }

    //get numerical value from string
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
    private int[] cardDecoder(String code) { //turn code to usable value (remember, there is no error handling here)
        String[] split = code.split("\\|");
        int[] result = new int[2];
        result[0] = suitDecode(split[0]);
        result[1] = valueDecode(split[1]);
        return result;
    }
    private List<int[]> decodeHand(ArrayList<String> hand) {
        List<int[]> decoded = new ArrayList<>();
        for (String card : hand) {
            decoded.add(cardDecoder(card));
        }
        return decoded;
    }

    //get combo
    private boolean pair(List<int[]> hand) { //pair
        for (int i =0; i<hand.size()-1; i++) {
            int check1 = hand.get(i)[1];
            for (int j = i+1; j<hand.size(); j++) {
                int check2 = hand.get(j)[1];
                if (check1 == check2) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean twoPair(List<int[]> hand) {
        int count = 0;
        for (int i =0; i<hand.size()-1; i++) {
            int check1 = hand.get(i)[1];
            for (int j = i+1; j<hand.size(); j++) {
                int check2 = hand.get(j)[1];
                if (check1 == check2) {
                    count += 1;
                }
            }
        }
        return count == 2;
    }
    private boolean threeOfAKind(List<int[]> hand) {
        int count = 0;
        for (int i =0; i<hand.size()-2; i++) {
            int check1 = hand.get(i)[1];
            for (int j = i+1; j<hand.size(); j++) {
                int check2 = hand.get(j)[1];
                if (check1 == check2) {
                    count += 1;
                }
            }
            if (count >= 3) {
                return true;
            }
            count = 0;
        }
        return false;
    }
    private boolean straight(List<int[]> hand) {
        //make list of unique value
        List<Integer> value = new ArrayList<>();
        for (int[] i : hand) {
            int temp = i[1];
            if (!value.contains(temp)) {
                value.add(temp);
            }
        }
        //check max num of straight
        int count =0,max = 0;
        for (int i=0; i<value.size()-1; i++) {
            int pointer = value.get(i);
            for (int j=i+1; j<value.size(); j++) {
                if (pointer+1 == value.get(j)) {
                    count +=1;
                    pointer +=1;
                }
            }
            if (count > max) {
                max = count;
            }
        }
        //check straight possible
        return max>=5;
    }
    private boolean flush(List<int[]> hand) {
        int[] num = {0,0,0,0};
        for (int[] i : hand) {
            num[i[0]-1] += 1;
        }
        for (int i : num) {
            if (i >= 5) {
                return true;
            }
        }
        return false;
    }
    private boolean fullHouse(List<int[]> hand) {
        int[] num = {0,0,0,0,0,0,0,0,0,0,0,0,0};
        for (int[] i : hand) {
            num[i[1]-2] += 1;
        }
        boolean three = false, two = false;
        for (int i : num) {
            if (i == 3) {
                three = true;
            } else if (i == 2) {
                two = true;
            }
        }
        return three && two;
    }
    private boolean fourOfAKind(List<int[]> hand) {
        int count = 0;
        for (int i =0; i<hand.size()-3; i++) {
            int check1 = hand.get(i)[1];
            for (int j = i+1; j<hand.size(); j++) {
                int check2 = hand.get(j)[1];
                if (check1 == check2) {
                    count += 1;
                }
            }
            if (count >= 4) {
                return true;
            }
            count = 0;
        }
        return false;
    }
    private boolean straightFlush(List<int[]> hand) {
        return (flush(hand) && straight(hand));
    }
    private boolean royalFlush(List<int[]> hand) {
        if (straightFlush(hand)) {
            List<Integer> cardValue = new ArrayList<>();
            for (int[] i : hand) {
                cardValue.add(i[1]);
            }
            List<Integer> check = new ArrayList<>() {
                {
                    add(10);
                    add(11);
                    add(12);
                    add(13);
                    add(14);
                }
            };
            return cardValue.contains(check);
        }
        return false;
    }

    //get ranking
    private int comboRanking(List<int[]> hand) { // Rank combo
        if (royalFlush(hand))
            return 10;
        if (straightFlush(hand))
            return 9;
        if (fourOfAKind(hand))
            return 8;
        if (fullHouse(hand))
            return 7;
        if (flush(hand))
            return 6;
        if (straight(hand))
            return 5;
        if (threeOfAKind(hand))
            return 4;
        if (twoPair(hand))
            return 3;
        if (pair(hand))
            return 2;
        return 1;
    }

    //get winner
    public int winner() {//output the winner (or 2 if tie)
        int R1 = comboRanking(decodeHand(p1)),R2 = comboRanking(decodeHand(p2));
        if (R1 > R2) {
            return 0; // P1 win
        } else if (R1 < R2) {
            return 1; // P2 win
        }
        return 2; // Tie
    }
}