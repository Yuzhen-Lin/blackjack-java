package Blackjack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Poker {
    // 卡牌数量，不含大小王
    private static final int MAXNUMBEROFCARD = 52;

    private int indexOfCard;
    private List<Integer> cardSequence = new ArrayList<>(MAXNUMBEROFCARD);

    // 抽卡，若卡牌已经抽完返回-1
    public int hitCard() {
        if (indexOfCard < MAXNUMBEROFCARD) {
            return cardSequence.get(indexOfCard++);
        }
        return -1;
    }

    // 洗牌
    public void shuffle() {
        Map<Integer, Integer> usedCards = new HashMap<>(13);
        cardSequence = new ArrayList<>(MAXNUMBEROFCARD);
        int deck = MAXNUMBEROFCARD;
        indexOfCard = 0;

        while (deck > 0) {
            int currentCard = (int) (Math.random() * 13 + 1);

            Integer freq = usedCards.get(currentCard);
            if (freq == null || freq < 4) {
                usedCards.put(currentCard, (freq == null) ? 1 : freq + 1);
                cardSequence.add(currentCard);
                deck--;
            }

        }
    }
}
