package PuzzleSolvers;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import puzzleutils.Move;
import puzzleutils.PuzzleContainers.Puzzle;

import static org.junit.jupiter.api.Assertions.*;

class BFSAlgorithmTest {

    private static List<Move> moveOrder = List.of(Move.LEFT, Move.UP, Move.RIGHT, Move.DOWN);

    @Test
    void solve() {
        int[] puzzleValues = {4, 3, 6,
                              8, 7, 1,
                              0, 5, 2};
        int[] correctPuzzle = {1, 2, 3,
                               4, 5, 6,
                               7, 8, 0};
        Puzzle puzzle = Puzzle.createPuzzle(3, 3, puzzleValues);

        BFSAlgorithm bfsAlgorithm = new BFSAlgorithm(64, moveOrder);
        List<Move> result = bfsAlgorithm.solve(puzzle).getSolveMoves();

        assertNotNull(result);
        assertFalse(result.isEmpty());

        Puzzle solvedPuzzle = puzzle;
        for (Move move : result) {
            solvedPuzzle = solvedPuzzle.move(move);
        }

        int[] values = solvedPuzzle.getValues();
        assertArrayEquals(correctPuzzle, values);
    }

    @Test
    @Disabled("Very long test")
    void solveBig() {
        int[] puzzleValues = {0, 1, 2, 7,
                              8, 9, 12, 10,
                              13, 3, 6, 4,
                              15, 14, 11, 5};
        int[] correctPuzzle = {1, 2, 3, 4,
                               5, 6, 7, 8,
                               9, 10, 11, 12,
                               13, 14, 15, 0};
        Puzzle puzzle = Puzzle.createPuzzle(4, 4, puzzleValues);

        BFSAlgorithm bfsAlgorithm = new BFSAlgorithm(64, moveOrder);
        List<Move> result = bfsAlgorithm.solve(puzzle).getSolveMoves();

        assertNotNull(result);
        assertFalse(result.isEmpty());

        Puzzle solvedPuzzle = puzzle;
        for (Move move : result) {
            solvedPuzzle = solvedPuzzle.move(move);
        }

        int[] values = solvedPuzzle.getValues();
        assertArrayEquals(correctPuzzle, values);
    }
}