package Tools;

import java.io.File;

import lombok.Data;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Data
public class GeneratorArguments {
    @Parameters(index = "0", paramLabel = "MARKER", arity = "1", description = "Generate flag")
    private String generateString;

    @Option(names = {"-w", "--width"}, description = "Puzzle width")
    private int width;

    @Option(names = {"-h", "--height"}, description = "Puzzle height")
    private int height;

    @Option(names = {"-d", "--depth"}, description = "Maximum number of steps needed to solve")
    private int maxDepth;

    @Parameters(index = "1", paramLabel = "OUTPUT", arity = "1", description = "Puzzle outputFile")
    private File outputFile;
}
