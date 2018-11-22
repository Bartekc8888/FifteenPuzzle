package puzzleutils.PuzzleHandling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import puzzleutils.Move;
import puzzleutils.PuzzleContainers.PuzzleSolvingMetadata;
import puzzleutils.PuzzleContainers.PuzzleSolvingResult;

@Slf4j
public class ResultSerializer {

    public static void serializeResult(PuzzleSolvingResult result, File resultFile, File metadataFile) {
        saveResult(result.getSolveMoves(), resultFile);
        saveMetadata(result.getMetadata(), metadataFile);
    }

    private static void saveResult(List<Move> solveMoves, File resultFile) {
        resultFile.getParentFile().mkdirs();

        try (PrintWriter out = new PrintWriter(resultFile)) {
            for (Move move : solveMoves) {
                out.print(move.getDirection());
            }
        } catch (FileNotFoundException e) {
            log.error("Error while trying to save result", e);
        }
    }

    private static void saveMetadata(PuzzleSolvingMetadata metadata, File metadataFile) {
        metadataFile.getParentFile().mkdirs();

        try (PrintWriter out = new PrintWriter(metadataFile)) {
            out.print(metadata.getProcessedStates());
            out.print(metadata.getVisitedStates());
            out.print(metadata.getRecursionDepth());
            out.print(String.format( "%.3f", metadata.getExecutionTimeInMilliseconds()));
        } catch (FileNotFoundException e) {
            log.error("Error while trying to save result", e);
        }
    }
}
