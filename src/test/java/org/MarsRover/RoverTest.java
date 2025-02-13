package org.MarsRover;

import org.MarsRover.Input.Direction;
import org.MarsRover.Input.Instruction;
import org.MarsRover.Input.Position;
import org.MarsRover.logic.Rover;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoverTest {

    @Test
    void testTurnLeftFromNorth() {
        Position startPosition = new Position(0, 0, Direction.N);
        Rover rover = new Rover(startPosition);
        rover.turn(Instruction.L);
        assertEquals(Direction.W, rover.getPosition().getDirection());
    }

    @Test
    void testTurnLeftFromEast() {
        Position startPosition = new Position(0, 0, Direction.E);
        Rover rover = new Rover(startPosition);
        rover.turn(Instruction.L);
        assertEquals(Direction.N, rover.getPosition().getDirection());
    }

    @Test
    void testTurnLeftFromSouth() {
        Position startPosition = new Position(0, 0, Direction.S);
        Rover rover = new Rover(startPosition);
        rover.turn(Instruction.L);
        assertEquals(Direction.E, rover.getPosition().getDirection());
    }

    @Test
    void testTurnLeftFromWest() {
        Position startPosition = new Position(0, 0, Direction.W);
        Rover rover = new Rover(startPosition);
        rover.turn(Instruction.L);
        assertEquals(Direction.S, rover.getPosition().getDirection());
    }

    @Test
    void testTurnRightFromNorth() {
        Position startPosition = new Position(0, 0, Direction.N);
        Rover rover = new Rover(startPosition);
        rover.turn(Instruction.R);
        assertEquals(Direction.E, rover.getPosition().getDirection());
    }

    @Test
    void testTurnRightFromEast() {
        Position startPosition = new Position(0, 0, Direction.E);
        Rover rover = new Rover(startPosition);
        rover.turn(Instruction.R);
        assertEquals(Direction.S, rover.getPosition().getDirection());
    }

    @Test
    void testTurnRightFromSouth() {
        Position startPosition = new Position(0, 0, Direction.S);
        Rover rover = new Rover(startPosition);
        rover.turn(Instruction.R);
        assertEquals(Direction.W, rover.getPosition().getDirection());
    }

    @Test
    void testTurnRightFromWest() {
        Position startPosition = new Position(0, 0, Direction.W);
        Rover rover = new Rover(startPosition);
        rover.turn(Instruction.R);
        assertEquals(Direction.N, rover.getPosition().getDirection());
    }

    @Test
    void turn_testInvalidDirection() {
        Position startPosition = new Position(0, 0, null);
        Rover rover = new Rover(startPosition);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            rover.turn(Instruction.L);
        });
        assertEquals("Invalid direction", exception.getMessage());
    }

    @Test
    void testInvalidInstruction() {
        Position startPosition = new Position(0, 0, Direction.N);
        Rover rover = new Rover(startPosition);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            rover.turn(Instruction.M); // M is invalid for a turn
        });
        assertEquals("Invalid instruction", exception.getMessage());
    }

    @Test
    void testMoveNorth() {
        Position startPosition = new Position(0, 0, Direction.N);
        Rover rover = new Rover(startPosition);
        rover.move();
        assertEquals(0, rover.getPosition().getX());
        assertEquals(1, rover.getPosition().getY());
        assertEquals(Direction.N, rover.getPosition().getDirection());
    }

    @Test
    void testMoveEast() {
        Position startPosition = new Position(0, 0, Direction.E);
        Rover rover = new Rover(startPosition);
        rover.move();
        assertEquals(1, rover.getPosition().getX());
        assertEquals(0, rover.getPosition().getY());
        assertEquals(Direction.E, rover.getPosition().getDirection());
    }

    @Test
    void testMoveSouth() {
        Position startPosition = new Position(0, 1, Direction.S);
        Rover rover = new Rover(startPosition);
        rover.move();
        assertEquals(0, rover.getPosition().getX());
        assertEquals(0, rover.getPosition().getY());
        assertEquals(Direction.S, rover.getPosition().getDirection());
    }

    @Test
    void testMoveWest() {
        Position startPosition = new Position(1, 0, Direction.W);
        Rover rover = new Rover(startPosition);
        rover.move();
        assertEquals(0, rover.getPosition().getX());
        assertEquals(0, rover.getPosition().getY());
        assertEquals(Direction.W, rover.getPosition().getDirection());
    }

    @Test
    void move_testInvalidDirection() {
        Position startPosition = new Position(0, 0, null);
        Rover rover = new Rover(startPosition);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            rover.move();
        });
        assertEquals("Invalid direction", exception.getMessage());
    }

}
