package rps;

public interface GameRules {
    Outcome determineOutcome(Move humanMove, Move computerMove);
}
