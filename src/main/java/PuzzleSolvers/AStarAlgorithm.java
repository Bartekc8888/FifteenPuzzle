package PuzzleSolvers;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import PuzzleSolvers.DistanceCalculators.DistanceCalculator;
import lombok.AllArgsConstructor;
import puzzleutils.Move;
import puzzleutils.PuzzleContainers.OrderedPuzzleNode;
import puzzleutils.PuzzleContainers.Puzzle;
import puzzleutils.PuzzleContainers.PuzzleSolvingMetadata;
import puzzleutils.PuzzleContainers.PuzzleSolvingResult;

import static java.util.Comparator.comparing;

@AllArgsConstructor
public class AStarAlgorithm implements PuzzleSolver {

    private final int maxGraphDepth;
    private final DistanceCalculator distanceCalculator;

    @Override
    public PuzzleSolvingResult solve(Puzzle puzzle) {
        PuzzleSolvingMetadata metadata = new PuzzleSolvingMetadata();
        List<Move> moves = Collections.emptyList();
        OrderedPuzzleNode root = new OrderedPuzzleNode(puzzle, null, null, 0, distanceCalculator);

        PriorityQueue<OrderedPuzzleNode> priorityQueue = new PriorityQueue<>(comparing(OrderedPuzzleNode::getOverallCost));
        priorityQueue.add(root);

        metadata.startMeasuringTime();
        while (!priorityQueue.isEmpty()) {
            OrderedPuzzleNode puzzleNode = priorityQueue.remove();

            metadata.incrementVisitedStates();
            metadata.updateRecursionDepthIfGreater(puzzleNode.getDepth());

            if (puzzleNode.getPuzzleState().isResolved()) {
                moves = puzzleNode.tracePath();
                break;
            }

            if (puzzleNode.getDepth() > maxGraphDepth) {
                break;
            }

            List<OrderedPuzzleNode> nextLevelNodes = puzzleNode.getNextLevelNodes();
            priorityQueue.addAll(nextLevelNodes);

            metadata.addToProcessedStates(nextLevelNodes.size());
        }
        metadata.stopMeasuringTime();
        metadata.setSolutionLength(moves.size());

        return new PuzzleSolvingResult(moves, metadata);
    }
}
