import java.util.*;

public class Deck {
    //initialize
    private ArrayList<String> currentDeck = new ArrayList<String>();
    private final String[] deck = {"cb|2","cb|3","cb|4","cb|5","cb|6","cb|7","cb|8","cb|9","cb|10","cb|J","cb|Q","cb|K","cb|A",//club
                               "ht|2","ht|3","ht|4","ht|5","ht|6","ht|7","ht|8","ht|9","ht|10","ht|J","ht|Q","ht|K","ht|A",//heart
                               "sp|2","sp|3","sp|4","sp|5","sp|6","sp|7","sp|8","sp|9","sp|10","sp|J","sp|Q","sp|K","sp|A",//spade
                               "dd|2","dd|3","dd|4","dd|5","dd|6","dd|7","dd|8","dd|9","dd|10","dd|J","dd|Q","dd|K","dd|A"};//diamond
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

    //draw
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

    //reset deck
    public void reset(){ //reset the deck
        this.currentDeck.clear();
        this.currentDeck.addAll(Arrays.asList(deck));
        this.shuffle();
    }
    public void resetCheck(int i) {
        if ((i*2)+5 < currentDeck.size()) {
            reset();
        }
    }
}