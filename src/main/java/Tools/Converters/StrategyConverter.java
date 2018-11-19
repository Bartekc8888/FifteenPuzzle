package Tools.Converters;

import PuzzleSolvers.DistanceCalculators.DistanceCalculatorType;
import picocli.CommandLine.ITypeConverter;

public class StrategyConverter implements ITypeConverter<DistanceCalculatorType> {

    @Override
    public DistanceCalculatorType convert(String s) {
        if (s.equalsIgnoreCase("hamm")) {
            return DistanceCalculatorType.HAMMING;
        } else if (s.equalsIgnoreCase("manh")) {
            return DistanceCalculatorType.MANHATTAN;
        }

        return DistanceCalculatorType.MANHATTAN;
    }
}
