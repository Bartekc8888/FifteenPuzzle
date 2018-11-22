package PuzzleSolvers;

import puzzleutils.PuzzleContainers.Puzzle;
import puzzleutils.PuzzleContainers.PuzzleSolvingResult;

public interface PuzzleSolver {

    PuzzleSolvingResult solve(Puzzle puzzle);
}
