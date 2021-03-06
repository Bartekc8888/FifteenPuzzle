package puzzleutils.PuzzleContainers;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;
import puzzleutils.Move;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PuzzleNodeTest {

    @Test
    void contains() {
        List<Move> moveOrder = List.of(Move.LEFT, Move.RIGHT, Move.UP, Move.DOWN);
        int[] puzzleValues1 = { 0, 1, 2, 7,
                               8, 9, 12, 10,
                               13, 3, 6, 4,
                               15, 14, 11, 5};
        int[] puzzleValues2 = { 0, 1, 2, 7,
                                8, 9, 12, 10,
                                13, 3, 6, 4,
                                15, 14, 11, 5};

        Puzzle puzzle1 = Puzzle.createPuzzle(4, 4, puzzleValues1);
        Puzzle puzzle2 = Puzzle.createPuzzle(4, 4, puzzleValues2);
        PuzzleNode node1 = new PuzzleNode(puzzle1, null, null, moveOrder);
        PuzzleNode node2 = new PuzzleNode(puzzle2, null, null, moveOrder);

        HashSet<PuzzleNode> puzzles = new HashSet<>();
        puzzles.add(node1);

        assertEquals(node1, node2);
        assertTrue(puzzles.contains(node1));
        assertTrue(puzzles.contains(node2));
    }
}