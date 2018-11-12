package puzzleutils;

import lombok.Getter;

@Getter
public enum Move {
    LEFT("L"), RIGHT("R"), UP("U"), DOWN("D");

    String direction;

    Move(String direction) {
        this.direction = direction;
    }
}
