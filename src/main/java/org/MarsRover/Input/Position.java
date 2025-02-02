package org.MarsRover.Input;

public class Position {
    private int x;
    private int y;
    private Direction direction;

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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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
