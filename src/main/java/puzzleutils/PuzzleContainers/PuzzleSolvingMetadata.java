package puzzleutils.PuzzleContainers;

import lombok.Data;

@Data
public class PuzzleSolvingMetadata {
    private int solutionLength;
    private int visitedStates;
    private int processedStates;
    private int recursionDepth;
    private double executionTimeInMilliseconds;

    private long startTime;

    public void updateRecursionDepthIfGreater(int depth) {
        if (depth > recursionDepth) {
            recursionDepth = depth;
        }
    }

    public void incrementVisitedStates() {
        visitedStates++;
    }

    public void incrementProcessedStates() {
        processedStates++;
    }

    public void addToProcessedStates(int number) {
        processedStates += number;
    }

    public void startMeasuringTime() {
        startTime = System.nanoTime();
    }

    public void stopMeasuringTime() {
        if (startTime > 0) {
            executionTimeInMilliseconds = (System.nanoTime() - startTime) / 1_000_000.d;
        }
    }
}
