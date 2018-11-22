package puzzleutils.PuzzleContainers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import puzzleutils.Move;

@Getter
@EqualsAndHashCode(of = {"puzzleState"})
public class PuzzleNode {
    private final PuzzleNode parent;
    private List<PuzzleNode> children;

    private final Move creationMove;
    private final Puzzle puzzleState;

    private List<Move> moveOrder;

    public PuzzleNode(Puzzle state, PuzzleNode parent, Move creationMove, List<Move> moveOrder) {
        children = new ArrayList<>();

        this.creationMove = creationMove;
        this.parent = parent;
        puzzleState = state;
        this.moveOrder = moveOrder;
    }

    public List<Move> tracePath() {
        List<Move> path = new LinkedList<>();

        PuzzleNode currentNode = this;
        while (currentNode.getParent() != null) {
            path.add(currentNode.getCreationMove());

            currentNode = currentNode.getParent();
        }

        Collections.reverse(path);
        return path;
    }

    public List<? extends PuzzleNode> getNextLevelNodes() {
        List<PuzzleNode> nextLevelNodes = new ArrayList<>();

        List<Move> possibleMoves = getPuzzleState().getPossibleMoves();
        possibleMoves = moveOrder.stream().filter(possibleMoves::contains).collect(Collectors.toList());
        for (Move move : possibleMoves) {
            Puzzle newPuzzleState = getPuzzleState().move(move);
            nextLevelNodes.add(new PuzzleNode(newPuzzleState, this, move, moveOrder));
        }

        return nextLevelNodes;
    }
}
