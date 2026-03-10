package rps;

public class ScoreBoard {

    private int humanWins;
    private int computerWins;
    private int draws;

    public void recordOutcome(Outcome outcome) {
        switch (outcome) {
            case HUMAN_WINS:    humanWins++;    break;
            case COMPUTER_WINS: computerWins++; break;
            case DRAW:          draws++;        break;
        }
    }

    public int getHumanWins()    { return humanWins;    }
    public int getComputerWins() { return computerWins; }
    public int getDraws()        { return draws;        }

    @Override
    public String toString() {
        return "Human:" + humanWins + " Computer:" + computerWins + " Draws=" + draws;
    }
}
