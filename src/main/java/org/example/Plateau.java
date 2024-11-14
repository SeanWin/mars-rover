package org.example;

public class Plateau {
    PlateauSize plateauSize;

    public Plateau(PlateauSize plateauSize) {
        this.plateauSize = plateauSize;
    }

    public boolean isWithinBounds(Position position){
        return 0 <= position.getX() && position.getX() <= plateauSize.getX() && 0 <= position.getY() && position.getY() <= plateauSize.getY();
    }
}
