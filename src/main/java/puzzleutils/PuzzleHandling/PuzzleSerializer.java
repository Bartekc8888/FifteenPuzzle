package puzzleutils.PuzzleHandling;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import puzzleutils.PuzzleContainers.Puzzle;
import puzzleutils.exceptions.PuzzleFormatException;

public class PuzzleSerializer {
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

    public static Puzzle readFromFile(String path) {
        File file = new File(path);
        return readFromFile(file);
    }

    public static Puzzle readFromFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String sizeLine = reader.readLine();

            List<Integer> puzzleSize = getValuesFromLine(sizeLine);
            if (puzzleSize.size() == 2) {
                Integer height = puzzleSize.get(0);
                Integer width = puzzleSize.get(1);

                int[] puzzleValues = new int[height * width];
                for (int i = 0; i < height; i++) {

                    String valuesLine = reader.readLine();
                    List<Integer> values = getValuesFromLine(valuesLine);
                    if (values.size() != width) {
                        throw new PuzzleFormatException("Parsed values count differ");
                    }

                    for (int j = 0; j < values.size(); j++) {
                        puzzleValues[i * width + j] = values.get(j);
                    }
                }

                return Puzzle.createPuzzle(width, height, puzzleValues);
            } else {
                throw new PuzzleFormatException("Could not parse size from file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static List<Integer> getValuesFromLine(String line) {
        String[] split = line.trim().split("\\s+");
        return Arrays.stream(split).map(Integer::parseInt).collect(Collectors.toList());
    }
}
