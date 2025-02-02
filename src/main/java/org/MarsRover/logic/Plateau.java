package org.MarsRover.logic;

import org.MarsRover.Input.PlateauSize;

public class Plateau {
    private PlateauSize plateauSize;

    public Plateau(PlateauSize plateauSize) {
        if(plateauSize.x()<1||plateauSize.y()<1){
            throw new IllegalArgumentException("Plateau size must be non negative");
        }
        this.plateauSize = plateauSize;
    }

    public boolean isWithinBounds(int x, int y){
        return 0 <= x && x <= plateauSize.x() && 0 <= y && y <= plateauSize.y();
    }

    public PlateauSize getPlateauSize() {
        return plateauSize;
    }
}
