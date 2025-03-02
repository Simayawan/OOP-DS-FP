import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Deck {
    //initialize
    private ArrayList<String> currentDeck = new ArrayList<String>();
    protected String[] deck = {"cb2","cb3","cb4","cb5","cb6","cb7","cb8","cb9","cb10","cbJ","cbQ","cbK","cbA",//club
                               "ht2","ht3","ht4","ht5","ht6","ht7","ht8","ht9","ht10","htJ","htQ","htK","htA",//heart
                               "cr2","cr3","cr4","cr5","cr6","cr7","cr8","cr9","cr10","crJ","crQ","crK","crA",//clover
                               "dd2","dd3","dd4","dd5","dd6","dd7","dd8","dd9","dd10","ddJ","ddQ","ddK","ddA"};//diamond
    protected Random random = new Random();

    public Deck() {
        this.currentDeck.addAll(Arrays.asList(deck));
    }
    //getter
    public ArrayList<String> print(){ //return list of card in deck
        return this.currentDeck;
    }
    //methods
    private void remove(String card){ //remove specific card
        this.currentDeck.remove(card);
    }
    public String draw(){ //draw 1 card
        int index = random.nextInt(this.currentDeck.size());
        String drawnCard = this.currentDeck.get(index);
        this.remove(drawnCard);
        return drawnCard;
    }
    public String[] draw(int numOfCard){
        String[] temp = new String[numOfCard];
        for (int i=0; i<numOfCard ; i++) {
            temp[i] = draw();
        }
        return temp;
    }
    public void reset(){
        this.currentDeck.clear();
        this.currentDeck.addAll(Arrays.asList(deck));
    }
}
