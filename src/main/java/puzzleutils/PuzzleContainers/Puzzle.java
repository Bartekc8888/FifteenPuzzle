package puzzleutils.PuzzleContainers;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import puzzleutils.Move;
import puzzleutils.exceptions.InvalidPuzzle;

@Getter
@EqualsAndHashCode(of = {"values"})
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Puzzle {
    private final int width;
    private final int height;

    private final int[] values;
    private final int indexOfEmpty;

    private Puzzle(int width, int height, int[] values) {
        this(width, height, values, findIndexOfEmpty(values));
    }

    public static Puzzle createPuzzle(int width, int height, int[] values) {
        if (width * height != values.length) {
            throw new InvalidPuzzle("Given size is incorrect");
        }

        return new Puzzle(width, height, values);
    }

    public Puzzle move(Move move) {
        int[] valuesClone = values.clone();

        int newIndexOfEmpty = getMovedIndexOfEmpty(move);
        if (!isIndexOfEmptyValid(newIndexOfEmpty)) {
            throw new IllegalArgumentException("This move is not supported");
        }

        int tempValue = valuesClone[newIndexOfEmpty];
        valuesClone[newIndexOfEmpty] = 0;
        valuesClone[indexOfEmpty] = tempValue;

        return createPuzzle(width, height, valuesClone);
    }

    public boolean isResolved() {
        if (values[values.length - 1] != 0) {
            return false;
        }

        for (int i = 0; i < values.length - 1; i++) {
            for (int j = i; j < values.length - 1; j++) {
                if (values[i] > values[j]) {
                    return false;
                }
            }
        }

        return true;
    }

    List<Move> getPossibleMoves() {
        List<Move> validMoves = new ArrayList<>();

        for (Move direction : Move.values()) {
            if (isIndexOfEmptyValid(getMovedIndexOfEmpty(direction))) {
                validMoves.add(direction);
            }
        }

        return validMoves;
    }

    public int[] getValues() {
        return values.clone();
    }


    public int getValueAt(int index) {
        if (index < values.length) {
            return values[index];
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                builder.append(values[i * width + j]).append(" ");
            }

            builder.append("\n");
        }

        return builder.toString();
    }

    private static int findIndexOfEmpty(int[] values) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == 0) {
                return i;
            }
        }

        throw new InvalidPuzzle("Value 0 not found in puzzle.");
    }

    private int getMovedIndexOfEmpty(Move move) {
        int offset;

        switch (move) {
            case LEFT:
                offset = -1;
                break;
            case RIGHT:
                offset = 1;
                break;
            case UP:
                offset = -width;
                break;
            case DOWN:
                offset = width;
                break;
            default:
                throw new UnsupportedOperationException("Unrecognized move direction.");
        }

        return indexOfEmpty + offset;
    }

    private boolean isIndexOfEmptyValid(int newIndexOfEmpty) {
        return newIndexOfEmpty >= 0 && newIndexOfEmpty < (width * height) &&
               (newIndexOfEmpty / width == indexOfEmpty / width ||
                newIndexOfEmpty % width == indexOfEmpty % width);
    }
}
