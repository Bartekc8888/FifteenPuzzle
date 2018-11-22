package PuzzleSolvers;

import java.util.List;

import PuzzleSolvers.DistanceCalculators.DistanceCalculator;
import PuzzleSolvers.DistanceCalculators.DistanceCalculatorType;
import PuzzleSolvers.DistanceCalculators.HammingDistance;
import PuzzleSolvers.DistanceCalculators.ManhattanDistance;
import Tools.Converters.MoveOrderConverter;
import Tools.Converters.StrategyConverter;
import Tools.SolverArguments;
import puzzleutils.Move;

public class PuzzleSolverFactory {

    public static PuzzleSolver createPuzzleSolver(SolverArguments arguments) {
        switch (arguments.getAlgorithmType()) {
            case BFS:
                return new BFSAlgorithm(30, getMoveOrder(arguments.getStrategy()));
            case DFS:
                return new DFSAlgorithm(30, getMoveOrder(arguments.getStrategy()));
            case ASTR:
                StrategyConverter strategyConverter = new StrategyConverter();
                DistanceCalculatorType distanceCalculatorType = strategyConverter.convert(arguments.getStrategy());
                DistanceCalculator distanceCalculator = createDistanceCalculator(distanceCalculatorType);

                return new AStarAlgorithm(30, distanceCalculator);
            default:
                throw new IllegalArgumentException("Algorithm not defined for given type.");
        }
    }

    private static DistanceCalculator createDistanceCalculator(DistanceCalculatorType distanceCalculatorType) {
        switch (distanceCalculatorType) {
            case HAMMING:
                return new HammingDistance();
            case MANHATTAN:
                return new ManhattanDistance();
            default:
                throw new IllegalArgumentException("Distance calculator not defined for given type.");
        }
    }

    private static List<Move> getMoveOrder(String moves) {
        MoveOrderConverter moveOrderConverter = new MoveOrderConverter();
        return moveOrderConverter.convert(moves);
    }
}
