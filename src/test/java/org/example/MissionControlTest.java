package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
}