package puzzleutils.PuzzleContainers;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import puzzleutils.Move;

import static org.junit.jupiter.api.Assertions.*;

class PuzzleTest {

    private Puzzle puzzle;
    private Puzzle correctPuzzle;

    @BeforeEach
    void setUp() {
        int[] puzzleValues = { 4, 3, 6,
                               8, 7, 1,
                               0, 5, 2};
        int[] correctPuzzleValues = {1, 2, 3,
                                    4, 5, 6,
                                    7, 8, 0};
        puzzle = Puzzle.createPuzzle(3, 3, puzzleValues);
        correctPuzzle = Puzzle.createPuzzle(3, 3, correctPuzzleValues);
    }

    @Test
    void move() {
        int[] movedValues = { 4, 3, 6,
                              8, 7, 1,
                              5, 0, 2};

        Puzzle moved = puzzle.move(Move.RIGHT);

        assertArrayEquals(movedValues, moved.getValues());
    }

    @Test
    void moveNotAllowed() {
        assertThrows(IllegalArgumentException.class,
                     ()-> puzzle.move(Move.LEFT));
    }

    @Test
    void isResolved() {
        assertTrue(correctPuzzle.isResolved());
    }

    @Test
    void getPossibleMoves() {
        List<Move> possibleMoves = puzzle.getPossibleMoves();

        assertEquals(2, possibleMoves.size());
        assertTrue(possibleMoves.contains(Move.UP));
        assertTrue(possibleMoves.contains(Move.RIGHT));
    }
}