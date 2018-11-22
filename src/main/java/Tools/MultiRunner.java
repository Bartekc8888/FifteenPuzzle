package Tools;

import java.io.File;
import java.util.List;

import PuzzleSolvers.AlgorithmType;
import PuzzleSolvers.PuzzleSolver;
import PuzzleSolvers.PuzzleSolverFactory;
import puzzleutils.PuzzleContainers.Puzzle;
import puzzleutils.PuzzleContainers.PuzzleSolvingResult;
import puzzleutils.PuzzleHandling.PuzzleSerializer;
import puzzleutils.PuzzleHandling.ResultSerializer;

public class MultiRunner {
    public void run() {
        AlgorithmType algorithmType = AlgorithmType.ASTR;
        //List<String> listOfStrategies = List.of("RDUL", "RDLU", "DRUL", "DRLU", "LUDR", "LURD", "ULDR", "ULRD");
        List<String> listOfStrategies = List.of("hamm", "manh");
        String masterFolder = "AllResults/";

        for (String strategy : listOfStrategies) {
            String resultFolder = masterFolder + algorithmType + "/" + strategy + "/Results";
            String metadataFolder = masterFolder + algorithmType + "/" + strategy + "/Metadata";

            SolverArguments solverArguments = new SolverArguments();
            solverArguments.setAlgorithmType(algorithmType);
            solverArguments.setStrategy(strategy);

            File folder = new File("generated");
            File[] listOfFiles = folder.listFiles();

            for (File file : listOfFiles) {
                solverArguments.setSourceFile(file);
                solverArguments.setResultFile(new File(resultFolder + "/" + file.getName() + "Result"));
                solverArguments.setMetadataFile(new File(metadataFolder + "/" + file.getName() + "Metadata"));

                Puzzle puzzle = PuzzleSerializer.readFromFile(solverArguments.getSourceFile());
                PuzzleSolver puzzleSolver = PuzzleSolverFactory.createPuzzleSolver(solverArguments);

                PuzzleSolvingResult solvedResults = puzzleSolver.solve(puzzle);
                ResultSerializer.serializeResult(solvedResults, solverArguments.getResultFile(), solverArguments.getMetadataFile());
            }
        }

        System.exit(0);
    }
}
