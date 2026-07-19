package Blackjack;

import java.util.List;

public class MessagePrinter {
    public static void printRules() {
        System.out.println("===== Blackjack Rules =====");
        System.out.println("1. Try to get your hand as close to 21 as possible without going over.");
        System.out.println(
                "2. Number cards (2-10) are worth their face value. J, Q, K are worth 10. An Ace is worth 1 or 11, whichever helps your hand more.");
        System.out.println(
                "3. At the start of a round, you and the dealer are each dealt two cards. Only one of the dealer's cards is shown.");
        System.out.println("4. On your turn, choose to Hit (draw another card) or Stand (keep your current hand).");
        System.out.println("5. If your total goes over 21, you bust and lose immediately.");
        System.out.println(
                "6. After you stand, the dealer reveals the hidden card and keeps hitting until reaching at least 17.");
        System.out.println("7. Whoever ends closer to 21 without busting wins. A tie is a push.");
        System.out.println("============================");
    }

    // 只露第一张牌，庄家暗牌阶段用，不显示总分
    public static void printHand(String label, List<Integer> cards) {
        System.out.println(label + ": " + cardToString(cards.get(0)) + " *");
    }

    // 显示完整手牌和当前总分
    public static void printHand(String label, List<Integer> cards, int score) {
        System.out.print(label + ": ");
        for (Integer card : cards) {
            System.out.printf("%s ", cardToString(card));
        }
        System.out.println("(Total: " + score + ")");
    }

    public static void printWinner(int result) {
        switch (result) {
            case 0:
                System.out.println("You win :)");
                break;
            case 1:
                System.out.println("You lose :(");
                break;
            case 2:
                System.out.println("Push!");
                break;
        }
    }

    private static String cardToString(int card) {
    return switch (card) {
        case 1  -> "A";
        case 11 -> "J";
        case 12 -> "Q";
        case 13 -> "K";
        default -> String.valueOf(card);
    };
}

}
