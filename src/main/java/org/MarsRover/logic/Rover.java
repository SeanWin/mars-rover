package org.MarsRover.logic;

import org.MarsRover.Input.Instruction;
import org.MarsRover.Input.Position;

public class Rover {
    private Position position;

    public Rover(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void turn(Instruction instruction){
        if (position.getDirection() == null) {
            throw new IllegalArgumentException("Invalid direction");
        }

        switch (instruction) {
            case L -> position = new Position(position.getX(), position.getY(), position.getDirection().turnLeft());
            case R -> position = new Position(position.getX(), position.getY(), position.getDirection().turnRight());
            default -> throw new IllegalArgumentException("Invalid instruction");
            }
    }

    public void move() {

        if (position.getDirection() == null) {
            throw new IllegalArgumentException("Invalid direction");
        }
        position = position.getNextPosition();

    }

}
