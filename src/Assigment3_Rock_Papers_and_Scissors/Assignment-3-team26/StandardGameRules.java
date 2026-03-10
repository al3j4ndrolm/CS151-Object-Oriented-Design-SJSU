package rps;

public class StandardGameRules implements GameRules {

    @Override
    public Outcome determineOutcome(Move humanMove, Move computerMove) {
        if (humanMove == computerMove) {
            return Outcome.DRAW;
        }
        if (isHumanWin(humanMove, computerMove)) {
            return Outcome.HUMAN_WINS;
        }
        return Outcome.COMPUTER_WINS;
    }

    private boolean isHumanWin(Move human, Move computer) {
        return (human == Move.ROCK     && computer == Move.SCISSORS) ||
               (human == Move.SCISSORS && computer == Move.PAPER)    ||
               (human == Move.PAPER    && computer == Move.ROCK);
    }
}
