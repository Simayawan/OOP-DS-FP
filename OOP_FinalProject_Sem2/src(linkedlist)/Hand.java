
import java.util.Arrays;
import java.util.LinkedList;

public class Hand {
    private LinkedList<String> currentHand = new LinkedList<>();
    private int numOfCard;
    public Hand() {
        this.numOfCard = 0;
    }
    public Hand(String[] list) {
        this.currentHand.addAll(Arrays.asList(list));
        this.numOfCard += list.length;
    }
    public String toString() {
        return ""+this.currentHand;
    }

    //getter
    public LinkedList<String> getHand() {
        return this.currentHand;
    }

    //add card to hand
    public void addCard(String card){
        this.currentHand.add(card);
        this.numOfCard += 1;
    }
    public void addCard(LinkedList<String> card){
        this.currentHand.addAll(card);
        this.numOfCard += card.size();
    }

    //remove card from hand
    public void removeCard(String card){
        if (this.currentHand.contains(card)) {
            this.currentHand.remove(card);
            this.numOfCard -= 1;
        }
    }
    public void removeCard(String[] card){
        boolean have = true;
        for (String i : card) {
            if (!this.currentHand.contains(i)) {
                have = false;
                break;
            }
        }
        if (have) {
            for (String i : card) {
                this.currentHand.remove(i);
                this.numOfCard -= 1;
            }
        }
    }
    public void resetHand() {
        if (!this.currentHand.isEmpty()) {
            this.currentHand.clear();
            this.numOfCard = 0;
        }
    }
}
