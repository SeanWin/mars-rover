package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class InputParserTest {

    @Test
    void testParsePlateau_ValidInput() {
        String input = "5 5";

        PlateauSize plateau = InputParser.parsePlateau(input);

        assertNotNull(plateau);
        assertEquals(5, plateau.getX());
        assertEquals(5, plateau.getY());
    }

    @Test
    void testParsePlateau_InvalidInput() {
        String input = "5";

        Exception exception = assertThrows(NumberFormatException.class, () -> {
            InputParser.parsePlateau(input);
        });
        assertTrue(exception.getMessage().contains("Invalid input"));
    }

    @Test
    void testParsePlateau_EmptyInput() {
        String input = "";

        Exception exception = assertThrows(NumberFormatException.class, () -> {
            InputParser.parsePlateau(input);
        });
        System.out.println(exception);
        assertTrue(exception.getMessage().contains("Invalid input"));
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