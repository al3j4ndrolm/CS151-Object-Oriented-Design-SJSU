package rps;

public enum Outcome {
    HUMAN_WINS("Human Wins!"),
    COMPUTER_WINS("Computer Wins!"),
    DRAW("Draw!");

    private final String message;

    Outcome(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
