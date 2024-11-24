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
    void parsePosition_validInputs() {
        assertAll(
                () -> {
                    Position position = InputParser.parsePosition("1 2 N");
                    assertEquals(1, position.getX());
                    assertEquals(2, position.getY());
                    assertEquals(Direction.N, position.getDirection());
                },
                () -> {
                    Position position = InputParser.parsePosition("   0    2    E   ");
                    assertEquals(0, position.getX());
                    assertEquals(2, position.getY());
                    assertEquals(Direction.E, position.getDirection());
                },
                () -> {
                    Position position = InputParser.parsePosition("1 2 w");
                    assertEquals(1, position.getX());
                    assertEquals(2, position.getY());
                    assertEquals(Direction.W, position.getDirection());
                }
        );
    }

    @Test
    void parsePosition_nullInput() {
        String input = null;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> InputParser.parsePosition(input));
        assertEquals("Input cannot be null.", exception.getMessage());
    }

    @Test
    void parsePosition_emptyInput() {
        String input = "";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> InputParser.parsePosition(input));
        assertEquals("Input must be two non-negative integers and a compass direction letter, all space-separated", exception.getMessage());
    }

    @Test
    void parsePosition_incorrectNumberOfFields() {
        assertAll(
                () -> {
                    String input = "1";
                    Exception exception = assertThrows(IllegalArgumentException.class, () -> InputParser.parsePosition(input));
                    assertEquals("Input must be two non-negative integers and a compass direction letter, all space-separated", exception.getMessage());
                },
                () -> {
                    String input = "1 N";
                    Exception exception = assertThrows(IllegalArgumentException.class, () -> InputParser.parsePosition(input));
                    assertEquals("Input must be two non-negative integers and a compass direction letter, all space-separated", exception.getMessage());
                },
                () -> {
                    String input = "1 1 2 N";
                    Exception exception = assertThrows(IllegalArgumentException.class, () -> InputParser.parsePosition(input));
                    assertEquals("Input must be two non-negative integers and a compass direction letter, all space-separated", exception.getMessage());
                }
        );
    }

    @Test
    void parsePosition_noSpaces() {
        String input = "12N";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> InputParser.parsePosition(input));
        assertEquals("Input must be two non-negative integers and a compass direction letter, all space-separated", exception.getMessage());
    }

    @Test
    void parsePosition_negativeCoordinates() {
        String input = "-1 2 N";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> InputParser.parsePosition(input));
        assertEquals("Input must be two non-negative integers and a compass direction letter, all space-separated", exception.getMessage());
    }

    @Test
    void parsePosition_nonNumericCoordinates() {
        String input = "x 2 N";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> InputParser.parsePosition(input));
        assertEquals("Input must be two non-negative integers and a compass direction letter, all space-separated", exception.getMessage());
    }

    @Test
    void parsePosition_invalidDirection() {
        String input = "1 2 x";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> InputParser.parsePosition(input));
        assertEquals("Direction must be one of: N, E, S, W", exception.getMessage());
    }

    @Test
    void parsePosition_largeCoordinate() {
        String input = "2147483648 2 N";
        Exception exception = assertThrows(NumberFormatException.class, () -> InputParser.parsePosition(input));
        assertEquals("Input coordinate values too large", exception.getMessage());
    }

    @Test
    void parseInstructions_validInputs() {
        assertAll(
                () -> assertArrayEquals(
                        new Instruction[]{Instruction.L},
                        InputParser.parseInstructions("L")
                ),
                () -> assertArrayEquals(
                        new Instruction[]{Instruction.L, Instruction.R},
                        InputParser.parseInstructions("LR")
                ),
                () -> assertArrayEquals(
                        new Instruction[]{Instruction.L, Instruction.R, Instruction.M},
                        InputParser.parseInstructions("LRM")
                ),
                () -> assertArrayEquals(
                        new Instruction[]{Instruction.L, Instruction.R, Instruction.M},
                        InputParser.parseInstructions("   L  R  M ")
                ),
                () -> assertArrayEquals(
                        new Instruction[]{Instruction.L, Instruction.R, Instruction.M},
                        InputParser.parseInstructions("lrm")
                )
        );
    }

    @Test
    void parseInstructions_nullInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> InputParser.parseInstructions(null));
        assertEquals("Input cannot be null", exception.getMessage());
    }

    @Test
    void parseInstructions_emptyInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> InputParser.parseInstructions(""));
        assertEquals("Input cannot be empty", exception.getMessage());
    }

    @Test
    void parseInstructions_invalidCharacters_shouldThrowException() {
        String input = "LRX";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> InputParser.parseInstructions(input));
        assertEquals("Instructions must consist of only the following letters: L, R, M", exception.getMessage());
    }
}