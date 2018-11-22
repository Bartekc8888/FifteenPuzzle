package PuzzleSolvers;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import puzzleutils.Move;
import puzzleutils.PuzzleContainers.Puzzle;
import puzzleutils.PuzzleContainers.PuzzleNode;
import puzzleutils.PuzzleContainers.PuzzleSolvingMetadata;
import puzzleutils.PuzzleContainers.PuzzleSolvingResult;

@AllArgsConstructor
public class DFSAlgorithm implements PuzzleSolver {
    private int maxRecursionDepth;
    private List<Move> moveOrder;

    @Override
    public PuzzleSolvingResult solve(Puzzle puzzle) {
        PuzzleSolvingMetadata metadata = new PuzzleSolvingMetadata();
        PuzzleNode root = new PuzzleNode(puzzle, null, null, moveOrder);

        metadata.startMeasuringTime();
        Optional<PuzzleNode> node = solveRecursively(root, 1, metadata);
        metadata.stopMeasuringTime();

        List<Move> moves = node.map(PuzzleNode::tracePath).orElse(Collections.emptyList());
        metadata.setSolutionLength(moves.size());

        return new PuzzleSolvingResult(moves, metadata);
    }

    private Optional<PuzzleNode> solveRecursively(PuzzleNode node, int currentDepth, PuzzleSolvingMetadata metadata) {
        metadata.updateRecursionDepthIfGreater(currentDepth);
        metadata.incrementProcessedStates();
        metadata.incrementVisitedStates();

        if (node.getPuzzleState().isResolved()) {
            return Optional.of(node);
        }
        if (currentDepth > maxRecursionDepth) {
            return Optional.empty();
        }

        List<? extends PuzzleNode> nextLevelNodes = node.getNextLevelNodes();

        for (PuzzleNode puzzleNode : nextLevelNodes) {
            Optional<PuzzleNode> solvedRecursively = solveRecursively(puzzleNode, currentDepth + 1, metadata);
            metadata.incrementVisitedStates();

            if (solvedRecursively.isPresent()) {
                return solvedRecursively;
            }
        }

        return Optional.empty();
    }
}
