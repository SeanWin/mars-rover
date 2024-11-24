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

   public static Position parsePosition(String input){
       if (input == null) {
           throw new IllegalArgumentException("Input cannot be null.");
       }

       input = input.trim().replaceAll("\\s+", " ");

       if (!input.matches("\\d+ \\d+ \\w+")) {
           throw new IllegalArgumentException("Input must be two non-negative integers and a compass direction letter, all space-separated");
       }

       String[] parts = input.split(" ");
       parts[2] = parts[2].toUpperCase();
       try{
           int x = Integer.parseInt(parts[0]);
           int y = Integer.parseInt(parts[1]);
           Direction direction = Direction.valueOf(parts[2]);
           return new Position(x,y,direction);

       } catch (NumberFormatException e) {
           throw new NumberFormatException("Input coordinate values too large");
       } catch (IllegalArgumentException e) {
           throw new IllegalArgumentException("Direction must be one of: N, E, S, W");
       }
   }

    public static Instruction[] parseInstructions(String input){
        Instruction[] instructions = new Instruction[input.length()];
        for (int i = 0; i < input.length(); i++) {
            instructions[i]=Instruction.valueOf(input.substring(i,i+1));
        }
        return instructions;
    }
}
