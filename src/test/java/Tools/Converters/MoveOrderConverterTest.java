package Tools.Converters;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import puzzleutils.Move;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoveOrderConverterTest {

    @Test
    void convert() {
        List<Move> expectedMoveOrder = Arrays.asList(Move.RIGHT, Move.DOWN, Move.UP, Move.LEFT);
        String moves = "RDUL";

        MoveOrderConverter moveOrderConverter = new MoveOrderConverter();
        List<Move> moveList = moveOrderConverter.convert(moves);

        assertEquals(expectedMoveOrder, moveList);
    }
}