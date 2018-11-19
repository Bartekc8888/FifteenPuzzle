package Tools;

import java.io.File;

import PuzzleSolvers.AlgorithmType;
import Tools.Converters.SizeConverter;
import lombok.Data;
import picocli.CommandLine.Parameters;
import puzzleutils.PuzzleContainers.PuzzleSize;

@Data
public class SolverArguments {
    @Parameters(index = "0", arity = "1", paramLabel = "ALGORITHM", description = "Algorithm type")
    private AlgorithmType algorithmType;

    @Parameters(index = "1", arity = "0..1", paramLabel = "STRATEGY",
                description = "Move order used in DFS and BFS algorithms or distance strategy used in A* algorithm")
    private String strategy;

    @Parameters(index = "2", arity = "1", paramLabel = "SIZE", description = "Puzzle size", converter = SizeConverter.class)
    private PuzzleSize<Integer, Integer> size;

    @Parameters(index = "3", arity = "1", paramLabel = "SOURCE", description = "Puzzle source file")
    private File sourceFile;

    @Parameters(index = "4", arity = "1", paramLabel = "RESULT", description = "File for puzzle solving steps")
    private File resultFile;

    @Parameters(index = "5", arity = "1", paramLabel = "METADATA", description = "File for solution metadata")
    private File metadataFile;
}
