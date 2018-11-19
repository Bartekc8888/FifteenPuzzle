package Tools.Converters;

import java.util.ArrayList;
import java.util.List;

import picocli.CommandLine.ITypeConverter;
import puzzleutils.Move;

public class MoveOrderConverter implements ITypeConverter<List<Move>> {

    @Override
    public List<Move> convert(String s) {
        char[] chars = s.toCharArray();
        List<Move> moveOrder = new ArrayList<>();

        for (char moveChar : chars) {
            switch (moveChar) {
                case 'L':
                    moveOrder.add(Move.LEFT);
                    break;
                case 'R':
                    moveOrder.add(Move.RIGHT);
                    break;
                case 'U':
                    moveOrder.add(Move.UP);
                    break;
                case 'D':
                    moveOrder.add(Move.DOWN);
                    break;
            }
        }

        return moveOrder;
    }
}
