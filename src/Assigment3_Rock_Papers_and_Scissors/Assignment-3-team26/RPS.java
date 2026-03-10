package rps;

import java.util.Scanner;

public class RPS {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        PlayerStrategy human    = new HumanPlayer(scanner);
        PlayerStrategy computer = new RandomComputerPlayer();
        GameRules      rules    = new StandardGameRules();

        GameEngine engine = new GameEngine(human, computer, rules);
        engine.play();

        scanner.close();
    }
}
