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

    //bet
    public boolean addBet(double amount) {
        if (amount <= this.money) {
            this.bet += amount;
            removeMoney(amount);
            return true;
        }
        return false;
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

    //interact with hand
    public Hand getHand() {
        return hand;
    }
    public void addCard(String[] cards) {
        this.hand.addCard(cards);
    }
    public void removeHand() {
        this.hand.resetHand();
    }
}
