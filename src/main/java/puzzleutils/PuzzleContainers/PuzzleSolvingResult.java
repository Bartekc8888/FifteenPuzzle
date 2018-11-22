package puzzleutils.PuzzleContainers;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import puzzleutils.Move;

@Data
@AllArgsConstructor
public class PuzzleSolvingResult {
    private List<Move> solveMoves;
    private PuzzleSolvingMetadata metadata;
}
