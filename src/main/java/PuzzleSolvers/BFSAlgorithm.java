package PuzzleSolvers;

import java.util.*;

import puzzleutils.Move;
import puzzleutils.PuzzleContainers.Puzzle;
import puzzleutils.PuzzleContainers.PuzzleNode;

public class BFSAlgorithm {

    public static List<Move> solve(Puzzle puzzle) {
        PuzzleNode root = new PuzzleNode(puzzle, null, null);

        Queue<PuzzleNode> openNodes = new ArrayDeque<>();
        Set<PuzzleNode> closedNodes = new HashSet<>();
        List<Move> path = new LinkedList<>();

        openNodes.add(root);

        boolean isSolutionFound = false;
        while (openNodes.size() > 0 && !isSolutionFound) {
            PuzzleNode currentNode = openNodes.remove();
            closedNodes.add(currentNode);

            List<PuzzleNode> expandedNodes = expandNode(currentNode);
            for (PuzzleNode node : expandedNodes) {
                if (node.getPuzzleState().isResolved()) {
                    isSolutionFound = true;
                    path = tracePath(node);
                }

                if (!closedNodes.contains(node) && !openNodes.contains(node)) {
                    openNodes.add(node);
                }
            }
        }

        return path;
    }

    private static List<PuzzleNode> expandNode(PuzzleNode node) {
        List<PuzzleNode> children = new ArrayList<>();

        List<Move> possibleMoves = node.getPuzzleState().getPossibleMoves();
        for (Move move : possibleMoves) {
            Puzzle newPuzzleState = node.getPuzzleState().move(move);
            children.add(new PuzzleNode(newPuzzleState, node, move));
        }

        return children;
    }

    private static List<Move> tracePath(PuzzleNode node) {
        List<Move> path = new LinkedList<>();

        PuzzleNode currentNode = node;
        while (currentNode.getParent() != null) {
            path.add(currentNode.getCreationMove());

            currentNode = currentNode.getParent();
        }

        Collections.reverse(path);
        return path;
    }
}
