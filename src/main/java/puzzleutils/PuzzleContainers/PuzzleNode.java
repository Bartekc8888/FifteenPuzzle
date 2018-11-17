package puzzleutils.PuzzleContainers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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

    public PuzzleNode(Puzzle state, PuzzleNode parent, Move creationMove) {
        children = new ArrayList<>();

        this.creationMove = creationMove;
        this.parent = parent;
        puzzleState = state;
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
}
