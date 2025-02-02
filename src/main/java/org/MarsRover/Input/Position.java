package org.MarsRover.Input;

public class Position {
    private final int x;
    private final int y;
    private final Direction direction;

    public Position(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public Position getNextPosition(){
        return switch (direction) {
            case N -> new Position(x, y + 1, direction);
            case E -> new Position(x + 1, y, direction);
            case S -> new Position(x, y - 1, direction);
            case W -> new Position(x - 1, y, direction);
        };
    }
}
