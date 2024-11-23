package org.MarsRover;

import static org.junit.jupiter.api.Assertions.*;

import org.MarsRover.Input.*;
import org.junit.jupiter.api.Test;


public class InputParserTest {

    @Test
    void parsePlateau_ValidInput() {
        PlateauSize plateauSize = InputParser.parsePlateau("5 5");
        assertEquals(5, plateauSize.getX());
        assertEquals(5, plateauSize.getY());

        PlateauSize plateauSize2 = InputParser.parsePlateau("10 20");
        assertEquals(10, plateauSize2.getX());
        assertEquals(20, plateauSize2.getY());

        PlateauSize plateauSize3 = InputParser.parsePlateau("    15     25    ");
        assertEquals(15, plateauSize3.getX());
        assertEquals(25, plateauSize3.getY());

    }

    @Test
    void parsePlateau_NullInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            InputParser.parsePlateau(null);
        });
        assertEquals("Input cannot be null.", exception.getMessage());
    }

    @Test
    void parsePlateau_EmptyInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            InputParser.parsePlateau("");
        });
        assertEquals("Input must be two space-separated positive integers.", exception.getMessage());

        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            InputParser.parsePlateau("   ");
        });
        assertEquals("Input must be two space-separated positive integers.",exception2.getMessage());
    }

    @Test
    void parsePlateau_ZeroDimensions() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            InputParser.parsePlateau("0 0");
        });
        assertEquals("Plateau dimensions must be greater than zero.", exception.getMessage());

        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            InputParser.parsePlateau("5 0");
        });
        assertEquals("Plateau dimensions must be greater than zero.", exception2.getMessage());

        Exception exception3 = assertThrows(IllegalArgumentException.class, () -> {
            InputParser.parsePlateau("0 5");
        });
        assertEquals("Plateau dimensions must be greater than zero.", exception3.getMessage());
    }

    @Test
    void parsePlateau_NegativeInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            InputParser.parsePlateau("-5 5");
        });
        assertEquals("Input must be two space-separated positive integers.", exception.getMessage());

        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            InputParser.parsePlateau("5 -5");
        });
        assertEquals("Input must be two space-separated positive integers.",exception2.getMessage());

        Exception exception3 = assertThrows(IllegalArgumentException.class, () -> {
            InputParser.parsePlateau("-5 -5");
        });
        assertEquals("Input must be two space-separated positive integers.",exception3.getMessage());
    }

    @Test
    void parsePlateau_InvalidFormats() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            InputParser.parsePlateau("55");
        });
        assertEquals("Input must be two space-separated positive integers.", exception.getMessage());

        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            InputParser.parsePlateau("5 5 5");
        });
        assertEquals("Input must be two space-separated positive integers.",exception2.getMessage());

        Exception exception3 = assertThrows(IllegalArgumentException.class, () -> {
            InputParser.parsePlateau("five five");
        });
        assertEquals("Input must be two space-separated positive integers.",exception3.getMessage());

        Exception exception4 = assertThrows(IllegalArgumentException.class, () -> {
            InputParser.parsePlateau("5,5");
        });
        assertEquals("Input must be two space-separated positive integers.",exception4.getMessage());
    }

    @Test
    void parsePlateau_OutOfRangeInput() {
        Exception exception = assertThrows(NumberFormatException.class, () -> {
            InputParser.parsePlateau("2147483648 5");
        });
        assertEquals("Input values too large", exception.getMessage());

        Exception exception2 = assertThrows(NumberFormatException.class, () -> {
            InputParser.parsePlateau("2147483648 5");
        });
        assertEquals("Input values too large",exception2.getMessage());
    }

    @Test
    void testParsePosition_ValidInput() {
        String input = "1 2 N";

        Position position = InputParser.parsePosition(input);

        assertNotNull(position);
        assertEquals(1, position.getX());
        assertEquals(2, position.getY());
        assertEquals(Direction.N, position.getDirection());
    }

    @Test
    void testParsePosition_InvalidDirection() {
        String input = "1 2 X";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            InputParser.parsePosition(input);
        });
        assertEquals("Invalid input", exception.getMessage());
    }

    @Test
    void testParsePosition_InvalidCoordinates() {
        String input = "A B N";

        Exception exception = assertThrows(NumberFormatException.class, () -> {
            InputParser.parsePosition(input);
        });
        assertTrue(exception.getMessage().contains("For input string"));
    }

    @Test
    void testParsePosition_MissingFields() {
        String input = "1 N";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            InputParser.parsePosition(input);
        });
        assertTrue(exception.getMessage().contains("Invalid input"));
    }

    @Test
    void testParseInstructions_ValidInput() {
        String input = "LMRMLMLMM";

        Instruction[] instructions = InputParser.parseInstructions(input);

        assertNotNull(instructions);
        assertEquals(9, instructions.length);
        assertEquals(Instruction.L, instructions[0]);
        assertEquals(Instruction.M, instructions[1]);
        assertEquals(Instruction.R, instructions[2]);
    }

    @Test
    void testParseInstructions_EmptyInput() {
        String input = "";

        Instruction[] commands = InputParser.parseInstructions(input);

        assertNotNull(commands);
        assertEquals(0, commands.length);
    }

    @Test
    void testParseInstructions_InvalidInstruction() {
        String input = "LMX";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            InputParser.parseInstructions(input);
        });
        assertEquals("No enum constant org.example.Instruction.X", exception.getMessage());
    }
}