package PuzzleSolvers.DistanceCalculators;

import puzzleutils.PuzzleContainers.Puzzle;

public class ManhattanDistance implements DistanceCalculator {

    @Override
    public int calculateDistance(Puzzle puzzle) {
        int distance = 0;

        int[] puzzleValues = puzzle.getValues();
        for (int i = 0; i < puzzleValues.length; i++) {
            distance += getDistanceForGivenIndex(i, puzzle);
        }

        return distance;
    }

    private int getDistanceForGivenIndex(int index, Puzzle puzzle) {
        int value = puzzle.getValueAt(index);
        int targetIndex = value == 0 ?
                          puzzle.getWidth() * puzzle.getHeight() - 1 :
                          value - 1;

        int xPosition = index % puzzle.getWidth();
        int yPosition = index / puzzle.getWidth();

        int xTarget = targetIndex % puzzle.getWidth();
        int yTarget = targetIndex / puzzle.getWidth();

        return Math.abs(xPosition - xTarget) + Math.abs(yPosition - yTarget);
    }
}
