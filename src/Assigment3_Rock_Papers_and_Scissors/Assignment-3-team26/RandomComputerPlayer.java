package rps;

import java.util.Random;

public class RandomComputerPlayer implements PlayerStrategy {

    private final Random random;

    public RandomComputerPlayer() {
        this.random = new Random();
    }

    public RandomComputerPlayer(Random random) {
        this.random = random;
    }

    @Override
    public Move getMove() {
        Move[] moves = Move.values();
        return moves[random.nextInt(moves.length)];
    }
}
