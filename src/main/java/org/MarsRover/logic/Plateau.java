package org.MarsRover.logic;

import org.MarsRover.Input.PlateauSize;

public class Plateau {
    private PlateauSize plateauSize;

    public Plateau(PlateauSize plateauSize) {
        if(plateauSize.getX()<1||plateauSize.getY()<1){
            throw new IllegalArgumentException("Plateau size must be non negative");
        }
        this.plateauSize = plateauSize;
    }

    public boolean isWithinBounds(int x, int y){
        return 0 <= x && x <= plateauSize.getX() && 0 <= y && y <= plateauSize.getY();
    }

    public PlateauSize getPlateauSize() {
        return plateauSize;
    }
}
