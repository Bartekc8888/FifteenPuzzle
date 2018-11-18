package puzzleutils.PuzzleContainers;

import java.util.ArrayList;
import java.util.List;

import PuzzleSolvers.DistanceCalculators.DistanceCalculator;
import lombok.Getter;
import puzzleutils.Move;

@Getter
public class OrderedPuzzleNode extends PuzzleNode {

    private int depth;
    private int differenceFromCorrect;
    private DistanceCalculator distanceCalculator;

    public OrderedPuzzleNode(Puzzle state, PuzzleNode parent, Move creationMove, int depth, DistanceCalculator distanceCalculator) {
        super(state, parent, creationMove);

        this.depth = depth;
        this.distanceCalculator = distanceCalculator;
        this.differenceFromCorrect = calculateDifferenceFromCorrect();
    }

    public int getOverallCost() {
        return depth + differenceFromCorrect;
    }

    private int calculateDifferenceFromCorrect() {
        return distanceCalculator.calculateDistance(getPuzzleState());
    }

    @Override
    public List<OrderedPuzzleNode> getNextLevelNodes() {
        List<OrderedPuzzleNode> nextLevelNodes = new ArrayList<>();

        List<Move> possibleMoves = getPuzzleState().getPossibleMoves();
        for (Move move : possibleMoves) {
            Puzzle newPuzzleState = getPuzzleState().move(move);
            nextLevelNodes.add(new OrderedPuzzleNode(newPuzzleState, this, move, depth + 1, distanceCalculator));
        }

        return nextLevelNodes;
    }
}
