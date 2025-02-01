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

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                ", direction=" + direction +
                '}';
    }

    public Position getNextPosition(){
        Position nextPosition = new Position(this.getX(),this.getY(),this.getDirection());
        switch (direction) {
            case N -> nextPosition.setY(nextPosition.getY() + 1);
            case E -> nextPosition.setX(nextPosition.getX() + 1);
            case S -> nextPosition.setY(nextPosition.getY() - 1);
            case W -> nextPosition.setX(nextPosition.getX() - 1);
        }
        return nextPosition;
    }
}
