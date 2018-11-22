package PuzzleSolvers;

import java.util.*;

import lombok.AllArgsConstructor;
import puzzleutils.Move;
import puzzleutils.PuzzleContainers.Puzzle;
import puzzleutils.PuzzleContainers.PuzzleNode;
import puzzleutils.PuzzleContainers.PuzzleSolvingMetadata;
import puzzleutils.PuzzleContainers.PuzzleSolvingResult;

@AllArgsConstructor
public class BFSAlgorithm implements PuzzleSolver {
    private int maxGraphDepth;
    private List<Move> moveOrder;

    @Override
    public PuzzleSolvingResult solve(Puzzle puzzle) {
        PuzzleSolvingMetadata metadata = new PuzzleSolvingMetadata();
        int graphDepth = 0;
        Queue<PuzzleNode> openNodes;
        Queue<PuzzleNode> nextLevelOpenNodes = new ArrayDeque<>();
        Set<PuzzleNode> processedNodes = new HashSet<>();
        List<Move> path = new LinkedList<>();

        PuzzleNode root = new PuzzleNode(puzzle, null, null, moveOrder);
        nextLevelOpenNodes.add(root);
        processedNodes.add(root);

        metadata.startMeasuringTime();
        boolean isSolutionFound = false;
        while (nextLevelOpenNodes.size() > 0 && !isSolutionFound) {
            metadata.incrementVisitedStates();

            if (graphDepth > maxGraphDepth) {
                break;
            }
            graphDepth++;

            openNodes = nextLevelOpenNodes;
            nextLevelOpenNodes = new ArrayDeque<>();

            while (openNodes.size() > 0 && !isSolutionFound) {
                PuzzleNode currentNode = openNodes.remove();

                List<? extends PuzzleNode> expandedNodes = currentNode.getNextLevelNodes();
                for (PuzzleNode node : expandedNodes) {
                    metadata.incrementProcessedStates();

                    if (node.getPuzzleState().isResolved()) {
                        isSolutionFound = true;
                        path = node.tracePath();
                    }

                    if (!processedNodes.contains(node)) {
                        nextLevelOpenNodes.add(node);
                        processedNodes.add(node);
                    }
                }
            }
        }
        metadata.stopMeasuringTime();
        metadata.setRecursionDepth(graphDepth);
        metadata.setSolutionLength(path.size());

        return new PuzzleSolvingResult(path, metadata);
    }
}
