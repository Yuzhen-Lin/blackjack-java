package Blackjack;

public class ControlPanel {
    private int balance = 1000;

    public static void main(String[] args) {
        ControlPanel cp = new ControlPanel();
        cp.start();
    }

    private void start() {
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.load(); // 启动时读取文件

        System.out.println("Welcome to blackjack!");
        String username = "player";

        while (true) {
            try {
                int userInput = InputReader.readIntInrange("0. Exit\n1. Start game\n2. Rules\n3. Ranksing \n", 0, 3);
                switch (userInput) {
                    case 0 -> {
                        username = InputReader.readString("Please enter your name.", username);
                        leaderboard.addEntry(username, balance);  // 保存积分
                        System.out.println("Program has exited.");
                        return;
                    }
                    case 1 -> {
                        GameFlow game = new GameFlow(new Player(), new Dealer());
                        int bet = 100;
                        int currentBet = InputReader.readIntWithDefault(
                                "Place your bet (default " + bet + ", max " + balance + "): ",
                                1, balance, bet);

                        balance += game.startGame(currentBet);
                        System.out.println("Balance: " + balance);
                        if (balance <= 0) {
                            System.out.println("You're out of points! Game over.");
                            return;
                        }
                    }
                    case 2 -> {
                        MessagePrinter.printRules();
                    }
                    case 3 -> {
                        leaderboard.displayTopScores();  // 显示前十
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, Please try again!");
            }
        }
    }
}
