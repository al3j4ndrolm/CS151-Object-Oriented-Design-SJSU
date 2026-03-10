package rps;

public class GameEngine {

    private static final int ROUNDS = 20;

    private final PlayerStrategy humanStrategy;
    private final PlayerStrategy computerStrategy;
    private final GameRules rules;
    private final ScoreBoard scoreBoard;

    public GameEngine(PlayerStrategy humanStrategy,
                      PlayerStrategy computerStrategy,
                      GameRules rules) {
        this.humanStrategy    = humanStrategy;
        this.computerStrategy = computerStrategy;
        this.rules            = rules;
        this.scoreBoard       = new ScoreBoard();
    }

    public void play() {
        for (int round = 1; round <= ROUNDS; round++) {
            System.out.print("Round " + round + " - ");

            Move humanMove    = humanStrategy.getMove();
            Move computerMove = computerStrategy.getMove();
            Outcome outcome   = rules.determineOutcome(humanMove, computerMove);

            scoreBoard.recordOutcome(outcome);

            System.out.println("You chose " + humanMove.getDisplayName() +
                               ". The computer chose " + computerMove.getDisplayName() +
                               ". " + outcome.getMessage());
            System.out.println("Score: " + scoreBoard);
            System.out.println();
        }

        printFinalResult();
    }

    private void printFinalResult() {
        System.out.println("=== Game Over! Final Score: " + scoreBoard + " ===");
        int h = scoreBoard.getHumanWins();
        int c = scoreBoard.getComputerWins();
        if (h > c) {
            System.out.println("You win the game!");
        } else if (c > h) {
            System.out.println("The computer wins the game!");
        } else {
            System.out.println("The game is a tie!");
        }
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }
}
