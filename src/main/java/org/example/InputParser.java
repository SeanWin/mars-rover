package org.example;

public class InputParser {

    public static PlateauSize parsePlateau(String input) {
        String[] coords = input.split(" ");
        if(coords.length!=2){
            throw new NumberFormatException("Invalid input: Expected two numbers separated by space.");
        }
        int x = Integer.parseInt(coords[0]);
        int y = Integer.parseInt(coords[1]);
        return new PlateauSize(x, y);
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
