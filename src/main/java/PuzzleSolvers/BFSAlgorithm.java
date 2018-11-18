package PuzzleSolvers;

import java.util.*;

import lombok.AllArgsConstructor;
import puzzleutils.Move;
import puzzleutils.PuzzleContainers.Puzzle;
import puzzleutils.PuzzleContainers.PuzzleNode;

@AllArgsConstructor
public class BFSAlgorithm implements PuzzleSolver {

    private int maxGraphDepth;

    @Override
    public List<Move> solve(Puzzle puzzle) {
        int graphDepth = 0;
        Queue<PuzzleNode> openNodes;
        Queue<PuzzleNode> nextLevelOpenNodes = new ArrayDeque<>();
        Set<PuzzleNode> processedNodes = new HashSet<>();
        List<Move> path = new LinkedList<>();

        PuzzleNode root = new PuzzleNode(puzzle, null, null);
        nextLevelOpenNodes.add(root);
        processedNodes.add(root);

        boolean isSolutionFound = false;
        while (nextLevelOpenNodes.size() > 0 && !isSolutionFound) {
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

        return path;
    }
}
