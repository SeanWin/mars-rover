package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MissionControlTest {
    PlateauSize plateauSize = new PlateauSize(5,5);
    Plateau plateau = new Plateau(plateauSize);
    MissionControl missionControl = new MissionControl(plateau);

    @Test
    void testAddRover_withinBounds() {
        Position position = new Position(1,2,Direction.N);
        Rover rover = new Rover(position);
        missionControl.addRover(rover);
        assertTrue(missionControl.rovers.contains(rover));
        assertEquals(missionControl.rovers.size(),1);
    }

    @Test
    void testAddRover_boundary() {
        Position position = new Position(5,5,Direction.N);
        Rover rover = new Rover(position);
        missionControl.addRover(rover);
        assertTrue(missionControl.rovers.contains(rover));
        assertEquals(missionControl.rovers.size(),1);
    }

    @Test
    void testAddRover_multipleRovers() {
        Position position1 = new Position(5,5,Direction.N);
        Position position2 = new Position(0,0,Direction.N);
        Position position3 = new Position(2,2,Direction.N);
        Rover rover1 = new Rover(position1);
        Rover rover2 = new Rover(position2);
        Rover rover3 = new Rover(position3);
        missionControl.addRover(rover1);
        missionControl.addRover(rover2);
        missionControl.addRover(rover3);
        assertTrue(missionControl.rovers.contains(rover1));
        assertTrue(missionControl.rovers.contains(rover2));
        assertTrue(missionControl.rovers.contains(rover3));
        assertEquals(missionControl.rovers.size(),3);
    }

    @Test
    void testAddRover_invalidPosition() {
        Position position = new Position(-1,-1,Direction.N);
        Rover rover = new Rover(position);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            missionControl.addRover(rover);
        });
        assertEquals("Rover position out of bounds", exception.getMessage());
    }

    @Test
    void testAddRover_PositionAlreadyOccupied() {
        Position position = new Position(1, 2, Direction.N);
        Rover rover1 = new Rover(position);
        Rover rover2 = new Rover(position);

        missionControl.addRover(rover1);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            missionControl.addRover(rover2);
        });
        assertEquals("Cannot deploy here, position already occupied", exception.getMessage());
    }

    @Test
    void testAddRover_PositionAlreadyOccupied_differentDirection() {
        Position position = new Position(1, 2, Direction.N);
        Position position2 = new Position(1, 2, Direction.S);
        Rover rover1 = new Rover(position);
        Rover rover2 = new Rover(position2);

        missionControl.addRover(rover1);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            missionControl.addRover(rover2);
        });
        assertEquals("Cannot deploy here, position already occupied", exception.getMessage());
    }

    @Test
    void testGetRoverPositions_EmptyList() {
        List<Position> positions = missionControl.getRoverPositions();
        assertEquals(0, positions.size()); // Expecting an empty list
    }

    @Test
    void testGetRoverPositions_SingleRover() {
        Position position1 = new Position(1, 2, Direction.N);
        Rover rover1 = new Rover(position1);
        missionControl.addRover(rover1);

        List<Position> positions = missionControl.getRoverPositions();
        assertEquals(1, positions.size());
        assertEquals(1, positions.get(0).getX());
        assertEquals(2, positions.get(0).getY());
        assertEquals(Direction.N, positions.get(0).getDirection());
    }

    @Test
    void testGetRoverPositions_MultipleRovers() {
        Position position1 = new Position(1, 2, Direction.N);
        Rover rover1 = new Rover(position1);
        Position position2 = new Position(3, 3, Direction.E);
        Rover rover2 = new Rover(position2);

        missionControl.addRover(rover1);
        missionControl.addRover(rover2);

        List<Position> positions = missionControl.getRoverPositions();
        assertEquals(2, positions.size());

        // First Rover
        assertEquals(1, positions.get(0).getX());
        assertEquals(2, positions.get(0).getY());
        assertEquals(Direction.N, positions.get(0).getDirection());

        // Second Rover
        assertEquals(3, positions.get(1).getX());
        assertEquals(3, positions.get(1).getY());
        assertEquals(Direction.E, positions.get(1).getDirection());
    }

    @Test
    void testExecuteInstructions_emptyInstructions() {
        Position startPosition = new Position(1, 1, Direction.N);
        Rover rover = new Rover(startPosition);

        Instruction[] instructions = {};
        Position finalPosition = missionControl.executeInstructions(rover, instructions);

        assertEquals(1, finalPosition.getX());
        assertEquals(1, finalPosition.getY());
        assertEquals(Direction.N, finalPosition.getDirection());
    }

    @Test
    void testExecuteInstructions_justTurn() {
        Position startPosition = new Position(1, 2, Direction.N);
        Rover rover = new Rover(startPosition);

        Instruction[] instructions = {Instruction.L,Instruction.R};
        Position finalPosition = missionControl.executeInstructions(rover, instructions);

        assertEquals(1, finalPosition.getX());
        assertEquals(2, finalPosition.getY());
        assertEquals(Direction.N, finalPosition.getDirection());
    }

    @Test
    void testExecuteInstructions_justMove() {
        Position startPosition = new Position(1, 2, Direction.N);
        Rover rover = new Rover(startPosition);

        Instruction[] instructions = {Instruction.M};
        Position finalPosition = missionControl.executeInstructions(rover, instructions);

        assertEquals(1, finalPosition.getX());
        assertEquals(3, finalPosition.getY());
        assertEquals(Direction.N, finalPosition.getDirection());
    }

    @Test
    void testExecuteInstructions_mixedInstructions() {
        Position startPosition = new Position(1, 2, Direction.N);
        Rover rover = new Rover(startPosition);

        Instruction[] instructions = {
                Instruction.L, Instruction.M,
                Instruction.L, Instruction.M,
                Instruction.L, Instruction.M,
                Instruction.L, Instruction.M,
                Instruction.M
        };

        Position finalPosition = missionControl.executeInstructions(rover, instructions);

        assertEquals(1, finalPosition.getX());
        assertEquals(3, finalPosition.getY());
        assertEquals(Direction.N, finalPosition.getDirection());
    }

    @Test
    void testExecuteInstructions_mixedInstructions2() {
        Position startPosition = new Position(2, 2, Direction.W);
        Rover rover = new Rover(startPosition);

        Instruction[] instructions = {
                Instruction.M, Instruction.R, Instruction.M, Instruction.L,
                Instruction.L, Instruction.M, Instruction.M, Instruction.R
        };
        Position finalPosition = missionControl.executeInstructions(rover, instructions);

        assertEquals(1, finalPosition.getX());
        assertEquals(1, finalPosition.getY());
        assertEquals(Direction.W, finalPosition.getDirection());
    }

    @Test
    void testExecuteInstructions_OutOfBounds() {
        Position startPosition = new Position(0, 0, Direction.S);
        Rover rover = new Rover(startPosition);

        Instruction[] instructions = {Instruction.M};

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            missionControl.executeInstructions(rover, instructions);
        });

        assertEquals("Rover out of bounds", exception.getMessage());
    }

    @Test
    void testExecuteInstructions_MovingToOccupiedSpot() {
        Position position1 = new Position(0, 0, Direction.N);
        Rover rover1 = new Rover(position1);
        missionControl.addRover(rover1);

        Position position2 = new Position(0, 1, Direction.N);
        Rover rover2 = new Rover(position2);
        missionControl.addRover(rover2);

        Instruction[] instructions = {Instruction.M};


        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            missionControl.executeInstructions(rover1, instructions);
        });

        assertEquals("Cannot move here, position already occupied", exception.getMessage());
    }

    @Test
    void testExecuteInstructions_MovingToOccupiedSpot_differentDirections() {
        Position position1 = new Position(0, 0, Direction.N);
        Rover rover1 = new Rover(position1);
        missionControl.addRover(rover1);

        Position position2 = new Position(0, 1, Direction.W);
        Rover rover2 = new Rover(position2);
        missionControl.addRover(rover2);

        Instruction[] instructions = {Instruction.M};


        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            missionControl.executeInstructions(rover1, instructions);
        });

        assertEquals("Cannot move here, position already occupied", exception.getMessage());
    }

}