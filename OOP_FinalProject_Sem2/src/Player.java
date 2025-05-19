import java.util.ArrayList;

public class Player{
    private boolean fold;
    private double money;
    private double bet;
    private Hand hand;

    //constructor
    public Player(){
        this.fold = false;
        this.money = 0;
        this.bet = 0;
        this.hand = new Hand();
    }
    public Player(double money){
        this.fold = false;
        if (money >= 0) {
            this.money = money;
        } else {
            this.money = 0;
        }
        this.bet = 0;
        this.hand = new Hand();
    }

    //money stuff
    public void addMoney(double amount) {
        this.money += amount;
    }
    public void removeMoney(double amount) {
        this.money -= amount;
    }
    public double getMoney(){
        return this.money;
    }

    //bet
    public boolean addBet(double amount) {
        if (amount <= this.money) {
            this.bet += amount;
            removeMoney(amount);
            return true;
        }
        return false;
    }
    public double getBet() {
        return bet;
    }
    public void allIn() {
        this.bet += this.money;
        this.money = 0;
    }
    public void resetBet() {
        this.bet = 0;
    }

    //fold
    public void fold() {
        fold = true;
    }
    public void resetFold() {
        fold = false;
    }
    public boolean getFold() {
        return fold;
    }

    //interact with hand
    public ArrayList<String> getHand() {
        return hand.getHand();
    }
    public void addCard(ArrayList<String> cards) {
        this.hand.addCard(cards);
    }
    public void addCard(String card) {
        this.hand.addCard(card);
    }
    public void removeHand() {
        this.hand.resetHand();
    }

    //display test
    public String toString() {
        return "Player(Money: " + money + ")";
    }
}