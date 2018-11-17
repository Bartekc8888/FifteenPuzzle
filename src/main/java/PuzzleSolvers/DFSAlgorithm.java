package PuzzleSolvers;

import java.util.*;

import lombok.AllArgsConstructor;
import puzzleutils.Move;
import puzzleutils.PuzzleContainers.Puzzle;
import puzzleutils.PuzzleContainers.PuzzleNode;

@AllArgsConstructor
public class DFSAlgorithm implements PuzzleSolver {
    private int maxRecursionDepth;

    @Override
    public List<Move> solve(Puzzle puzzle) {
        PuzzleNode root = new PuzzleNode(puzzle, null, null);
        Optional<PuzzleNode> node = solveRecursively(root, 1);

        return node.map(PuzzleNode::tracePath).orElse(Collections.emptyList());
    }

    private Optional<PuzzleNode> solveRecursively(PuzzleNode node, int currentDepth) {
        if (node.getPuzzleState().isResolved()) {
            return Optional.of(node);
        }
        if (currentDepth > maxRecursionDepth) {
            return Optional.empty();
        }

        List<PuzzleNode> nextLevelNodes = new ArrayList<>();

        List<Move> possibleMoves = node.getPuzzleState().getPossibleMoves();
        for (Move move : possibleMoves) {
            Puzzle newPuzzleState = node.getPuzzleState().move(move);
            nextLevelNodes.add(new PuzzleNode(newPuzzleState, node, move));
        }

        for (PuzzleNode puzzleNode : nextLevelNodes) {
            Optional<PuzzleNode> solvedRecursively = solveRecursively(puzzleNode, currentDepth + 1);
            if (solvedRecursively.isPresent()) {
                return solvedRecursively;
            }
        }

        return Optional.empty();
    }
}
