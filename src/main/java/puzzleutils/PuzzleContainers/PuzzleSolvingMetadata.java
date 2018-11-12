package puzzleutils.PuzzleContainers;

import lombok.Data;

@Data
public class PuzzleSolvingMetadata {
    private int solutionLength;
    private int visitedStates;
    private int processedStates;
    private int recursionDepth;
    private double executionTimeInMilliseconds;
}
