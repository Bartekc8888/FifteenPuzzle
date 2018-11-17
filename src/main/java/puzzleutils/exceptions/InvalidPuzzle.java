package puzzleutils.exceptions;

public class InvalidPuzzle extends RuntimeException {
    public InvalidPuzzle(String message) {
        super(message);
    }
}