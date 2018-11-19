package puzzleutils.PuzzleContainers;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class PuzzleSize<F, S> {
    private F width;
    private S height;
}
