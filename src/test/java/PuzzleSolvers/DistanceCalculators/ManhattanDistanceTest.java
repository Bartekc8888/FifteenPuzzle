package PuzzleSolvers.DistanceCalculators;

import org.junit.jupiter.api.Test;
import puzzleutils.PuzzleContainers.Puzzle;

import static org.junit.jupiter.api.Assertions.*;

class ManhattanDistanceTest {

    @Test
    void calculateDistance() {
        int[] puzzleValues = {4, 3, 6,
                              8, 7, 1,
                              0, 5, 2};
        int expectedDistance = 1 + 1 + 1 + 2 + 2 + 3 + 2 + 1 + 3;

        Puzzle puzzle = Puzzle.createPuzzle(3, 3, puzzleValues);
        ManhattanDistance distanceCalculator = new ManhattanDistance();

        int calculatedDistance = distanceCalculator.calculateDistance(puzzle);

        assertEquals(expectedDistance, calculatedDistance);
    }
}