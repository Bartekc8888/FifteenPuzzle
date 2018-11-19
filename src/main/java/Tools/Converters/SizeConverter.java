package Tools.Converters;

import picocli.CommandLine.ITypeConverter;
import puzzleutils.PuzzleContainers.PuzzleSize;

public class SizeConverter implements ITypeConverter<PuzzleSize<Integer, Integer>> {
    @Override
    public PuzzleSize<Integer, Integer> convert(String s) {
        String[] split = s.split("x");

        if (split.length == 2) {
            return PuzzleSize.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        } else {
            return null;
        }
    }
}
