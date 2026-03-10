package rps;

import java.util.Random;

public class RPSTests {

    private static int passed = 0;
    private static int failed = 0;

    private static void assertEquals(String name, Object expected, Object actual) {
        if (expected.equals(actual)) {
            System.out.println("  PASS: " + name);
            passed++;
        } else {
            System.out.println("  FAIL: " + name);
            System.out.println("        expected: " + expected);
            System.out.println("        actual:   " + actual);
            failed++;
        }
    }

    private static void assertTrue(String name, boolean condition) {
        if (condition) {
            System.out.println("  PASS: " + name);
            passed++;
        } else {
            System.out.println("  FAIL: " + name);
            failed++;
        }
    }

    private static void testStandardGameRules() {
        System.out.println("\n[StandardGameRules]");
        StandardGameRules rules = new StandardGameRules();

        assertEquals("Rock beats Scissors",
                Outcome.HUMAN_WINS, rules.determineOutcome(Move.ROCK, Move.SCISSORS));
        assertEquals("Scissors beats Paper",
                Outcome.HUMAN_WINS, rules.determineOutcome(Move.SCISSORS, Move.PAPER));
        assertEquals("Paper beats Rock",
                Outcome.HUMAN_WINS, rules.determineOutcome(Move.PAPER, Move.ROCK));

        assertEquals("Scissors loses to Rock",
                Outcome.COMPUTER_WINS, rules.determineOutcome(Move.SCISSORS, Move.ROCK));
        assertEquals("Rock loses to Paper",
                Outcome.COMPUTER_WINS, rules.determineOutcome(Move.ROCK, Move.PAPER));
        assertEquals("Paper loses to Scissors",
                Outcome.COMPUTER_WINS, rules.determineOutcome(Move.PAPER, Move.SCISSORS));

        assertEquals("Rock vs Rock is Draw",
                Outcome.DRAW, rules.determineOutcome(Move.ROCK, Move.ROCK));
        assertEquals("Paper vs Paper is Draw",
                Outcome.DRAW, rules.determineOutcome(Move.PAPER, Move.PAPER));
        assertEquals("Scissors vs Scissors is Draw",
                Outcome.DRAW, rules.determineOutcome(Move.SCISSORS, Move.SCISSORS));
    }

    private static void testScoreBoard() {
        System.out.println("\n[ScoreBoard]");
        ScoreBoard board = new ScoreBoard();

        assertEquals("Initial human wins = 0",    0, board.getHumanWins());
        assertEquals("Initial computer wins = 0", 0, board.getComputerWins());
        assertEquals("Initial draws = 0",         0, board.getDraws());

        board.recordOutcome(Outcome.HUMAN_WINS);
        assertEquals("Human wins incremented to 1",    1, board.getHumanWins());
        assertEquals("Computer wins unchanged after human win", 0, board.getComputerWins());
        assertEquals("Draws unchanged after human win",         0, board.getDraws());

        board.recordOutcome(Outcome.COMPUTER_WINS);
        assertEquals("Computer wins incremented to 1", 1, board.getComputerWins());
        assertEquals("Human wins unchanged after computer win", 1, board.getHumanWins());

        board.recordOutcome(Outcome.DRAW);
        assertEquals("Draws incremented to 1", 1, board.getDraws());

        assertEquals("toString format", "Human:1 Computer:1 Draws=1", board.toString());

        board.recordOutcome(Outcome.HUMAN_WINS);
        board.recordOutcome(Outcome.HUMAN_WINS);
        board.recordOutcome(Outcome.DRAW);
        assertEquals("Cumulative human wins = 3",  3, board.getHumanWins());
        assertEquals("Cumulative computer wins = 1", 1, board.getComputerWins());
        assertEquals("Cumulative draws = 2",       2, board.getDraws());
    }

    private static void testRandomComputerPlayer() {
        System.out.println("\n[RandomComputerPlayer]");
        RandomComputerPlayer player = new RandomComputerPlayer();

        assertTrue("getMove() is non-null", player.getMove() != null);

        Move move = player.getMove();
        assertTrue("getMove() returns a valid Move",
                move == Move.ROCK || move == Move.PAPER || move == Move.SCISSORS);

        boolean sawRock = false, sawPaper = false, sawScissors = false;
        for (int i = 0; i < 300; i++) {
            Move m = player.getMove();
            if (m == Move.ROCK)     sawRock     = true;
            if (m == Move.PAPER)    sawPaper    = true;
            if (m == Move.SCISSORS) sawScissors = true;
        }
        assertTrue("All three moves appear across 300 trials",
                sawRock && sawPaper && sawScissors);

        Random seeded = new Random(42);
        RandomComputerPlayer seededPlayer = new RandomComputerPlayer(seeded);
        Move seededMove = seededPlayer.getMove();
        assertTrue("Seeded player returns a valid Move",
                seededMove == Move.ROCK || seededMove == Move.PAPER || seededMove == Move.SCISSORS);
    }

    public static void main(String[] args) {
        System.out.println("============================");
        System.out.println("  RPS Test Suite");
        System.out.println("============================");

        testStandardGameRules();
        testScoreBoard();
        testRandomComputerPlayer();

        System.out.println("\n============================");
        System.out.printf("  Results: %d passed, %d failed%n", passed, failed);
        System.out.println("============================");

        if (failed > 0) {
            System.exit(1);
        }
    }
}
