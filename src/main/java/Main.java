import PuzzleSolvers.PuzzleSolver;
import PuzzleSolvers.PuzzleSolverFactory;
import Tools.GeneratorArguments;
import Tools.SolverArguments;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;
import puzzleutils.PuzzleContainers.Puzzle;
import puzzleutils.PuzzleContainers.PuzzleSolvingResult;
import puzzleutils.PuzzleHandling.Generator;
import puzzleutils.PuzzleHandling.PuzzleSerializer;
import puzzleutils.PuzzleHandling.ResultSerializer;

@Slf4j
public class Main {

    public static void main(String[] args) {
        log.info("Program is starting...");

        if (args.length > 0 && args[0].equals("generate")) {
            log.info("try generating puzzle.");

            GeneratorArguments generatorArguments = new GeneratorArguments();
            new CommandLine(generatorArguments).setCaseInsensitiveEnumValuesAllowed(true).parse(args);

            Generator generator = new Generator(generatorArguments.getHeight(), generatorArguments.getMaxDepth());
            generator.generateToFile(generatorArguments.getOutputFile().getAbsolutePath());

            log.info("generating finished.");
        } else {
            log.info("try solving puzzle.");

            SolverArguments solverArguments = new SolverArguments();
            new CommandLine(solverArguments).setCaseInsensitiveEnumValuesAllowed(true).parse(args);

            Puzzle puzzle = PuzzleSerializer.readFromFile(solverArguments.getSourceFile());
            PuzzleSolver puzzleSolver = PuzzleSolverFactory.createPuzzleSolver(solverArguments);

            PuzzleSolvingResult solvedResults = puzzleSolver.solve(puzzle);
            ResultSerializer.serializeResult(solvedResults, solverArguments.getResultFile(), solverArguments.getMetadataFile());
        }
    }
}
