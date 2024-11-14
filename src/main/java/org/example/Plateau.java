package org.example;

public class Plateau {
    PlateauSize plateauSize;

    public Plateau(PlateauSize plateauSize) {
        this.plateauSize = plateauSize;
    }

    public boolean isWithinBounds(int x, int y){
        return 0 <= x && x <= plateauSize.getX() && 0 <= y && y <= plateauSize.getY();
    }

}
