import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        Deck deck = new Deck();
//        Deck deck2 = new Deck();
//        System.out.println(deck);
//        System.out.println(deck.draw(5));
//        System.out.println(deck);
        Hand test = new Hand();
        test.addCard("e1");
        System.out.println(test);
        test.resetHand();
        System.out.println(test);
    }
}