package PuzzleSolvers;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import PuzzleSolvers.DistanceCalculators.DistanceCalculator;
import lombok.AllArgsConstructor;
import puzzleutils.Move;
import puzzleutils.PuzzleContainers.OrderedPuzzleNode;
import puzzleutils.PuzzleContainers.Puzzle;

import static java.util.Comparator.comparing;

@AllArgsConstructor
public class AStarAlgorithm implements PuzzleSolver {

    private final int maxGraphDepth;
    private final DistanceCalculator distanceCalculator;

    @Override
    public List<Move> solve(Puzzle puzzle) {
        List<Move> moves = Collections.emptyList();
        OrderedPuzzleNode root = new OrderedPuzzleNode(puzzle, null, null, 0, distanceCalculator);

        PriorityQueue<OrderedPuzzleNode> priorityQueue = new PriorityQueue<>(comparing(OrderedPuzzleNode::getOverallCost));
        priorityQueue.add(root);

        while (!priorityQueue.isEmpty()) {
            OrderedPuzzleNode puzzleNode = priorityQueue.remove();

            if (puzzleNode.getPuzzleState().isResolved()) {
                moves = puzzleNode.tracePath();
                break;
            }

            if (puzzleNode.getDepth() > maxGraphDepth) {
                break;
            }

            List<OrderedPuzzleNode> nextLevelNodes = puzzleNode.getNextLevelNodes();
            priorityQueue.addAll(nextLevelNodes);
        }

        return moves;
    }

}
