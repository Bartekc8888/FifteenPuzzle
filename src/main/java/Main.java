import Tools.GeneratorArguments;
import Tools.SolverArguments;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;
import puzzleutils.PuzzleHandling.Generator;

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
        }

    }
}
