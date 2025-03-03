import java.util.*;

public class Deck {
    //initialize
    private ArrayList<String> currentDeck = new ArrayList<String>();
    private final String[] deck = {"cb2","cb3","cb4","cb5","cb6","cb7","cb8","cb9","cb10","cbJ","cbQ","cbK","cbA",//club
                               "ht2","ht3","ht4","ht5","ht6","ht7","ht8","ht9","ht10","htJ","htQ","htK","htA",//heart
                               "cr2","cr3","cr4","cr5","cr6","cr7","cr8","cr9","cr10","crJ","crQ","crK","crA",//clover
                               "dd2","dd3","dd4","dd5","dd6","dd7","dd8","dd9","dd10","ddJ","ddQ","ddK","ddA"};//diamond
    protected Random random = new Random();

    public Deck() {
        this.currentDeck.addAll(Arrays.asList(deck));
        this.shuffle();
    }
    //getter
    public ArrayList<String> getDeck() {
        return currentDeck;
    }
    //printing deck
    public String toString() {
        return currentDeck+"";
    }
    //methods
    private void remove(String card){ //remove specific card
        this.currentDeck.remove(card);
    }
    private void shuffle() { //shuffle card in deck for more rng
        Collections.shuffle(this.currentDeck);
    }
    public boolean higher(String x, String y) { //check x is higher than y
        Dictionary<String, Integer> ranking = new Hashtable<>();
        ranking.put("dd",1);
        ranking.put("cr",2);
        ranking.put("ht",3);
        ranking.put("cb",4);
        return ranking.get(x) > ranking.get(y); //help me with the if same condition
    }
    public String draw(){ //draw 1 card
        int index = random.nextInt(this.currentDeck.size());
        String drawnCard = this.currentDeck.get(index);
        this.remove(drawnCard);
        return drawnCard;
    }
    public ArrayList<String> draw(int numOfCard){ //draw x number of card
        ArrayList<String> temp = new ArrayList<String>();
        for (int i=0; i<numOfCard ; i++) {
            temp.add(draw());
        }
        return temp;
    }
    public void reset(){ //reset the deck
        this.currentDeck.clear();
        this.currentDeck.addAll(Arrays.asList(deck));
        this.shuffle();
    }
}
