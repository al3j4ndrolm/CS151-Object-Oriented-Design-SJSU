package rps;

import java.util.Scanner;

public class HumanPlayer implements PlayerStrategy {

    private final Scanner scanner;

    public HumanPlayer(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Move getMove() {
        while (true) {
            System.out.print("Choose (1=rock, 2=paper, 3=scissors): ");
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                if (choice >= 1 && choice <= 3) {
                    return Move.values()[choice - 1];
                }
            } else {
                scanner.next();
            }
            System.out.println("Invalid input. Please enter 1, 2, or 3.");
        }
    }
}
