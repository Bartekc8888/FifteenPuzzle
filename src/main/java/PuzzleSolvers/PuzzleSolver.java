package PuzzleSolvers;

import java.util.List;

import puzzleutils.Move;
import puzzleutils.PuzzleContainers.Puzzle;

public interface PuzzleSolver {

    List<Move> solve(Puzzle puzzle);
}
