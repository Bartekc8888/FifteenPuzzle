package PuzzleSolvers;

import java.util.*;

import lombok.AllArgsConstructor;
import puzzleutils.Move;
import puzzleutils.PuzzleContainers.Puzzle;
import puzzleutils.PuzzleContainers.PuzzleNode;

@AllArgsConstructor
public class BFSAlgorithm implements PuzzleSolver {

    private int maxRecursionDepth;

    @Override
    public List<Move> solve(Puzzle puzzle) {
        int recursionDepth = 0;
        Queue<PuzzleNode> openNodes;
        Queue<PuzzleNode> nextLevelOpenNodes = new ArrayDeque<>();
        Set<PuzzleNode> processedNodes = new HashSet<>();
        List<Move> path = new LinkedList<>();

        PuzzleNode root = new PuzzleNode(puzzle, null, null);
        nextLevelOpenNodes.add(root);
        processedNodes.add(root);

        boolean isSolutionFound = false;
        while (nextLevelOpenNodes.size() > 0 && !isSolutionFound) {
            if (recursionDepth > maxRecursionDepth) {
                break;
            }
            recursionDepth++;

            openNodes = nextLevelOpenNodes;
            nextLevelOpenNodes = new ArrayDeque<>();

            while (openNodes.size() > 0 && !isSolutionFound) {
                PuzzleNode currentNode = openNodes.remove();

                List<PuzzleNode> expandedNodes = expandNode(currentNode);
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

    private List<PuzzleNode> expandNode(PuzzleNode node) {
        List<PuzzleNode> children = new ArrayList<>();

        List<Move> possibleMoves = node.getPuzzleState().getPossibleMoves();
        for (Move move : possibleMoves) {
            Puzzle newPuzzleState = node.getPuzzleState().move(move);
            children.add(new PuzzleNode(newPuzzleState, node, move));
        }

        return children;
    }
}
