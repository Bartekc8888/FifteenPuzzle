package PuzzleSolvers;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import puzzleutils.Move;
import puzzleutils.PuzzleContainers.Puzzle;

class BFSAlgorithmTest {

    @Test
    void solve() {
        int[] puzzleValues = {1, 2, 4,
                              3, 0, 5,
                              7, 6, 8};
        Puzzle puzzle = Puzzle.createPuzzle(3, 3, puzzleValues);

        List<Move> result = BFSAlgorithm.solve(puzzle);

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(14, result.size());
    }
}