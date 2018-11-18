package PuzzleSolvers.DistanceCalculators;

import puzzleutils.PuzzleContainers.Puzzle;

public class HammingDistance implements DistanceCalculator {

    @Override
    public int calculateDistance(Puzzle puzzle) {
        int distance = 0;

        int[] puzzleValues = puzzle.getValues();
        for (int i = 0; i < puzzleValues.length - 1; i++) {
            if (puzzleValues[i] != (i + 1)) {
                distance++;
            }
        }

        if (puzzleValues[puzzleValues.length - 1] != 0) {
            distance++;
        }

        return distance;
    }
}
