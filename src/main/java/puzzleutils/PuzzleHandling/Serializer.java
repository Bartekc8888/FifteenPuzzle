package puzzleutils.PuzzleHandling;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import puzzleutils.PuzzleContainers.Puzzle;

public class Serializer {
    private static String newline = System.getProperty("line.separator");

    public static void printToFile(String path, Puzzle puzzle) {
        String filePath = path + "/generatedPuzzle.txt";
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        int[] puzzleValues = puzzle.getValues();
        try (PrintWriter out = new PrintWriter(file)) {
            int iterator = 0;
            out.print(puzzle.getWidth() + " " + puzzle.getHeight());
            out.print(newline);

            for (int puzzleValue : puzzleValues) {
                out.print(puzzleValue);
                ++iterator;

                if (iterator == puzzle.getWidth()) {
                    out.print(newline);
                    iterator = 0;
                } else {
                    out.print(" ");
                }
            }

        } catch (FileNotFoundException var18) {
            var18.printStackTrace();
        }
    }
}
