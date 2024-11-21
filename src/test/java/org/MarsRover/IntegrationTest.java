package org.MarsRover;

import org.MarsRover.Input.*;
import org.MarsRover.logic.MissionControl;
import org.MarsRover.logic.Plateau;
import org.MarsRover.logic.Rover;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {

    @Test
    void integrationTest_OneRover() {
        String plateauInput = "5 5";
        String roverPositionInput = "0 0 N";
        String roverInstructionsInput = "MRMLLMLM";

        PlateauSize plateauSize = InputParser.parsePlateau(plateauInput);
        Plateau plateau = new Plateau(plateauSize);
        Position initialPosition = InputParser.parsePosition(roverPositionInput);
        Instruction[] instructions = InputParser.parseInstructions(roverInstructionsInput);

        MissionControl missionControl = new MissionControl(plateau);
        Rover rover = new Rover(initialPosition);
        missionControl.addRover(rover);

        Position finalPosition = missionControl.executeInstructions(rover, instructions);
        Position expected = new Position(0,0, Direction.S);

        assertEquals(expected.getX(),finalPosition.getX());
        assertEquals(expected.getY(),finalPosition.getY());
        assertEquals(expected.getDirection(),finalPosition.getDirection());
    }

    @Test
    void integrationTest_TwoRovers(){
        String stringPlateau = "5 5";
        String stringPosition1 = "1 2 N";
        String stringInstructions1 = "LMLMLMLMM";
        String stringPosition2 = "3 3 E";
        String stringInstructions2 = "MMRMMRMRRM";
        String[] input = {stringPlateau,stringPosition1,stringInstructions1,stringPosition2,stringInstructions2};

        PlateauSize plateauSize = InputParser.parsePlateau(input[0]);
        Plateau plateau = new Plateau(plateauSize);
        Position position = InputParser.parsePosition(input[1]);
        Instruction [] instructions = InputParser.parseInstructions(input[2]);
        Position position2 = InputParser.parsePosition(input[3]);
        Instruction [] instructions2 = InputParser.parseInstructions(input[4]);

        MissionControl missionControl = new MissionControl(plateau);
        Rover rover = new Rover(position);
        Rover rover2 = new Rover(position2);
        missionControl.addRover(rover);
        missionControl.addRover(rover2);
        Position finalPosition1 = missionControl.executeInstructions(missionControl.getRovers().get(0),instructions);
        Position finalPosition2 = missionControl.executeInstructions(missionControl.getRovers().get(1),instructions2);
        Position expected1 = new Position(1,3,Direction.N);
        Position expected2 = new Position(5,1,Direction.E);

        assertEquals(expected1.getX(),finalPosition1.getX());
        assertEquals(expected1.getY(),finalPosition1.getY());
        assertEquals(expected1.getDirection(),finalPosition1.getDirection());

        assertEquals(expected2.getX(),finalPosition2.getX());
        assertEquals(expected2.getY(),finalPosition2.getY());
        assertEquals(expected2.getDirection(),finalPosition2.getDirection());
    }

    @Test
    void integrationTest_ThreeRovers() {
        String plateauInput = "5 5";

        String rover1PositionInput = "1 1 N";
        String rover1InstructionsInput = "MMRMM";

        String rover2PositionInput = "4 3 E";
        String rover2InstructionsInput = "MRMRM";

        String rover3PositionInput = "0 0 E";
        String rover3InstructionsInput = "MMMML";

        PlateauSize plateauSize = InputParser.parsePlateau(plateauInput);
        Plateau plateau = new Plateau(plateauSize);

        Position position1 = InputParser.parsePosition(rover1PositionInput);
        Instruction[] instructions1 = InputParser.parseInstructions(rover1InstructionsInput);

        Position position2 = InputParser.parsePosition(rover2PositionInput);
        Instruction[] instructions2 = InputParser.parseInstructions(rover2InstructionsInput);

        Position position3 = InputParser.parsePosition(rover3PositionInput);
        Instruction[] instructions3 = InputParser.parseInstructions(rover3InstructionsInput);

        MissionControl missionControl = new MissionControl(plateau);

        Rover rover1 = new Rover(position1);
        Rover rover2 = new Rover(position2);
        Rover rover3 = new Rover(position3);

        missionControl.addRover(rover1);
        missionControl.addRover(rover2);
        missionControl.addRover(rover3);

        Position finalPosition1 = missionControl.executeInstructions(rover1, instructions1);
        Position finalPosition2 = missionControl.executeInstructions(rover2, instructions2);
        Position finalPosition3 = missionControl.executeInstructions(rover3, instructions3);

        Position expected1 = new Position(3, 3, Direction.E);
        Position expected2 = new Position(4, 2, Direction.W);
        Position expected3 = new Position(4, 0, Direction.N);

        assertEquals(expected1.getX(),finalPosition1.getX());
        assertEquals(expected1.getY(),finalPosition1.getY());
        assertEquals(expected1.getDirection(),finalPosition1.getDirection());

        assertEquals(expected2.getX(),finalPosition2.getX());
        assertEquals(expected2.getY(),finalPosition2.getY());
        assertEquals(expected2.getDirection(),finalPosition2.getDirection());

        assertEquals(expected3.getX(),finalPosition3.getX());
        assertEquals(expected3.getY(),finalPosition3.getY());
        assertEquals(expected3.getDirection(),finalPosition3.getDirection());
    }



}