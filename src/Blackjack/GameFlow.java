package Blackjack;

public class GameFlow {
    private final Poker poker = new Poker();
    private Player player;
    private Dealer dealer;
    private int playerScore;
    private int dealerScore;

    public GameFlow(Player player, Dealer dealer) {
        this.player = player;
        this.dealer = dealer;
    }

    public int startGame(int bet) {
        // 每场游戏开始前洗牌
        poker.shuffle();

        // 分别给玩家庄家发牌
        for (int i = 0; i < 2; i++) {
            player.hit(poker);
            dealer.hit(poker);
        }

        // 输出手牌
        MessagePrinter.printHand("dealer", dealer.hand);
        MessagePrinter.printHand("player", player.hand, player.getScore());

        // 初次计算，如果已经是21点则跳过回合
        playerScore = player.getScore();
        dealerScore = dealer.getScore();

        playerTurn();
        if (player.isBust())
            return -bet;
        dealerTurn();
        if (dealer.isBust())
            return bet;

        playerScore = player.getScore();
        dealerScore = dealer.getScore();

        int outcome = judgeWinner(playerScore, dealerScore);
        MessagePrinter.printWinner(outcome);
        return switch (outcome) {
            case 0 -> bet;
            case 1 -> -bet;
            default -> 0;
        };
    }

    private void playerTurn() {
        if (playerScore == 21) {
            System.out.println("Black jack!");
            return;
        }
        System.out.println("Player turn:");
        while (!player.isBust() && player.wantsHit()) {
            player.hit(poker);
            MessagePrinter.printHand("player", player.hand, player.getScore());
            if (player.isBust()) {
                MessagePrinter.printWinner(1);
                return;
            }
        }
    }

    private void dealerTurn() {
        MessagePrinter.printHand("dealer", dealer.hand, dealer.getScore());
        if (dealerScore == 21) {
            System.out.println("Black jack!");
            return;
        }
        System.out.println("Dealer turn:");
        while (!dealer.isBust() && dealer.wantsHit()) {
            dealer.hit(poker);
            MessagePrinter.printHand("dealer", dealer.hand, dealer.getScore());
            if (dealer.isBust()) {
                MessagePrinter.printWinner(0);
                return;
            }
        }
    }

    // 0代表玩家胜利，1代表电脑胜利，2代表平局
    private int judgeWinner(int playerScore, int dealerScore) {
        if (!player.isBust() && dealer.isBust())
            return 0;
        if (player.isBust() && !dealer.isBust())
            return 1;
        if (player.isBust() && dealer.isBust())
            return 2;
        if (playerScore > dealerScore)
            return 0;
        if (playerScore < dealerScore)
            return 1;
        return 2;
    }

}
