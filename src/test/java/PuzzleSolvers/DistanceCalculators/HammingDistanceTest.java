package PuzzleSolvers.DistanceCalculators;

import org.junit.jupiter.api.Test;
import puzzleutils.PuzzleContainers.Puzzle;

import static org.junit.jupiter.api.Assertions.*;

class HammingDistanceTest {

    @Test
    void calculateDistance() {
        int[] puzzleValues = {4, 3, 6,
                              8, 7, 1,
                              0, 5, 2};
        int expectedDistance = 9;

        Puzzle puzzle = Puzzle.createPuzzle(3, 3, puzzleValues);
        HammingDistance distanceCalculator = new HammingDistance();

        int calculatedDistance = distanceCalculator.calculateDistance(puzzle);

        assertEquals(expectedDistance, calculatedDistance);
    }
}