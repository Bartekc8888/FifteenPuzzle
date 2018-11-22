package PuzzleSolvers;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import puzzleutils.Move;
import puzzleutils.PuzzleContainers.Puzzle;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class DFSAlgorithmTest {
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

        DFSAlgorithm dfsAlgorithm = new DFSAlgorithm(18, moveOrder);
        List<Move> result = dfsAlgorithm.solve(puzzle).getSolveMoves();

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
    void solveBig() {
        int[] puzzleValues = {1, 2, 3, 4,
                              5, 7, 11, 8,
                              9, 6, 14, 12,
                              13, 10, 0, 15};
        int[] correctPuzzle = {1, 2, 3, 4,
                               5, 6, 7, 8,
                               9, 10, 11, 12,
                               13, 14, 15, 0};
        Puzzle puzzle = Puzzle.createPuzzle(4, 4, puzzleValues);

        DFSAlgorithm dfsAlgorithm = new DFSAlgorithm(20, moveOrder);
        List<Move> result = dfsAlgorithm.solve(puzzle).getSolveMoves();

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