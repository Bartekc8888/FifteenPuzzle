package puzzleutils.PuzzleHandling;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.Arrays;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import puzzleutils.exceptions.InvalidMovementException;

@Getter(AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
public class PuzzleEvalueator {
    private int[][] state;
    private int size;
    private int zeroX;
    private int zeroY;

    public PuzzleEvalueator(int[][] state) {
        this.state = state;
        this.size = state.length;
        this.findZero();
    }

    public PuzzleEvalueator(PuzzleEvalueator puzzle) {
        this.state = this.deepClone(puzzle.state);
        this.size = puzzle.size;
        this.zeroX = puzzle.zeroX;
        this.zeroY = puzzle.zeroY;
    }

    private int[][] deepClone(int[][] arr) {
        int[][] copy = new int[arr.length][];

        for(int i = 0; i < copy.length; ++i) {
            copy[i] = Arrays.copyOf(arr[i], arr[i].length);
        }

        return copy;
    }

    private void findZero() {
        for(int row = 0; row < this.state.length; ++row) {
            for(int column = 0; column < this.state.length; ++column) {
                if (this.state[row][column] == 0) {
                    this.zeroX = column;
                    this.zeroY = row;
                    return;
                }
            }
        }

    }

    private void move(char direction, PuzzleEvalueator puzzle) throws InvalidMovementException {
        int puzzleEmptyX = puzzle.getZeroX();
        int puzzleEmptyY = puzzle.getZeroY();
        String errorMessage = "Your solution went out of board";

        switch(direction) {
            case 'D':
                ++puzzleEmptyY;
                if (puzzleEmptyY >= puzzle.getSize()) {
                    throw new InvalidMovementException(errorMessage);
                }
                break;
            case 'L':
                --puzzleEmptyX;
                if (puzzleEmptyX < 0) {
                    throw new InvalidMovementException(errorMessage);
                }
                break;
            case 'R':
                ++puzzleEmptyX;
                if (puzzleEmptyX >= puzzle.getSize()) {
                    throw new InvalidMovementException(errorMessage);
                }
                break;
            case 'U':
                --puzzleEmptyY;
                if (puzzleEmptyY < 0) {
                    throw new InvalidMovementException(errorMessage);
                }
        }

        puzzle.getState()[puzzle.getZeroY()][puzzle.getZeroX()] = puzzle.getState()[puzzleEmptyY][puzzleEmptyX];
        puzzle.getState()[puzzleEmptyY][puzzleEmptyX] = 0;
        puzzle.setZeroY(puzzleEmptyY);
        puzzle.setZeroX(puzzleEmptyX);
    }

    boolean isSolved(char[] sol) throws InvalidMovementException {
        PuzzleEvalueator copy = new PuzzleEvalueator(this);
        int j = sol.length;

        for(int var5 = 0; var5 < j; ++var5) {
            char dir = sol[var5];
            this.move(dir, copy);
        }

        for(int i = 0; i < copy.getSize(); ++i) {
            for(j = 0; j < copy.getSize(); ++j) {
                if (i == copy.getSize() - 1 && j == copy.getSize() - 1) {
                    if (copy.getState()[i][j] != 0) {
                        return false;
                    }
                } else if (copy.getState()[i][j] != i * copy.getSize() + j + 1) {
                    return false;
                }
            }
        }

        return true;
    }

    public String toString() {
        StringBuilder description = new StringBuilder("Puzzle: \n");

        for (int[] aState : this.state) {
            for (int column = 0; column < this.state.length; ++column) {
                description.append(aState[column]);
                description.append(" ");
            }

            description.append("\n");
        }

        return description.toString();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            PuzzleEvalueator puzzle = (PuzzleEvalueator)o;
            if (this.size != puzzle.getSize()) {
                return false;
            } else if (this.zeroX != puzzle.getZeroX()) {
                return false;
            } else {
                return this.zeroY == puzzle.getZeroY() && Arrays.deepEquals(this.state, puzzle.getState());
            }
        } else {
            return false;
        }
    }

    public int hashCode() {
        int result = Arrays.deepHashCode(this.state);
        result = 31 * result + this.size;
        result = 31 * result + this.zeroX;
        result = 31 * result + this.zeroY;
        return result;
    }
}
