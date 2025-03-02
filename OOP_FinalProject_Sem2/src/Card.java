import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Card {
    //initialize
    private ArrayList<String> currentDeck = new ArrayList<String>();
    protected String[] deck = {"cb2","cb3","cb4","cb5","cb6","cb7","cb8","cb9","cb10","cbJ","cbQ","cbK","cbA",
                               "ht2","ht3","ht4","ht5","ht6","ht7","ht8","ht9","ht10","htJ","htQ","htK","htA",
                               "cr2","cr3","cr4","cr5","cr6","cr7","cr8","cr9","cr10","crJ","crQ","crK","crA",
                               "dd2","dd3","dd4","dd5","dd6","dd7","dd8","dd9","dd10","ddJ","ddQ","ddK","ddA"};
    protected Random random = new Random();

    public Card() {
        this.currentDeck.addAll(Arrays.asList(deck));
    }
    //getter
    public ArrayList<String> print(){
        return currentDeck;
    }
    //inner tools
    private void remove(String card){ //remove specific card
        this.currentDeck.remove(card);
    }
    private String draw(){
        int index = random.nextInt(currentDeck.size());
        String drawnCard = currentDeck.get(index);
        this.remove(drawnCard);
        return drawnCard;
    }
}
