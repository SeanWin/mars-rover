package org.example;

public class InputParser {

    public static PlateauSize parsePlateau(String input) {
        String[] coords = input.split(" ");
        int x = Integer.parseInt(coords[0]);
        int y = Integer.parseInt(coords[1]);
        return new PlateauSize(x, y);
    }

    public static Position parsePosition(String input) {
        String[] parts = input.split(" ");
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
        return new Position(x, y, direction);

    }
}
