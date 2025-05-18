
import java.util.ArrayList;
import java.util.Scanner;

public class Queue {
    private ArrayList<String> Pair; // list for pairs
    private ArrayList<String> Solo; // list for solo
    private ArrayList<String> Playing; // list for currently playing players
    private int pairCount;

    public Queue(){
        this.Pair = new ArrayList<>();
        this.Solo = new ArrayList<>();
        this.Playing = new ArrayList<>();
        this.pairCount = 0;
    }

    public ArrayList<String> getPair() {
        return this.Pair;
    }

    public ArrayList<String> getSolo() {
        return this.Solo;
    }

    public ArrayList<String> getMix(){
        return this.Playing;
    }

    public int getPairCount() {
        return pairCount;
    }

    public int incrementPairCount(){
        pairCount++;
        return pairCount;
    }

    public int resetPairCount(){
        if(pairCount == 2){
            pairCount = 0;
        }
        return pairCount;
    }

    public void addPair(String player){
        Pair.add(player);
    }

    public void addSolo(String player){
        if (!Solo.isEmpty()){
            String existingSolo = Solo.remove(0);
            String combinedSolo = existingSolo + "&" + player;
            Pair.add(combinedSolo);
        } else{
            Solo.add(player);
        }
    }

    public void nextPlayer(){


        if(!Pair.isEmpty()){
            String FirstPair = Pair.get(0);
            Playing.add(FirstPair);

            incrementPairCount();

        }



    }

    public String currentQueue(){
        return Pair.get(0);
    }

}
