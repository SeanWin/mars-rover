package org.MarsRover.Input;

public class InputParser {
    public static PlateauSize parsePlateau(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null.");
        }

        input = input.trim().replaceAll("\\s+", " ");

        if (!input.matches("\\d+ \\d+")) {
            throw new IllegalArgumentException("Input must be two space-separated positive integers.");
        }

        String[] parts = input.split(" ");
        try {
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);

            if (x == 0 || y == 0) {
                throw new IllegalArgumentException("Plateau dimensions must be greater than zero.");
            }

            return new PlateauSize(x, y);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Input values too large");
        }
    }

    public static Position parsePosition(String input) {
        String[] parts = input.split(" ");
        if(parts.length!=3){
            throw new IllegalArgumentException("Invalid input");
        }

        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        Direction direction = null;
        if (parts[2].equalsIgnoreCase("n")) {
            direction = Direction.N;
        } else if (parts[2].equalsIgnoreCase("e")) {
            direction = Direction.E;
        } else if (parts[2].equalsIgnoreCase("s")) {
            direction = Direction.S;
        } else if (parts[2].equalsIgnoreCase("w")) {
            direction = Direction.W;
        }
        if(direction == null){
            throw new IllegalArgumentException("Invalid input");
        }

        return new Position(x, y, direction);

    }

    public static Instruction[] parseInstructions(String input){
        Instruction[] instructions = new Instruction[input.length()];
        for (int i = 0; i < input.length(); i++) {
            instructions[i]=Instruction.valueOf(input.substring(i,i+1));
        }
        return instructions;
    }
}
